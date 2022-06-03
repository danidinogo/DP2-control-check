package acme.features.inventor.chimpum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.item.Status;
import acme.entities.xomemi.Xomemi;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Xomemi>{

	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository adminRepository;
	
	@Override
	public boolean authorise(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Item i = this.repository.findItemById(id);
		final Inventor inv = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		return i.getInventor().getId()==inv.getId() && i.getStatus().equals(Status.NON_PUBLISHED) && i.getType().equals(ItemType.TOOL); 
	}

	@Override
	public void bind(final Request<Xomemi> request, final Xomemi entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Calendar calendar = Calendar.getInstance();
		final Item i = this.repository.findItemById(request.getModel().getInteger("id"));
		
		request.bind(entity, errors, "name", "statement", "startDate", "finishDate", "ration", "furtherInfo");
		entity.setCreationMoment(calendar.getTime());
		entity.setArtefact(i);
		
	}

	@Override
	public void unbind(final Request<Xomemi> request, final Xomemi entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "startDate", "finishDate", "ration", "furtherInfo");
		
		model.setAttribute("id", request.getModel().getInteger("id"));
		model.setAttribute("name", entity.getName());
		model.setAttribute("statement", entity.getStatement());
		model.setAttribute("ration", entity.getRation());
		model.setAttribute("furtherInfo", entity.getFurtherInfo());
	}

	@Override
	public Xomemi instantiate(final Request<Xomemi> request) {
		assert request != null;
		
		final Xomemi res = new Xomemi();
		final Money budget = new Money();
		final Item i = this.repository.findItemById(request.getModel().getInteger("id"));
		//final Date fecha = new Date();
		budget.setCurrency("EUR");
		budget.setAmount(0.0);
		
		res.setCode(this.generateCode());
		res.setName("");
		res.setStatement("");
		res.setRation(budget);
		res.setFurtherInfo("");
		res.setArtefact(i);
		return res;
	}
	

	@Override
	public void validate(final Request<Xomemi> request, final Xomemi entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (entity.getFinishDate() != null && entity.getStartDate() != null && entity.getStartDate().after(entity.getFinishDate())) {
			errors.state(request, false, "startDate", "inventor.message.form.error.date");
		}

		if (entity.getFinishDate() != null && entity.getStartDate() != null && entity.getFinishDate().before(entity.getStartDate())) {
			errors.state(request, false, "finishDate", "inventor.message.form.error.date2");
		}
		
		Calendar calendar;
		
		if(entity.getStartDate() != null) {
			calendar = Calendar.getInstance();
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MONTH, -1);
			
			if(calendar.getTime().before(entity.getCreationMoment())) {
				errors.state(request, false, "startDate", "inventor.message.form.error.startDate");
			}
			if(entity.getStartDate()!=null & entity.getFinishDate()!=null) {
				final long diff = (entity.getFinishDate().getTime()-entity.getStartDate().getTime())/1000/60/60/24;
	
				if(entity.getStartDate().before(entity.getFinishDate()) && diff<7) {
					errors.state(request, false, "finishDate", "inventor.message.form.error.finishDate");
				}
				
			}
		} else {
			errors.state(request, false, "startDate", "inventor.message.form.error.startDate");
		}
		
		final Configuration c = this.adminRepository.findConfiguration();
		
		if(entity.getRation().getAmount()<=0) {
			errors.state(request, false, "ration", "inventor.messages.form.error.budget.ammount");
		}
		
		if(!c.getAcceptedCurr().contains(entity.getRation().getCurrency())) {
			errors.state(request, false, "ration", "inventor.messages.form.error.budget.currency");
		}
		
		final List<Item> items = this.repository.findItemsWithChimpum();
		for(final Item i:items) {
			if(i.getId()==entity.getArtefact().getId()) {
				errors.state(request, false, "name", "inventor.messages.form.error.duplicate");
			}
		}
		
		

		
	}

	@Override
	public void create(final Request<Xomemi> request, final Xomemi entity) {
		assert request != null;
		assert entity != null;
		
		final Item i = this.repository.findItemById(request.getModel().getInteger("id"));
		entity.setArtefact(i);
		
		System.out.println(this.generateCode());
		
		entity.setCode(this.generateCode());
		
		this.repository.save(entity);
	}
	
	private String generateCode() {
		final Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		final String day= String.format("%02d" , calendar2.get(Calendar.DAY_OF_MONTH));
		final String month= String.format("%02d" , calendar2.get(Calendar.MONTH));
		final String year = String.valueOf(calendar2.get(Calendar.YEAR)).substring(2);
		
		String code = year+month+day+"#";
		
		final List<String> alphabet = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		
		for(int i=0; i<3; i++) {
			code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		}
		
		return code;
	}

}
