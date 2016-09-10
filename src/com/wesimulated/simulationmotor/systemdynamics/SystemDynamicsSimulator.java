package com.wesimulated.simulationmotor.systemdynamics;

import com.wesimulated.simulation.BaseSimulator;

public abstract class SystemDynamicsSimulator extends BaseSimulator {

	public SystemDynamicsExecutor getSystemDynamicsExecutor() {
		return (SystemDynamicsExecutor) this.getExecutor();
	}

	public void register(Module module) {
		this.getSystemDynamicsExecutor().addFlows(module.getFlows());
		this.getSystemDynamicsExecutor().addStocks(module.getStocks());
	}

	public void register(Stock stock) {
		this.getSystemDynamicsExecutor().add(stock);
	}

	public void register(Flow flow) {
		this.getSystemDynamicsExecutor().add(flow);
	}
}
