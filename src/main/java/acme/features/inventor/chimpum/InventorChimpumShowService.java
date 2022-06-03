package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.xomemi.Xomemi;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Xomemi>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Xomemi c = this.repository.findChimpumById(id);
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		return c.getArtefact().getInventor().getId()==i.getId();
	}

	@Override
	public Xomemi findOne(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Xomemi res = this.repository.findChimpumById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Xomemi> request, final Xomemi entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "creationMoment", "name", "statement", "startDate", "finishDate", "ration", "furtherInfo");
		
	}

}
