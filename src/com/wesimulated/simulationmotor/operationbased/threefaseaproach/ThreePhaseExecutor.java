package com.wesimulated.simulationmotor.operationbased.threefaseaproach;

import java.util.LinkedList;
import java.util.TreeSet;

import com.wesimulated.simulation.runparameters.EndCodition;
import com.wesimulated.simulationmotor.operationbased.OperationBasedExecutor;

public class ThreePhaseExecutor extends OperationBasedExecutor {

	public ThreePhaseExecutor(EndCodition endCondition) {
		super(endCondition);
		this.setBOperations(new TreeSet<>());
		this.setCOperations(new LinkedList<>());
	}
	
	@Override
	public void doCicle() {
		this.execAPhase();
		this.execBPhase();
		this.execCPhase();
	}
}
