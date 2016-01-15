package com.wesimulated.simulation;

import com.wesimulated.simulation.runparameters.EndCodition;

public abstract class BaseExcecutor {
	private EndCodition endCondition;
	private Clock clock;
	
	public BaseExcecutor(EndCodition endCondition) {
		this.endCondition = endCondition;
	}

	protected abstract void doCicle();
	
	public void run() {
		while (!this.simulationMustStop()) {
			this.doCicle();
		}
	}
	
	protected boolean simulationMustStop() {
		return endCondition.isSatisfied();
	}



	public Clock getClock() {
		return clock;
	}

	private void setClock(Clock clock) {
		this.clock = clock;
	}
}
