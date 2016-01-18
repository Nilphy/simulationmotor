package com.wesimulated.simulation;

import java.util.concurrent.Semaphore;

import com.wesimulated.simulation.hla.DateLogicalTime;
import com.wesimulated.simulation.runparameters.EndCodition;
import com.wesimulated.simulationmotor.operationbased.TimeControllerEntity;

public abstract class BaseExcecutor {
	private EndCodition endCondition;
	private Clock clock;
	private Semaphore hlaWait;

	public BaseExcecutor(EndCodition endCondition) {
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

	public void initClock(DateLogicalTime time, TimeControllerEntity timeControllerEntity) {
		this.setClock(new Clock(time.getValue(), this, timeControllerEntity));
	}

	public void continueFromDate(DateLogicalTime time) {
		if (this.getClock() != null && this.getClock().getCurrentDate().compareTo(time.getValue()) != 0) {
			System.out.println("Seting already setted date to clock with a different value, the difference is of new - old: "
					+ (time.getValue().getTime() - this.getClock().getCurrentDate().getTime()));
		}
		this.getClock().setCurrentDate(time.getValue());
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
