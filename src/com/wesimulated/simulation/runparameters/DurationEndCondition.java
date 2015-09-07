package com.wesimulated.simulation.runparameters;

import java.time.Duration;

import com.wesimulated.simulation.Clock;

public class DurationEndCondition extends EndCodition {

	private Duration duration;

	public DurationEndCondition(Duration duration) {
		this.duration = duration;
	}
	
	@Override
	public boolean isSatisfied() {
		return Clock.getInstance().durationHasPassed(duration);
	}

}
