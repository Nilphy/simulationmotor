package com.wesimulated.simulationmotor.des.threefaseaproach;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

public class ThreePhaseExecutor extends OperationBasedExecutor {

	public ThreePhaseExecutor(EndCondition endCondition) {
		super(endCondition);
	}
	
	@Override
	public void doCicle() {
		this.execAPhase();
		this.execBPhase();
		this.execCPhase();
	}
}
