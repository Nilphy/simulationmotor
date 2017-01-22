package com.wesimulated.simulationmotor.des;

import com.wesimulated.simulation.BaseSimulator;

public abstract class OperationBasedSimulator extends BaseSimulator {

	public OperationBasedSimulator(OperationBasedExecutor executor) {
		super(executor);
	}

	public OperationBasedExecutor getOperationBasedExecutor() {
		return (OperationBasedExecutor) this.getExecutor();
	}
}
