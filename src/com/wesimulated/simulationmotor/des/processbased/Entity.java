package com.wesimulated.simulationmotor.des.processbased;

import java.util.Date;

public abstract class Entity {

	private Date reActivationTime;

	public Date getReActivationTime() {
		return reActivationTime;
	}

	public void setReActivationTime(Date reActivationTime) {
		this.reActivationTime = reActivationTime;
	}

	public void process() {
		this.reActivationTime = null;
		this.reActivationTime = this.doProcess();
	}

	protected abstract Date doProcess();
	public abstract void acceptInterruption(Date interruptionDate);
	
	public boolean isUnconditionalyDelayed() {
		return this.reActivationTime != null;
	}
}
