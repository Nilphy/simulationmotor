package com.wesimulated.simulationmotor.systemdynamics;

import java.util.ArrayList;
import java.util.Collection;

import com.wesimulated.simulation.BaseExecutor;
import com.wesimulated.simulation.runparameters.EndCodition;
import com.wesimulated.simulationmotor.systemdynamics.CoreStructureAspect.Flow;
import com.wesimulated.simulationmotor.systemdynamics.CoreStructureAspect.Stock;

public class SystemDynamicsExecutor extends BaseExecutor {
	private Collection<CoreStructureAspect> stocks;
	private Collection<CoreStructureAspect> flows;
	private long timeStep;

	public SystemDynamicsExecutor(EndCodition endCondition) {
		super(endCondition);
		this.stocks = new ArrayList<>();
		this.flows = new ArrayList<>();
	}

	@Override
	protected void doCicle() {
		this.calculate(stocks);
		this.calculate(flows);
	}

	private void calculate(Collection<CoreStructureAspect> structureAspects) {
		for (CoreStructureAspect structureAspect : structureAspects) {
			structureAspect.calculateNext(timeStep);
		}
	}

	public void addStock(Stock stock) {
		this.getStocks().add(new CoreStructureAspect(stock));
	}
	
	public void addFlow(Flow flow) {
		this.getFlows().add(new CoreStructureAspect(flow));
	}

	public Collection<CoreStructureAspect> getStocks() {
		return this.stocks;
	}

	public Collection<CoreStructureAspect> getFlows() {
		return this.flows;
	}
}
