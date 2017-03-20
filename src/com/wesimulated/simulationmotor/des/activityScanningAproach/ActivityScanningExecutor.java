package com.wesimulated.simulationmotor.des.activityScanningAproach;

import java.util.LinkedList;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

public class ActivityScanningExecutor extends OperationBasedExecutor {

	public ActivityScanningExecutor(EndCondition endCondition) {
		super(endCondition);
		this.setCOperations(new LinkedList<>());	
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execCPhase();
	}
}
