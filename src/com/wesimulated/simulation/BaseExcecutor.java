package com.wesimulated.simulation;


import javafx.application.Platform;
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

import com.wesimulated.simulation.runparameters.EndCodition;

public abstract class BaseExcecutor {
	private EndCodition endCondition;
	private Clock clock;
	
	public BaseExcecutor(EndCodition endCondition) {
		this.endCondition = endCondition;
	}

	protected abstract void doCicle() throws LogicalTimeAlreadyPassed, InvalidLogicalTime, InTimeAdvancingState, RequestForTimeRegulationPending, RequestForTimeConstrainedPending, SaveInProgress,
			RestoreInProgress, FederateNotExecutionMember, NotConnected, RTIinternalError, InterruptedException;
	
		while (!this.simulationMustStop()) {
			this.doCicle();
		}
	public void run() throws LogicalTimeAlreadyPassed, InvalidLogicalTime, InTimeAdvancingState, RequestForTimeRegulationPending, RequestForTimeConstrainedPending, SaveInProgress, RestoreInProgress,
			FederateNotExecutionMember, NotConnected, RTIinternalError, InterruptedException {
	}
	
	protected boolean simulationMustStop() {
		return endCondition.isSatisfied();
	}



	public Clock getClock() {
		return clock;
	}

	private void setClock(Clock clock) {
		this.clock = clock;
	}
}
