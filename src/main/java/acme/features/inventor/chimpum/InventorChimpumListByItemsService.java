package acme.features.inventor.chimpum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListByItemsService implements AbstractListService<Inventor, Chimpum> {
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public List<Chimpum> findMany(final Request<Chimpum> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findByItemId(id);
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creation", "title", "description", "startsAt","finishesAt","budget","link","item.code");		
	}

}
