package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	
	

	@Autowired
	protected PatronPatronageRepository repository;


	
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		int id = request.getPrincipal().getActiveRoleId();
		Collection<Patronage> patronages = this.repository.findAllPatronagesByPatronId(id);
		int patronage_id = request.getModel().getInteger("id");
		Patronage patronage = this.repository.findPatronageById(patronage_id);
		return patronages.contains(patronage);
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
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget",  "startsAt", "finishesAt",  "link", "inventor.company","inventor.statement","inventor.link");
		
	}

}
