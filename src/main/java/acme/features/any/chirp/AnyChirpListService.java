package acme.features.any.chirp;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyChirpListService implements AbstractListService<Any, Chirp>{

	@Autowired
	protected AnyChirpRepository repository;

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Chirp> findMany(final Request<Chirp> request) {
		assert request != null;

		Collection<Chirp> res;
		Calendar calendar;
		Date maxTime;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		maxTime = calendar.getTime();

		res = this.repository.findLastChirp(maxTime);

		return res;
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "author", "body", "creationTime","email","title");
		
	}
	
}