package com.wesimulated.simulationmotor.systemdynamics;

public class SinkFlow extends Flow {

	public SinkFlow() {
		super("SINK");
	}

	@Override
	public Double calculateNext(Double previousValue) {
		return Double.MAX_VALUE;
	}

}
