package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(item) FROM Item item WHERE item.type = :type")
	Integer getItemTotalsByType(ItemType type);
	
	@Query("SELECT COUNT(patronage) FROM Patronage patronage WHERE patronage.status = :status")
	Integer getPatronageTotalsByStatus(Status status);
	
	@Query("select min(patronage.budget.amount), max(patronage.budget.amount), avg(patronage.budget.amount), stddev(patronage.budget.amount) from Patronage patronage where patronage.status = :status")
	String getPatronageBudgetByStatus(Status status);
	
	
}
