package com.wesimulated.simulationmotor.systemdynamics;

import java.util.Date;

public class CoreStructureAspect {
	private double actualValue;
	private Date actualDate;
	private StructureAspect structureAspect;
	
	public CoreStructureAspect(StructureAspect structureAspect) {
		this.structureAspect = structureAspect;
	}

	public double calculateNext(double dt) {
		return this.structureAspect.calculateNext(this.actualValue, dt); 
	}

	public final void calculate(double dt) {
		this.actualValue = this.calculateNext(dt);
	}

	public double getActualValue() {
		return actualValue;
	}

	public Date getActualDate() {
		return actualDate;
	}

	@FunctionalInterface
	public interface StructureAspect {
		public double calculateNext(double previousValue, double dt);
	}

	@FunctionalInterface
	public interface Stock extends StructureAspect {
	}

	@FunctionalInterface
	public interface Flow extends StructureAspect {
	}
}
