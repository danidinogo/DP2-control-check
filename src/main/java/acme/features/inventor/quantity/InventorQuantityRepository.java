package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorQuantityRepository extends AbstractRepository{

	@Query("select t from Toolkit t where t.id =:id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserId(int id);
	
	@Query("select i from Item i")
	List<Item> findManyItem();
	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
}
