package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(item) FROM Item item where item.type = :type")
	int getItemTotalsByType(ItemType type);
	
	@Query("SELECT COUNT(patronage) FROM Patronage patronage where patronage.status = :status")
	int getPatronageTotalsByStatus(Status status);
}
