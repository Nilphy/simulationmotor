package com.wesimulated.simulationmotor.operationbased.activityScanningAproach;

import java.util.LinkedList;

import com.wesimulated.simulation.runparameters.EndCodition;
import com.wesimulated.simulationmotor.operationbased.OperationBasedExecutor;

public class ActivityScanningExecutor extends OperationBasedExecutor {

	public ActivityScanningExecutor(EndCodition endCondition) {
		super(endCondition);
		this.setCOperations(new LinkedList<>());	
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execCPhase();
	}
}
