package com.wesimulated.simulation.runparameters;

public class TaskCompletedEndCondition extends EndCondition {

	private Completable completableTask;

	public TaskCompletedEndCondition(Completable completableTask) {
		this.completableTask = completableTask;
	}
	
	@Override
	public boolean isSatisfied() {
		return this.completableTask.isCompleted();
	}
}
