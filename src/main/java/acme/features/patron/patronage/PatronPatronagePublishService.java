package acme.features.patron.patronage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.enums.PublishedStatus;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;

import acme.framework.services.AbstractUpdateService;

import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage> {

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
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Patronage> request, Patronage entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	
	@Override
	public void validate(Request<Patronage> request, Patronage entity, Errors errors) {
		
	}

	

	@Override
	public Patronage findOne(Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void update(Request<Patronage> request, Patronage entity) {
		// TODO Auto-generated method stub
		entity.setPublishedStatus(PublishedStatus.PUBLISHED);
		this.repository.save(entity);
	}

	
	

}
