package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		final PatronDashboard result = new PatronDashboard();
		int id = request.getPrincipal().getActiveRoleId();
		result.setPatronagesBudgets(this.getPatronagesBudgets(result.getDataKeys()));
		
		result.setTotalNumberPatronage(this.getTotals(id));
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberPatronage", "patronagesBudgets");
	}
	
	private Map<Status, Integer> getTotals(final int id) {
		
		
		final Map<Status, Integer> totals = new HashMap<Status, Integer>();
		for(final Status key : Status.values()) {
			
			totals.put(key, this.repository.getPatronageTotalsByStatus(key, id));	
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
