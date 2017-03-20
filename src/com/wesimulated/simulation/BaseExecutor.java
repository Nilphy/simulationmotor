package com.wesimulated.simulation;

import java.util.Date;
import java.util.concurrent.Semaphore;

import ch.qos.logback.core.util.Duration;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.TimeControllerEntity;

public abstract class BaseExecutor {
	private EndCondition endCondition;
	private Clock clock;
	private Semaphore hlaWait;

	public BaseExecutor(EndCondition endCondition) {
		this.endCondition = endCondition;
		this.hlaWait = new Semaphore(0);
	}

	protected abstract void doCicle();

	public void run() {
		new Thread(() -> {
			if (this.getClock() == null) {
				this.pauseSimulationMotorExecution();
			}
			while (!this.isSimulationEnd()) {
				this.doCicle();
			}
		}).start();
	}

	public boolean isSimulationEnd() {
		return endCondition.isSatisfied();
	}

	public void initClock(Date time, TimeControllerEntity timeControllerEntity) {
		this.setClock(new Clock(time, this, timeControllerEntity));
	}

	public void continueFromDate(Date time) {
		if (this.getClock() != null && this.getClock().getCurrentDate().compareTo(time) != 0) {
			Duration duration = Duration.buildByMilliseconds(time.getTime() - this.getClock().getCurrentDate().getTime());
			System.out.println("Setting already setted date to clock with a different value, the difference is of new - old: " + duration.toString());
		}
		this.getClock().setCurrentDate(time);
		this.continueSimulationMotorExecution();
	}

	public void pauseSimulationMotorExecution() {
		try {
			this.hlaWait.acquire();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void continueSimulationMotorExecution() {
		this.hlaWait.release();
	}

	public Clock getClock() {
		return clock;
	}

	private void setClock(Clock clock) {
		this.clock = clock;
	}
}
