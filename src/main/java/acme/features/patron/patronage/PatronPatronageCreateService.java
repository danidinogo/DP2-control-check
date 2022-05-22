package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.enums.PublishedStatus;
import acme.enums.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	
	@Override
	public boolean authorise(Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Patronage> request, Patronage entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublishedStatus(PublishedStatus.NONE_PUBLISHED);
		entity.setInventor(this.repository.findInventorByUsername(request.getModel().getString("inventorUN")));
		request.bind(entity, errors, "code", "legalStuff", "budget", "startsAt", "finishesAt","link");
	}

	@Override
	public void unbind(Request<Patronage> request, Patronage entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("inventors", this.repository.findInventors());
		request.unbind(entity, model, "code", "legalStuff", "budget", "startsAt", "finishesAt","link");
		
	}

	@Override
	public Patronage instantiate(Request<Patronage> request) {
		assert request != null;

		final Patronage result;
		Date startTime;
		Date finishedTime;
		Inventor inventor = new Inventor();
		
		int id = request.getPrincipal().getActiveRoleId();
		startTime = new Date(System.currentTimeMillis());
		finishedTime= new Date(System.currentTimeMillis());
		
		Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		
		result = new Patronage();
		result.setStatus(Status.proposed);
		result.setCode("");
		result.setLegalStuff("");
		result.setStartsAt(startTime);
		result.setFinishesAt(finishedTime);
		
		result.setBudget(money);
		result.setLink("");
		result.setPublishedStatus(PublishedStatus.NONE_PUBLISHED);
		result.setInventor(inventor);
		result.setPatron(this.repository.findPatronById(id));


		return result;
	}

	@Override
	public void validate(Request<Patronage> request, Patronage entity, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Request<Patronage> request, Patronage entity) {
		assert request != null;
		assert entity != null;
		
		

		this.repository.save(entity);
	}

}
