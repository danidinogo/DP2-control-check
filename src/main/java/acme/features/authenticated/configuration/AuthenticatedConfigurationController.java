package acme.features.authenticated.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.configuration.Configuration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/authenticated/configuration/")
public class AuthenticatedConfigurationController extends AbstractController<Authenticated, Configuration>{
	
	@Autowired
	protected AuthenticatedConfigurationListService listService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
	}

}
