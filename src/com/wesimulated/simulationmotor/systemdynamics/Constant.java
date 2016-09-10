package com.wesimulated.simulationmotor.systemdynamics;

public class Constant implements Input {

	private Double value;
	private String name;

	public Constant(String name, Double value) {
		this.name = name;
		this.value = value;
	}

	public Double getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Double getActualValue() {
		return this.getValue();
	}
}
