package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListOwnToolService implements AbstractListService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository toolRepository;
	
	@Override
	public List<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		List<Item> res;
		final int id = request.getPrincipal().getActiveRoleId();
		res= this.toolRepository.findToolsByInventorId(id);
		
		return res;
	}
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getPrincipal().getActiveRoleId();
		final List<Item> tools = this.toolRepository.findToolsByInventorId(id);
		
		return tools.get(0).getInventor().getId()==id;
	}


	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "name","description", "retailPrice");
		
	}
	
	

}
