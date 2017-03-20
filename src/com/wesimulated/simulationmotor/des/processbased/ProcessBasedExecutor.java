package com.wesimulated.simulationmotor.des.processbased;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.TreeSet;

import com.wesimulated.simulation.BaseExecutor;
import com.wesimulated.simulation.Clock;
import com.wesimulated.simulation.runparameters.EndCondition;

public class ProcessBasedExecutor extends BaseExecutor {

	private Collection<Entity> futureEventsList;
	private Collection<Entity> currentEventsList;
	private Clock clock;

	public ProcessBasedExecutor(EndCondition endCondition) {
		super(endCondition);
		this.futureEventsList = new TreeSet<Entity>();
		this.currentEventsList = new LinkedList<Entity>();
	}

	@Override
	protected void doCicle() {
		try {
			Date nextEventTime = this.futureEventsScan();
			this.moveBetweenLists(nextEventTime);
			this.currentEventsScan();
		} catch (LogicalTimeAlreadyPassed | InvalidLogicalTime | InTimeAdvancingState | RequestForTimeRegulationPending | RequestForTimeConstrainedPending | SaveInProgress | RestoreInProgress
				| FederateNotExecutionMember | NotConnected | RTIinternalError | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void currentEventsScan() {
		if (currentEventsList.isEmpty()) return;
		Collection<Entity> entitiesToRemove = new ArrayList<>();
		for (Entity entity : currentEventsList) {
			entity.process();
			if (entity.isUnconditionalyDelayed()) {
				this.futureEventsList.add(entity);
				entitiesToRemove.add(entity);
			}
		}
		for (Entity entity : entitiesToRemove) {
			this.currentEventsList.remove(entity);
		}
		this.currentEventsScan();
	}

	private void moveBetweenLists(Date nextEventTime) {
		Collection<Entity> entitiesToRemove = new ArrayList<>();
		for (Entity entity : futureEventsList) {
			int comparisonResult = entity.getReActivationTime().compareTo(nextEventTime);
			if (comparisonResult == 0) {
				entitiesToRemove.add(entity);
				currentEventsList.add(entity);
			} else if (comparisonResult > 0) {
				break;
			}
		}
		for (Entity entity : entitiesToRemove) {
			this.futureEventsList.remove(entity);
		}
	}

	private Date futureEventsScan() throws LogicalTimeAlreadyPassed, InvalidLogicalTime, InTimeAdvancingState, RequestForTimeRegulationPending, RequestForTimeConstrainedPending, SaveInProgress, RestoreInProgress, FederateNotExecutionMember, NotConnected, RTIinternalError, InterruptedException {
		Date nextEventTime = futureEventsList.iterator().next().getReActivationTime();
		this.clock.advanceUntil(nextEventTime);
		return nextEventTime;
	}
}	
