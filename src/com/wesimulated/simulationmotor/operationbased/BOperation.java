package com.wesimulated.simulationmotor.operationbased;

import java.util.Date;

import com.wesimulated.simulation.Operation;

public abstract class BOperation extends Operation implements Comparable<BOperation> {
	
	private Date startTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Override
	public int compareTo(BOperation otherOperation) {
		return this.getStartTime().compareTo(otherOperation.getStartTime());
	}
}
