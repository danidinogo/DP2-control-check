package acme.features.administrator.dashboard;

import java.util.List;

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
	
	@Query("SELECT MIN(patronage.budget.amount), MAX(patronage.budget.amount), AVG(patronage), STDDEV(patronage.budget.amount) FROM Patronage patronage WHERE patronage.status = :status")
	List<Double> getPatronageBudgetByStatus(Status status);
}
