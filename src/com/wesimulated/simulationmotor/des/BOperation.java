package com.wesimulated.simulationmotor.des;

import java.util.Date;

public abstract class BOperation implements Operation {

	private Operation originalOperation;

	public BOperation(Operation originalOperation) {
		this.originalOperation = originalOperation;
	}

	public abstract void doAction();

	public abstract Date getStartTime();

	public Operation getOriginalOperation() {
		return this.originalOperation;
	}

	public void setOriginalOperation(Operation operation) {
		this.originalOperation = operation;
	}
}
