package acme.features.inventor.chimpum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum> {

	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		int itemId;
		Item item;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findItemById(itemId);
		result = request.getPrincipal().getActiveRoleId() == item.getInventor().getId();

		return result;
	}
	
	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;
		int itemId;
		Item item;
		Date date;
		int chimpumId;
		
		itemId = request.getModel().getInteger("id");
		item = this.repository.findItemById(itemId);
		date = new Date();
		chimpumId = request.getModel().getInteger("id");
		
		result = new Chimpum();
		result.setItem(item);
		result.setCreation(date);
		result.setCode(this.generateCode(item.getCode()));

		return result;
	}
	
	private String generateCode(final String itemCode) {
		final List<String> code = new ArrayList<String>();
		
		code.add(itemCode);
		code.add(String.format("%04d" , this.repository.findChimpums().size()+1));
		
		return String.join("-", code);
	}
	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code","creation", "title", "description", "startsAt", "finishesAt", "budget", "link");
		
	}
	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Boolean isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "inventor.chimpum.form.accept.error");
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code","creation", "title", "description", "startsAt", "finishesAt", "budget", "link");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("confirm", "false");
	}
	
	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		entity.setCreation(new Date());
		entity.setCode(this.generateCode(entity.getItem().getCode()));

		this.repository.save(entity);
		
	}
}
