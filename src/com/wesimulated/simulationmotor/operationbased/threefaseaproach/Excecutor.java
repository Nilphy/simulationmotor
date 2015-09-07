package com.wesimulated.simulationmotor.operationbased.threefaseaproach;

import java.util.LinkedList;
import java.util.TreeSet;

import com.wesimulated.simulation.runparameters.EndCodition;

public class Excecutor extends com.wesimulated.simulationmotor.operationbased.Excecutor {

	public Excecutor(EndCodition endCondition) {
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
