package com.wesimulated.simulationmotor.systemdynamics;

import com.wesimulated.simulation.BaseSimulator;

public abstract class SystemDynamicsSimulator extends BaseSimulator<SystemDynamicsExecutor> {

	public SystemDynamicsSimulator(SystemDynamicsExecutor executor) {
		super(executor);
	}

	public void register(Module module) {
		this.getExecutor().addFlows(module.getFlows());
		this.getExecutor().addStocks(module.getStocks());
	}

	public void register(Stock stock) {
		this.getExecutor().add(stock);
	}

	public void register(Flow flow) {
		this.getExecutor().add(flow);
	}
}
