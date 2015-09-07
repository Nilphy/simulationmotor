package com.wesimulated.simulation;

import java.time.Duration;
import java.util.Date;

public class Clock {

	private static Clock instance;
	
	private Date date;
	private Date startDate;
	
	private Clock(Date startDate) {
		this.startDate = startDate;
	}
	
	private Clock() {
		this.startDate = new Date();
	}
	
	public static Clock getInstance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	}

	public boolean durationHasPassed(Duration duration) {
		return date.getTime() - startDate.getTime() > duration.toMillis();
	}

	public void advanceUntil(Date newDate) {
		// TODO HLA request time advance
		date = newDate;
	}

	public Date getCurrentDate() {
		return date;
	}
}
