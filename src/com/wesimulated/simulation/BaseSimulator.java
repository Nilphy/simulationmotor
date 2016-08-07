package com.wesimulated.simulation;

public abstract class BaseSimulator {

	private BaseExcecutor executor;

	public void startExecution() {
		this.executor.run();
	}

	public BaseExcecutor getExecutor() {
		return executor;
	}

	public void setExecutor(BaseExcecutor executor) {
		this.executor = executor;
	}
	
	public boolean isRunning() {
		return !this.getExecutor().isSimulationEnd();
	}
}
