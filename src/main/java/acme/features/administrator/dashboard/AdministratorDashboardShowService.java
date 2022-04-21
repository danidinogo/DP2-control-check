package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.forms.Dashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		return true;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		final Dashboard result = new Dashboard();
		
		final Map<String, Integer> totalsData = new HashMap<String, Integer>();
		for(final String key : result.getTotalsDataKeys()) {
			switch(key) {
				case "Component":
				case "Tool":
					System.out.println(this.repository.getItemTotalsByType(ItemType.valueOf(key.toUpperCase())));
					totalsData.put(key, this.repository.getItemTotalsByType(ItemType.valueOf(key.toUpperCase())));
					break;
				case "Proposed":
				case "Accepted":
				case "Denied":
					totalsData.put(key, this.repository.getPatronageTotalsByStatus(Status.valueOf(key.toLowerCase())));
					break;
				default:
					break;
			}
		}
		
		result.setTotalsData(totalsData);
		
		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalsData");
	}
}
