package com.wesimulated.simulationmotor.systemdynamics;

import com.wesimulated.simulation.BaseSimulator;

public abstract class SystemDynamicsSimulator extends BaseSimulator {

	public SystemDynamicsExecutor getSystemDynamicsExecutor() {
		return (SystemDynamicsExecutor) this.getExecutor();
	}
}