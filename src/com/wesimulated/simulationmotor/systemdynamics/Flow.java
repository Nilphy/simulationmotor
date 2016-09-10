package com.wesimulated.simulationmotor.systemdynamics;

import java.util.HashMap;
import java.util.Map;

public abstract class Flow extends CoreStructureAspect {
	private Map<String, Input> connectedInputs;
	private Map<String, PhysicalInput> connectedFisicalInputs;

	public Flow(String name) {
		super(name);
		this.connectedInputs = new HashMap<>();
	}

	public void connectInput(Input input) {
		this.connectedInputs.put(input.getName(), input);
	}
	
	public void connectInput(PhysicalInput input) {
		this.connectedInputs.put(input.getName(), input);
		this.connectedFisicalInputs.put(input.getName(), input);
	}

	public double v(String name) {
		return this.connectedInputs.get(name).getActualValue();
	}

	public void consumeActualFromInput() {
		connectedFisicalInputs.values().forEach((input) -> {
			input.extract(this.getActualValue());
		});
	}
}
