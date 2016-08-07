package com.wesimulated.simulationmotor.des;

import com.wesimulated.simulation.BaseSimulator;

public class OperationBasedSimulator extends BaseSimulator {

	public OperationBasedExecutor getOperationBasedExecutor() {
		return (OperationBasedExecutor) this.getExecutor();
	}
}