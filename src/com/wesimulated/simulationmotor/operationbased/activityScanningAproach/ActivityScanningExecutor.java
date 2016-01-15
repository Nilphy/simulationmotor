package com.wesimulated.simulationmotor.operationbased.activityScanningAproach;

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

import java.util.LinkedList;

import com.wesimulated.simulation.runparameters.EndCodition;
import com.wesimulated.simulationmotor.operationbased.OperationBasedExecutor;

public class ActivityScanningExecutor extends OperationBasedExecutor {

	public ActivityScanningExecutor(EndCodition endCondition) {
		super(endCondition);
		this.setCOperations(new LinkedList<>());	
	}

	@Override
	protected void doCicle() throws LogicalTimeAlreadyPassed, InvalidLogicalTime, InTimeAdvancingState, RequestForTimeRegulationPending, RequestForTimeConstrainedPending, SaveInProgress, RestoreInProgress, FederateNotExecutionMember, NotConnected, RTIinternalError, InterruptedException {
		this.execAPhase();
		this.execCPhase();
	}
}
