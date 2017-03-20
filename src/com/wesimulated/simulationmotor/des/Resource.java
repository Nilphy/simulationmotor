package com.wesimulated.simulationmotor.des;

public interface Resource {

	public boolean isAvailable(Prioritized taskWithPriority);
	public void setAvailable(boolean available);
}
