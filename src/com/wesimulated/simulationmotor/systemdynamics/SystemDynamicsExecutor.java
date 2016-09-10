package com.wesimulated.simulationmotor.systemdynamics;

import java.util.ArrayList;
import java.util.Collection;

import com.wesimulated.simulation.BaseExecutor;
import com.wesimulated.simulation.runparameters.EndCodition;

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
		this.calculate(this.getStocks());
		this.calculate(this.getFlows());
	}

	private void calculate(Collection<CoreStructureAspect> structureAspects) {
		for (CoreStructureAspect structureAspect : structureAspects) {
			structureAspect.calculate(this.getTimeStep());
		}
	}

	private double getTimeStep() {
		return this.timeStep;
	}

	public void add(Stock stock) {
		this.getStocks().add(stock);
	}

	public void add(Flow flow) {
		this.getFlows().add(flow);
	}

	public Collection<CoreStructureAspect> getStocks() {
		return this.stocks;
	}

	public Collection<CoreStructureAspect> getFlows() {
		return this.flows;
	}

	public void addStocks(Collection<Stock> stocks) {
		this.stocks.addAll(stocks);
	}

	public void addFlows(Collection<Flow> flows) {
		this.flows.addAll(flows);
	}

}
