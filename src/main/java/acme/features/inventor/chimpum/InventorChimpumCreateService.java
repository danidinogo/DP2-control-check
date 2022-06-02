package acme.features.inventor.chimpum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
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

		final Chimpum result;
		result = new Chimpum();
		Date creationTime;
		Date startTime;
		Date finishedTime;
		
		creationTime = new Date(System.currentTimeMillis());
		startTime= DateUtils.addMonths( creationTime,1);
		startTime= DateUtils.addMinutes(creationTime, 1);
		finishedTime= DateUtils.addMonths( startTime,1);
		finishedTime= DateUtils.addMinutes(startTime, 1);
		
		int itemId = request.getModel().getInteger("id");
		
		Item item = this.repository.findItemById(itemId);
		
		
		final Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		
		final int id = request.getPrincipal().getActiveRoleId();
		Inventor inventor = this.repository.findInventorById(id);
		
		
		result.setCode(this.generateCode(item.getCode()));
		result.setItem(item);
		result.setInventor(inventor);
		result.setCreation(creationTime);
		result.setStartsAt(startTime);
		result.setFinishesAt(finishedTime);
		
		result.setTitle("");
		result.setDescription("");
		result.setBudget(money);
		result.setLink("");
	
		
		
		
		

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
		
	
		request.bind(entity, errors, "code", "title", "description", "startsAt", "finishesAt", "budget", "link");
		
	}
	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
	
		
		request.unbind(entity, model,"code", "title", "description", "startsAt", "finishesAt", "budget", "link");
	
	}
	
	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		
		entity.setCode(this.generateCode(entity.getItem().getCode()));

		this.repository.save(entity);
		
	}
}
