package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Status;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors, "code", "title", "descripcion", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "descripcion", "assemblyNotes", "link");
		
		final List<Item> items = this.repository.findManyItem();
		model.setAttribute("items", items);
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		
		final Toolkit res = new Toolkit();
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		final List<Quantity> quantities = new ArrayList<>();
		
		res.setInventor(i);
		res.setQuantity(quantities);
		res.setCode("");
		res.setDescripcion("");
		res.setAssemblyNotes("");
		res.setLink("");
		res.setStatus(Status.NONE_PUBLISHED);
		
		return res;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
