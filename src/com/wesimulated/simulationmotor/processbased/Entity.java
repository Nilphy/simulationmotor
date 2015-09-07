package com.wesimulated.simulationmotor.processbased;

import java.util.Date;

public abstract class Entity implements Comparable<Entity> {

	private Date reActivationTime;
	
	@Override
	public int compareTo(Entity o) {
		return this.getReActivationTime().compareTo(o.getReActivationTime());
	}

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

	public boolean isUnconditionalyDelayed() {
		return this.reActivationTime != null;
	}
}
