package com.wesimulated.simulationmotor.operationbased;

import com.wesimulated.simulation.Operation;


public abstract class COperation extends Operation 	{

	public abstract boolean testIfRequirementsAreMet();
	public abstract int getPriority();
	
	public int getNegativePriority() {
		return - getPriority();
	}
}
