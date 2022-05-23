package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage> {

	
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		entity.setInventor(this.repository.findInventorByUsername(request.getModel().getString("inventorUN")));
		request.bind(entity, errors, "code", "legalStuff", "budget", "startsAt", "finishesAt","link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("inventors", this.repository.findInventors());
		
		
		request.unbind(entity, model, "status","code", "legalStuff", "budget", "startsAt", "finishesAt","link");
		
	}

	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Patronage patronage = this.repository.findPatronageByCode(entity.getCode());
		
		if(patronage != null) {
			errors.state(request, patronage.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
		
		errors.state(request, entity.getBudget().getAmount() >= 0.00, "budget", "inventor.item.title.minPrice");
	}

	

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		// TODO Auto-generated method stub
		this.repository.save(entity);
	}

}
