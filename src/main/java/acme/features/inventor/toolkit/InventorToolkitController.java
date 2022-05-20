package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("inventor/toolkit/")
public class InventorToolkitController extends AbstractController<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitListOwnService listService;
	
	@Autowired
	protected InventorToolkitShowService showService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-mine", "list", this.listService);
		super.addCommand("show", this.showService);
	}
	
	

}
