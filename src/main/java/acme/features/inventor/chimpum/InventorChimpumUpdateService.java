package acme.features.inventor.chimpum;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.ItemType;
import acme.entities.item.Status;
import acme.entities.xomemi.Xomemi;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Xomemi>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository adminRepository;
	
	@Override
	public boolean authorise(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Xomemi c = this.repository.findChimpumById(id);
		final Inventor inv = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		return c.getArtefact().getInventor().getId()==inv.getId() && c.getArtefact().getStatus().equals(Status.NON_PUBLISHED) && c.getArtefact().getType().equals(ItemType.TOOL); 
	}

	@Override
	public void bind(final Request<Xomemi> request, final Xomemi entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "statement", "startDate", "finishDate", "ration", "furtherInfo");
		
	}

	@Override
	public void unbind(final Request<Xomemi> request, final Xomemi entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "statement", "startDate", "finishDate", "ration", "furtherInfo", "creationMoment");
		
	}

	@Override
	public Xomemi findOne(final Request<Xomemi> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Xomemi res = this.repository.findChimpumById(id);
		
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
		
		final Configuration c = this.adminRepository.findConfiguration();
		
		if(entity.getRation().getAmount()<=0) {
			errors.state(request, false, "ration", "inventor.messages.form.error.budget.ammount");
		}
		
		if(!c.getAcceptedCurr().contains(entity.getRation().getCurrency())) {
			errors.state(request, false, "ration", "inventor.messages.form.error.budget.currency");
		}
		
	}

	@Override
	public void update(final Request<Xomemi> request, final Xomemi entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
