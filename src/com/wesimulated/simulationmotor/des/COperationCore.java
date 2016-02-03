package com.wesimulated.simulationmotor.des;



class COperationCore {

	private COperation operation;
	
	COperationCore(COperation operation) {
		this.operation = operation;
	}
	
	boolean testIfRequirementsAreMet() {
		return operation.testIfRequirementsAreMet();
	}
	
	void doAction() {
		operation.doAction();
	}
	
	int getPriority() {
		return 1;
	}
	
	int getNegativePriority() {
		return - getPriority();
	}
}
