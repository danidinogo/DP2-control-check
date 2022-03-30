package acme.forms;

import java.io.Serializable;
import java.util.List;

public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	
	Double	totalNumberComponents;
	List<Double> averageRetailPriceComponentsByTechCurr1;
	List<Double> averageRetailPriceComponentsByTechCurr2;
	List<Double> averageRetailPriceComponentsByTechCurr3;
	
	List<Double> deviationRetailPriceComponentsByTechCurr1;
	List<Double> deviationRetailPriceComponentsByTechCurr2;
	List<Double> deviationRetailPriceComponentsByTechCurr3;
	
	List<Double> minimunRetailPriceComponentsByTechCurr1;
	List<Double> minimunRetailPriceComponentsByTechCurr2;
	List<Double> minimunRetailPriceComponentsByTechCurr3;
	
	List<Double> maximunRetailPriceComponentsByTechCurr1;
	List<Double> maximunRetailPriceComponentsByTechCurr2;
	List<Double> maximunRetailPriceComponentsByTechCurr3;
	
	Double	totalNumberTools;
	
	Double	averageRetailPriceToolsCurr1;
	Double	averageRetailPriceToolsCurr2;
	Double	averageRetailPriceToolsCurr3;
	
	Double	deviationRetailPriceToolsCurr1;
	Double	deviationRetailPriceToolsCurr2;
	Double	deviationRetailPriceToolsCurr3;
	
	Double	minimumRetailPriceToolsCurr1;
	Double	minimumRetailPriceToolsCurr2;
	Double	minimumRetailPriceToolsCurr3;

	Double	maximumRetailPriceToolsCurr1;
	Double	maximumRetailPriceToolsCurr2;
	Double	maximumRetailPriceToolsCurr3;
	
	
	Double	totalNumberPatronagesProposed;
	Double	totalNumberPatronagesAccepted;
	Double	totalNumberPatronagesDenied;
	
	Double	averagePatronagesBudgetProposed;
	Double	averagePatronagesBudgetAccepted;
	Double	averagePatronagesBudgetDenied;
	
	Double	deviationPatronagesBudgetProposed;
	Double	deviationPatronagesBudgetAccepted;
	Double	deviationPatronagesBudgetDenied;
	
	Double	minimunPatronagesBudgetPatronagesProposed;
	Double	minimunPatronagesBudgetPatronagesAccepted;
	Double	minimunPatronagesBudgetPatronagesDenied;
	
	Double	maximunPatronagesBudgetPatronagesProposed;
	Double	maximunPatronagesBudgetPatronagesAccepted;
	Double	maximunPatronagesBudgetPatronagesDenied;
	



	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
