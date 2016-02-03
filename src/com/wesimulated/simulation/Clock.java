package com.wesimulated.simulation;

import java.time.Duration;
import java.util.Date;

import com.wesimulated.simulationmotor.des.TimeControllerEntity;

public class Clock {
	
	private Date date;
	private Date startDate;
	private BaseExcecutor simulationMotor;
	private TimeControllerEntity timeControllerEntity;
	
	
	public Clock(Date startDate, BaseExcecutor simulationMotor, TimeControllerEntity hlaFederate) {
		this.startDate = startDate;
		this.setCurrentDate(startDate);
		this.simulationMotor = simulationMotor;
		this.timeControllerEntity = hlaFederate;
	}
	
	public boolean durationHasPassed(Duration duration) {
		return date.getTime() - startDate.getTime() > duration.toMillis();
	}
	
	public boolean hasThisDate(Date thisDate) {
		return this.date.equals(thisDate);
	}

	public void advanceUntil(Date newDate) {
		this.timeControllerEntity.requestTimeAdvance(newDate);
		this.simulationMotor.pauseSimulationMotorExecution();
	}

	public Date getCurrentDate() {
		return date;
	}

	public void setCurrentDate(Date date) {
		this.date = date;
	}
}
