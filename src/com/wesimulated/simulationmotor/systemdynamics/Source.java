package com.wesimulated.simulationmotor.systemdynamics;

/**
 * The idea is that the source listens for some hla class and have inputs when
 * this has changes.
 * 
 * @author Carolina
 */
public class Source implements PhysicalInput {
	private Double actualValue;
	private String name;

	public Source(String name) {
		this.name = name;
		this.actualValue = 0.0;
	}

	// TODO this method will be called when an hla instance register a modification in the value mapped to this
	public void addToActualValue(Double value) {
		this.actualValue += value;
	}

	public void setActualValue(Double value) {
		this.actualValue = value;
	}

	public Double extractAll() {
		Double tempVar = this.actualValue;
		this.actualValue = 0.0;
		return tempVar;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Double getActualValue() {
		return this.actualValue;
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
