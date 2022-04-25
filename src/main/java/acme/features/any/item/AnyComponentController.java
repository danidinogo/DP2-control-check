package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyComponentController extends AbstractController<Any, Item>{
		
		@Autowired
		protected AnyComponentListService listService;
		
		@Autowired
		protected AnyComponentShowService showService;
		
		@Autowired
		protected AnyToolShowService showToolService;
		
		@Autowired
		protected AnyToolListService listToolService;
		
		@Autowired
		protected AnyItemListByToolkit listByToolkitService;
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list-component", "list", this.listService);
			super.addCommand("show-component", "list", this.showService);
			super.addCommand("list-tool", "list", this.listToolService);
			super.addCommand("show-tool", this.showToolService);
			super.addCommand("list-by-toolkit", "list", this.listByToolkitService);
		}
		
		

	}


