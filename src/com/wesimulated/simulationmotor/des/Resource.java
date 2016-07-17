package com.wesimulated.simulationmotor.des;

public interface Resource {

	public boolean isAvailable(TaskWithPriority taskWithPriority);
	public void setAvailable(boolean available);
}
