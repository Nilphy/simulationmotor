package com.wesimulated.simulationmotor.systemdynamics;

import com.wesimulated.simulation.BaseSimulator;

public class SystemDynamicsSimulator extends BaseSimulator {

	public SystemDynamicsExecutor getOperationBasedExecutor() {
		return (SystemDynamicsExecutor) this.getExecutor();
	}
}