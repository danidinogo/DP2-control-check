package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Quantity q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		
		return q.getToolkit().getInventor().getId() == i.getId();
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Quantity res = this.repository.findOneQuantityById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.info", "item.status", "item.type");
		
	}

}
