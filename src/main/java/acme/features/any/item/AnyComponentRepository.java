package acme.features.any.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyComponentRepository extends AbstractRepository {

	
	@Query( "select t from Item t where t.type = 'COMPONENT'")
	Collection<Item> findAnyComponents();
	
	@Query("select t from Item t where t.id= :id")
	Item findItemById(int id);
	
	@Query("select q.item from Quantity q where q.toolkit.id= :id")
	List<Item> findItemByToolkitId(int id);
}
