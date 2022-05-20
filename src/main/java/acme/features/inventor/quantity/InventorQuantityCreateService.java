package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("toolkitId");
		final Toolkit toolkit = this.repository.findOneToolkitById(id);
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		
		//return toolkit.getInventor().getId() == i.getId();
		return true;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setItem(this.repository.findOneItemById(Integer.valueOf(request.getModel().getAttribute("item").toString())));
		request.bind(entity, errors, "number");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int toolkitId = request.getModel().getInteger("toolkitId");
		model.setAttribute("toolkitId", toolkitId);
		final List<Item> items = this.repository.findManyItem();
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "number");
		
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		final Quantity res = new Quantity();
		res.setNumber(0);
		final int id = request.getModel().getInteger("toolkitId");
		final Toolkit toolkit = this.repository.findOneToolkitById(id);
		res.setToolkit(toolkit);
		return res;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);
		
	}
}
