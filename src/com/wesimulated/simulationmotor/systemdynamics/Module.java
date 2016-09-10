package com.wesimulated.simulationmotor.systemdynamics;

import java.util.Collection;

public interface Module {

	public Collection<Flow> getFlows();

	public Collection<Stock> getStocks();
}
