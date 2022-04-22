package acme.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import acme.entities.item.ItemType;
import acme.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	/*
	 * 	Old attrs:
	 * 	Integer	totalNumberComponents;
	 *	Integer	totalNumberTools;
	 *	Integer	totalNumberPatronagesProposed;
	 *	Integer	totalNumberPatronagesAccepted;
	 *	Integer	totalNumberPatronagesDenied;
	 *	
	 *	averageRetailPriceComponents
	 *  deviationRetailPriceComponents
	 *  minimunRetailPriceComponents
	 *  maximunRetailPriceComponents
		
		maximunPatronagesBudgetPatronagesProposed
		minimunPatronagesBudgetPatronagesProposed
		deviationPatronagesBudgetProposed
		averagePatronagesBudgetProposed
	 */
	
	Map<String, Integer> totalsData;
	
	Map<String, Map<Pair<String, String>, Double>> componentsData;
	
	Map<ItemType, Map<Pair<String, String>, Double>> itemsRetailPrice;
	
	Map<Status, Map<String, Double>> patronagesBudgets;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	
	public List<String> getTotalsDataKeys() {
		return new ArrayList<String>(Arrays.asList("Component", "Tool", "Proposed", "Accepted", "Denied"));
	}
	
	public List<String> getDataKeys() {
		return new ArrayList<String>(Arrays.asList("Min", "Max", "Avg", "Dev"));
	}

}
