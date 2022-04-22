package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

		result.setTotalsData(this.getTotals(result.getTotalsDataKeys()));
		result.setPatronagesBudgets(this.getPatronagesBudgets(result.getBudgetKeys()));
		
		System.out.println(result.getPatronagesBudgets().toString());
		
		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalsData", "patronagesBudgets");
	}
	
	private Map<String, Integer> getTotals(final List<String> totalsKeys) {
		final Map<String, Integer> totals = new HashMap<String, Integer>();
		for(final String key : totalsKeys) {
			switch(key) {
				case "Component":
				case "Tool":
					totals.put(key, this.repository.getItemTotalsByType(ItemType.valueOf(key.toUpperCase())));
					break;
				case "Proposed":
				case "Accepted":
				case "Denied":
					totals.put(key, this.repository.getPatronageTotalsByStatus(Status.valueOf(key.toLowerCase())));
					break;
				default:
					break;
			}
		}
		return totals;
	}
	
	private Map<Status, Map<String, Double>> getPatronagesBudgets(final List<String> budgetKeys) {
		final Map<Status, Map<String, Double>> patronageBudgets = new HashMap<Status, Map<String, Double>>();
		for(final Status status : Status.values()) {
			// TODO it's been tried to have a List<Double> but it only returns 1 index instead of 4 indexes-list. Maybe there is a better way
			final String budgetData = this.repository.getPatronageBudgetByStatus(status);
			
			final String[] budget = budgetData.split(",");
			
			final List<Double> budgetDbl = new ArrayList<Double>();
			for(final String b : budget) {
				budgetDbl.add(Double.valueOf(b));
			}
			
			final Map<String, Double> bd = new HashMap<String, Double>();
			for(int i=0; i<budgetDbl.size(); i++) {
				bd.put(budgetKeys.get(i), budgetDbl.get(i));
			}
			
			patronageBudgets.put(status, bd);
		}
		return patronageBudgets;
	}
}
