package com.wesimulated.simulationmotor.des;

import java.util.Date;

class BOperationCore implements Comparable<BOperationCore> {
	
	private BOperation operation;

	public BOperationCore(BOperation bOperation) {
		this.operation = bOperation;
	}

	void doAction() {
		this.operation.doAction();
	}
	
	Date getStartTime() {
		return this.operation.getStartTime();
	}

	@Override
	public int compareTo(BOperationCore otherOperation) {
		return this.getStartTime().compareTo(otherOperation.getStartTime());
	}
}
