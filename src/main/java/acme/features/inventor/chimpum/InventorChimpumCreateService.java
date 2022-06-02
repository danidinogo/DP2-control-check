package acme.features.inventor.chimpum;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
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
		Date creation;
		Date startsAt;
		Date finishesAt;
		
		
		creation=new Date(System.currentTimeMillis());
		startsAt=DateUtils.addMonths(creation, 1);
		startsAt=DateUtils.addMinutes(creation, 1);
		finishesAt=DateUtils.addMonths(startsAt, 1);
		finishesAt=DateUtils.addMinutes(startsAt, 1);
		
		final int itemId= request.getModel().getInteger("id");
		
		final Item item=this.repository.findItemById(itemId);
		
		final Money money =new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		
		result.setCode(this.generateCode());
		result.setItem(item);
		
		result.setCreation(creation);
		result.setStartsAt(startsAt);
		result.setFinishesAt(finishesAt);
		
		result.setTitle("");
		result.setDescription("");
		result.setBudget(money);
		result.setLink("");
		
		
	

		return result;
	}
	
	private String generateCode() {
		String code = "";
		final List<String> alphabet = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		
		for(int i=0; i<3; i++) {
			code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		}
		code += "-";
		for(int i=0; i<3; i++) {
			code += Integer.toString(ThreadLocalRandom.current().nextInt(0, 9));
		}
		code += "-";
		code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		
		return code;
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
		model.setAttribute("id", request.getModel().getAttribute("id"));
		
	}
	
	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
	

		this.repository.save(entity);
		
	}
}