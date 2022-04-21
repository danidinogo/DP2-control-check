package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.status = TRUE")
	Collection<Toolkit> findAnyToolkits();
	
	@Query("select t from Toolkit t where t.id = :id and t.status = TRUE")
	Toolkit findToolkitById(int id);
	

}
