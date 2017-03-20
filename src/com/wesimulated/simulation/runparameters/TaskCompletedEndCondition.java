package com.wesimulated.simulation.runparameters;

public class TaskCompletedEndCondition extends EndCondition {

	private CompletableTask completableTask;

	public TaskCompletedEndCondition(CompletableTask completableTask) {
		this.completableTask = completableTask;
	}
	
	@Override
	public boolean isSatisfied() {
		return this.completableTask.isCompleted();
	}
}
