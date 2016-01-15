package com.wesimulated.simulationmotor.processbased;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.TreeSet;

import com.wesimulated.simulation.BaseExcecutor;
import com.wesimulated.simulation.Clock;
import com.wesimulated.simulation.runparameters.EndCodition;


public class Excecutor extends BaseExcecutor {
	private Collection<Entity> futureEventsList;
	private Collection<Entity> currentEventsList;
	private Clock clock;
	
	public Excecutor(EndCodition endCondition) {
		super(endCondition);
		this.futureEventsList = new TreeSet<Entity>();
		this.currentEventsList = new LinkedList<Entity>();
	}

	@Override
	protected void doCicle() {
		Date nextEventTime = this.futureEventsScan();
		this.moveBetweenLists(nextEventTime);
		this.currentEventsScan();
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

	private Date futureEventsScan() {
		Date nextEventTime = futureEventsList.iterator().next().getReActivationTime();
		this.clock.advanceUntil(nextEventTime);
		return nextEventTime;
	}
}	
