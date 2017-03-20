package com.wesimulated.simulationmotor.systemdynamics;

/**
 * Constant used in system dynamic models to use in flow equations
 *
 * @author Carolina
 *
 */
public class Constant implements Input {

	private String name;
	private VariableValue variableValue;

	public Constant(String name, VariableValue value) {
		this.name = name;
		this.variableValue = value;
	}

	public Double getValue() {
		return this.variableValue.findValue();
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Double getActualValue() {
		return this.getValue();
	}
}
