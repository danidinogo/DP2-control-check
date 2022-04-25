package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{
	
	@Autowired
	protected InventorItemToolkitListService listByToolkitService;
	
	@Autowired
	protected InventorComponentListService listByComponentService;
	
	@Autowired
	protected InventorComponentShowService showComponentService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("list-by-toolkit","list", this.listByToolkitService);
		super.addCommand("list-component", "list", this.listByComponentService);
		super.addCommand("show", this.showComponentService);
	}
	
}
