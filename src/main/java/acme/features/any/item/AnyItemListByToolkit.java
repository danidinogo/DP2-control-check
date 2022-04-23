package acme.features.any.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyItemListByToolkit  implements AbstractListService<Any, Item>{

	@Autowired
	protected AnyItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public List<Item> findMany(final Request<Item> request){
		assert request !=null;
		
		List<Item> res;
		final int id = request.getModel().getInteger("id");
		res=this.repository.findItemByToolkitId(id);
		return res;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "code", "technology", "description" , "info");
		
	}
}
