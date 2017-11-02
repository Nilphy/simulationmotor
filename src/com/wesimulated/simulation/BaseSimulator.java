package com.wesimulated.simulation;

import java.util.Date;

public abstract class BaseSimulator<T extends BaseExecutor> {

	private T executor;

	public BaseSimulator(T executor) {
		this.executor = executor;
	}

	public void startExecution() {
		this.executor.run();
	}

	public T getExecutor() {
		return executor;
	}

	public void setExecutor(T executor) {
		this.executor = executor;
	}

	public boolean isRunning() {
		return !this.getExecutor().isSimulationEnd();
	}

	public Date getCurrentDate() {
		return this.getExecutor().getClock().getCurrentDate();
	}

}
