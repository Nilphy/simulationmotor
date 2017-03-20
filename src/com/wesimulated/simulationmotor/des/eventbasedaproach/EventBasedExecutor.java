package com.wesimulated.simulationmotor.des.eventbasedaproach;

import java.util.TreeSet;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

public class EventBasedExecutor extends OperationBasedExecutor {

	public EventBasedExecutor(EndCondition endCondition) {
		super(endCondition);
		this.setBOperations(new TreeSet<>());
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execBPhase();		
	}
}
