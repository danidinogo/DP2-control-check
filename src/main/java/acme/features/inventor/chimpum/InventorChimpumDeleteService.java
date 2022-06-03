package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.Status;
import acme.entities.xomemi.Xomemi;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, Xomemi>{

	@Autowired
	protected InventorChimpumRepository repository;;
	
	@Override
	public boolean authorise(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Xomemi c = this.repository.findChimpumById(id);
		final Inventor inv = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		final Item i = c.getArtefact();
		return i.getInventor().getId()==inv.getId() && i.getStatus().equals(Status.NON_PUBLISHED); //&& c.getArtefact().getStatus().equals(ItemType.TOOL); 
	}

	@Override
	public void bind(final Request<Xomemi> request, final Xomemi entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
		
	}

	@Override
	public void unbind(final Request<Xomemi> request, final Xomemi entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
		
	}

	@Override
	public Xomemi findOne(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");

		return this.repository.findChimpumById(id);
	}

	@Override
	public void validate(final Request<Xomemi> request, final Xomemi entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Xomemi> request, final Xomemi entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}

}
