package acme.features.inventor.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{

	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	@Query("select t from Toolkit t where t.id = :masterId")
	Toolkit findOneToolkitById(int masterId);
	

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);
	
	@Query("select distinct(q.toolkit) from Quantity q where q.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("select i from Item i where i.type ='COMPONENT' and i.inventor.id = :id")
	List<Item> findComponentsByInventorId(int id);
	
	@Query("select i from Item i where i.type ='COMPONENT' and i.id = :id")
	Item findOneComponentById(int id);
}
