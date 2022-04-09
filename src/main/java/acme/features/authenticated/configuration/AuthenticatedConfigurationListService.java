package acme.features.authenticated.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedConfigurationListService implements AbstractListService<Authenticated, Configuration>{
	
	@Autowired
	protected AuthenticatedConfigurationRepository repository;
	
	@Override
	public Collection<Configuration> findMany(final Request<Configuration> request) {
		assert request != null;
		
		return this.repository.findMany();
	}
	
	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"defaultCurr", "acceptedCurr");
	}

}
