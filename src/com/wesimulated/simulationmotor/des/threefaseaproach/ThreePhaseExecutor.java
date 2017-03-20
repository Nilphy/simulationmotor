package com.wesimulated.simulationmotor.des.threefaseaproach;

import java.util.LinkedList;
import java.util.TreeSet;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

public class ThreePhaseExecutor extends OperationBasedExecutor {

	public ThreePhaseExecutor(EndCondition endCondition) {
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
