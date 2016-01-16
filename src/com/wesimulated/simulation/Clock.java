package com.wesimulated.simulation;

import hla.rti1516e.exceptions.FederateNotExecutionMember;
import hla.rti1516e.exceptions.InTimeAdvancingState;
import hla.rti1516e.exceptions.InvalidLogicalTime;
import hla.rti1516e.exceptions.LogicalTimeAlreadyPassed;
import hla.rti1516e.exceptions.NotConnected;
import hla.rti1516e.exceptions.RTIinternalError;
import hla.rti1516e.exceptions.RequestForTimeConstrainedPending;
import hla.rti1516e.exceptions.RequestForTimeRegulationPending;
import hla.rti1516e.exceptions.RestoreInProgress;
import hla.rti1516e.exceptions.SaveInProgress;

import java.time.Duration;
import java.util.Date;

import com.wesimulated.simulationmotor.operationbased.TimeControllerEntity;

public class Clock {
	
	private Date date;
	private Date startDate;
	private BaseExcecutor simulationMotor;
	private TimeControllerEntity timeControllerEntity;
	
	
	public Clock(Date startDate, BaseExcecutor simulationMotor, TimeControllerEntity hlaFederate) {
		this.startDate = startDate;
		this.simulationMotor = simulationMotor;
		this.timeControllerEntity = hlaFederate;
	}
	
	public boolean durationHasPassed(Duration duration) {
		return date.getTime() - startDate.getTime() > duration.toMillis();
	}
	
	public boolean hasThisDate(Date thisDate) {
		return this.date.equals(thisDate);
	}

	public void advanceUntil(Date newDate) throws InterruptedException, LogicalTimeAlreadyPassed, InvalidLogicalTime, InTimeAdvancingState, RequestForTimeRegulationPending, RequestForTimeConstrainedPending, SaveInProgress, RestoreInProgress, FederateNotExecutionMember, NotConnected, RTIinternalError {
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
