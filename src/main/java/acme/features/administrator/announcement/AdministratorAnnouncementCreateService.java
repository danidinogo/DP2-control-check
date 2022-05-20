package acme.features.administrator.announcement;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement>{

	
	@Autowired
	protected AdministratorAnnouncementRepository repository;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;
		Announcement res;
		res = new Announcement();
		return res;
	}
	
	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date creationTime;
		Calendar calendar;
		creationTime = new Date();
		calendar = Calendar.getInstance();
		creationTime = calendar.getTime();
		request.bind(entity, errors, "title", "body", "critic", "link");
		entity.setCreation(creationTime);
		
	}
	
	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirm = request.getModel().getBoolean("confirm");
		errors.state(request, confirm, "confirm", "administrator.announcement.accept.error");

	}
	
	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "critic", "link");
		model.setAttribute("confirm", false);
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
