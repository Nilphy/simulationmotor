package com.wesimulated.simulationmotor.systemdynamics;

import java.util.Date;

public abstract class CoreStructureAspect {
	private Double actualValue;
	private Date actualDate;
	private String name;

	public CoreStructureAspect(String name) {
		this.name = name;
	}

	public final void calculate(Double dt) {
		this.actualValue = this.calculateNext(this.actualValue) * dt;
	}

	public abstract Double calculateNext(Double previousValue);

	public Double getActualValue() {
		return this.actualValue;
	}

	public void setActualValue(Double value) {
		this.actualValue = value;
	}

	public Date getActualDate() {
		return this.actualDate;
	}

	public String getName() {
		return this.name;
	}
}
