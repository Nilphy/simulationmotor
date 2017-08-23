package com.wesimulated.simulationmotor.des.activityScanningAproach;

import java.util.Date;
import java.util.LinkedList;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.COperation;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

/**
 * The activity scaning aproach represent all events as operations similar to the COperations of the three phase approach
 * The BOperations just check if the date is due to know if it can be executed
 * 
 * @author Carolina
 *
 */
public class ActivityScanningExecutor extends OperationBasedExecutor {

	public ActivityScanningExecutor(EndCondition endCondition) {
		super(endCondition);
		this.setCOperations(new LinkedList<>());	
	}

	@Override
	protected void doCicle() {
		Date nextDate = this.findNextDateOfPosibleStateChange();
		this.getClock().advanceUntil(nextDate);
		this.execCPhase();
	}

	private Date findNextDateOfPosibleStateChange() {
		Date actualDate = this.getClock().getCurrentDate();
		Date nextDate = null;
		for (COperation cOperation : this.getCOperations()) {
			if (cOperation.dateOfOccurrenceIsBetween(actualDate, nextDate)) {
				nextDate = cOperation.getDateOfOccurrence(actualDate);
			}
		}
		return nextDate;
	}
}
