package com.wesimulated.simulationmotor.des.eventbasedaproach;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

public class EventBasedExecutor extends OperationBasedExecutor {

	public EventBasedExecutor(EndCondition endCondition) {
		super(endCondition);
	}

	@Override
	protected void doCicle() {
		this.execAPhase();
		this.execBPhase();		
	}
}
