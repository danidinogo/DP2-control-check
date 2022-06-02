package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChimpumDashboard implements Serializable{
	
	protected static final long	serialVersionUID	= 1L;
	
	Double	averageItemsChimpum;
	
	Double	averageBudgetChimpumCurr1;
	Double	averageBudgetChimpumCurr2;
	Double	averageBudgetChimpumCurr3;
	
	Double	deviationBudgetChimpumCurr1;
	Double	deviationBudgetChimpumCurr2;
	Double	deviationBudgetChimpumCurr3;
	
	Double	minimunBudgeChimpumCurr1;
	Double	minimunBudgetChimpumCurr2;
	Double	minimunBudgetChimpumCurr3;
	
	Double	maximunBudgetChimpumCurr1;
	Double	maximunBudgetChimpumCurr2;
	Double	maximunBudgetChimpumCurr3;

	


}
