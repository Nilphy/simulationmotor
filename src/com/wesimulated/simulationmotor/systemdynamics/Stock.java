package com.wesimulated.simulationmotor.systemdynamics;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Stock extends CoreStructureAspect implements PhysicalInput {
	private Map<String, Flow> inputFlows;
	private Map<String, Flow> outputFlows;

	public Stock(String name) {
		super(name);
		this.inputFlows = new HashMap<>();
		this.outputFlows = new HashMap<>();
	}

	@Override
	public Double calculateNext(Double previousValue) {
		Double totalInput = inputFlows.values().stream().mapToDouble((flow) -> {
			return flow.getActualValue();
		}).sum();
		Double totalOutput = outputFlows.values().stream().mapToDouble((flow) -> {
			return flow.getActualValue();
		}).sum();
		Stream.concat(inputFlows.values().stream(), outputFlows.values().stream()).forEach((flow) -> {
			flow.consumeActualFromInput();
		});
		return previousValue + totalInput - totalOutput;
	}

	public void connectInputFlow(Flow flow) {
		this.inputFlows.put(flow.getName(), flow);
	}

	public void connectOutputFlow(Flow flow) {
		this.outputFlows.put(flow.getName(), flow);
	}

	@Override
	public void extract(Double value) {
		if (this.getActualValue() > value) {
			this.setActualValue(this.getActualValue() - value);
		} else {
			this.setActualValue(0.0);
		}
	}
}
