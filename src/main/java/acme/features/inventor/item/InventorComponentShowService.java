package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorComponentShowService implements AbstractShowService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository componentRepository; 
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		final int componentId = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		
		final List<Item> components = this.componentRepository.findComponentsByInventorId(inventorId);
		final Item component = this.componentRepository.findOneComponentById(componentId);
		
		return components.contains(component);
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.componentRepository.findOneComponentById(id);
		
	
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info");
		
	}

}
