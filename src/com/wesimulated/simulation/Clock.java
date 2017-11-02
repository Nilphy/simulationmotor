package com.wesimulated.simulation;

import java.time.Duration;
import java.util.Date;

import com.wesimulated.simulationmotor.des.TimeControllerEntity;

public class Clock {

	private Date date;
	private Date startDate;
	private BaseExecutor simulationExecutor;
	private TimeControllerEntity timeControllerEntity;

	public Clock(Date startDate, BaseExecutor simulationExecutor, TimeControllerEntity timeControllerEntity) {
		this.startDate = startDate;
		this.setCurrentDate(startDate);
		this.simulationExecutor = simulationExecutor;
		this.timeControllerEntity = timeControllerEntity;
	}

	public boolean durationHasPassed(Duration duration) {
		return date.getTime() - startDate.getTime() > duration.toMillis();
	}

	public boolean hasThisDate(Date thisDate) {
		return this.date.equals(thisDate);
	}

	public boolean dateHasPassed(Date date) {
		return this.date.compareTo(date) > 0;
	}

	public void advanceUntil(Date newDate) {
		this.timeControllerEntity.requestTimeAdvance(newDate);
		this.simulationExecutor.pauseSimulationMotorExecution();
	}

	public Date getCurrentDate() {
		return (Date) date.clone();
	}

	public void setCurrentDate(Date date) {
		this.date = date;
	}
}
