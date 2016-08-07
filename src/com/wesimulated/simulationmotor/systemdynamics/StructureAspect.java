package com.wesimulated.simulationmotor.systemdynamics;

import java.util.Date;

public abstract class StructureAspect {
	private double actualValue;
	private Date actualDate;
	

	public abstract double calculateNext(double dt);
	

	public final void calculate(double dt) {
		this.actualValue = this.calculateNext(dt);
	}

	public double getActualValue() {
		return actualValue;
	}

	public Date getActualDate() {
		return actualDate;
	}
}
