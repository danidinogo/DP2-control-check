package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		final Quantity q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		
		return q.getToolkit().getInventor().getId()==i.getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "number", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.info", "item.status", "item.type");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.info", "item.status", "item.type");
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		
		return this.repository.findOneQuantityById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		final Item i = entity.getItem();
		this.repository.save(i);
		this.repository.save(entity);
		
		
	}

}
