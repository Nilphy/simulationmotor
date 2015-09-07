package com.wesimulated.simulationmotor.operationbased.eventbasedaproach;

import java.util.TreeSet;

import com.wesimulated.simulation.runparameters.EndCodition;

public class Excecutor extends com.wesimulated.simulationmotor.operationbased.Excecutor {

	public Excecutor(EndCodition endCondition) {
		super(endCondition);
		this.setBOperations(new TreeSet<>());
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execBPhase();		
	}
}
