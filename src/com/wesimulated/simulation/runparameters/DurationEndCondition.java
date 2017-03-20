package com.wesimulated.simulation.runparameters;

import java.time.Duration;

import com.wesimulated.simulation.Clock;

public class DurationEndCondition extends EndCondition {

	private Duration duration;
	private Clock clock;

	public DurationEndCondition(Duration duration, Clock clock) {
		this.duration = duration;
		this.clock = clock;
	}
	
	@Override
	public boolean isSatisfied() {
		return this.clock.durationHasPassed(duration);
	}

}
