package com.wesimulated.simulationmotor.operationbased.activityScanningAproach;

import java.util.LinkedList;

import com.wesimulated.simulation.runparameters.EndCodition;

public class Excecutor extends com.wesimulated.simulationmotor.operationbased.Excecutor {

	public Excecutor(EndCodition endCondition) {
		super(endCondition);
		this.setCOperations(new LinkedList<>());	
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execCPhase();
	}
}
