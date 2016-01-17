package com.wesimulated.simulationmotor.operationbased.eventbasedaproach;

import java.util.TreeSet;

import com.wesimulated.simulation.runparameters.EndCodition;
import com.wesimulated.simulationmotor.operationbased.OperationBasedExecutor;

public class EventBasedExecutor extends OperationBasedExecutor {

	public EventBasedExecutor(EndCodition endCondition) {
		super(endCondition);
		this.setBOperations(new TreeSet<>());
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execBPhase();		
	}
}
