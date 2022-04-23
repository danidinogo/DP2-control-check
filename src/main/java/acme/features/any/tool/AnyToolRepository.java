package acme.features.any.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolRepository extends AbstractRepository {

	@Query("SELECT item FROM Item item WHERE item.type = acme.entities.item.ItemType.TOOL")
	Collection<Item> findTools();
	
	@Query("SELECT item FROM Item item WHERE item.type = acme.entities.item.ItemType.TOOL AND item.id = :id")
	Item findToolById(Integer id);

}
