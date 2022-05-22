package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Toolkit t = this.repository.findOneToolkitById(request.getModel().getInteger("id"));
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		
		
		return t.getInventor().getId()==i.getId();
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findQuantityByToolkitId(id);
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Toolkit t = this.repository.findOneToolkitById(request.getModel().getInteger("id"));
		model.setAttribute("tStatus", t.getStatus());
		
		request.unbind(entity, model, "number", "item.name", "item.code", "item.retailPrice", "item.status");
		
		
		
	}

}
