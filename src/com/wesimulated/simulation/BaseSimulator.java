package com.wesimulated.simulation;

public abstract class BaseSimulator {

	private BaseExecutor executor;

	public void startExecution() {
		this.executor.run();
	}

	public BaseExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(BaseExecutor executor) {
		this.executor = executor;
	}
	
	public boolean isRunning() {
		return !this.getExecutor().isSimulationEnd();
	}
}
