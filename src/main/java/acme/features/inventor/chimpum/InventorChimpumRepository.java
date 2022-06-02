package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{
	
	@Query("select c from Chimpum c where c.item.inventor.id = :id")
	Collection<Chimpum> findChimpumsByItemId(int id);
	
	@Query("select p from Chimpum p where p.item.id = :id")
	List<Chimpum> findByItemId(int id);
	
	@Query("select p from Item p where p.id = :id")
	Item findItemById(int id);
	
	@Query("select p from Chimpum p where p.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("select p from Chimpum p where p.item.id = :id")
	Collection<Chimpum> findChimpumById2(int id);
	
	@Query("select p from Chimpum p")
	List<Chimpum> findChimpums();
	
	@Query("select p from Chimpum p where p.item.id = :itemId")
	Collection<Chimpum> findAllChimpunsByItemId(int itemId);
	
	@Query("select p from Chimpum p where p.id = :id")
	Chimpum findChimpumById(int id);

}