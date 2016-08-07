package com.wesimulated.simulationmotor.systemdynamics;

import java.util.Collection;

import com.wesimulated.simulation.BaseExcecutor;
import com.wesimulated.simulation.runparameters.EndCodition;

public class SystemDynamicsExcecutor extends BaseExcecutor {
	private Collection<StructureAspect> stocks;
	private Collection<StructureAspect> flows;
	private long timeStep;

	public SystemDynamicsExcecutor(EndCodition endCondition) {
		super(endCondition);
	}

	@Override
	protected void doCicle() {
		this.calculate(stocks);
		this.calculate(flows);
	}

	private void calculate(Collection<StructureAspect> structureAspects) {
		for (StructureAspect structureAspect : structureAspects) {
			structureAspect.calculateNext(timeStep);
		}
	}
}
