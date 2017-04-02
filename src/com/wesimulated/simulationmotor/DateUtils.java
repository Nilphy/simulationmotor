package com.wesimulated.simulationmotor;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final int MILLIES_IN_MINUTE = 60000;

	public static Date convertToStartOfNextLabDay(Date date) {
		Calendar calOfDate = Calendar.getInstance();
		calOfDate.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.set(calOfDate.get(Calendar.YEAR), calOfDate.get(Calendar.MONTH), calOfDate.get(Calendar.DATE), 9, 0);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			cal.add(Calendar.DATE, 3);
		} else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			cal.add(Calendar.DATE, 2);
		} else {
			cal.add(Calendar.DATE, 1);
		}
		return cal.getTime();
	}

	public static Date addMilis(Date startDate, float millisToAdd) {
		float startPlusAddedMillis = startDate.getTime() + millisToAdd;
		return new Date((long) startPlusAddedMillis);
	}

	public static Date addOneDay(Date dayInProgress) {
		Calendar c = Calendar.getInstance();
		c.setTime(dayInProgress);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	public static int calculateDifferenceInMinutes(Date oldest, Date newest) {
		return (int) (newest.getTime() - oldest.getTime()) / MILLIES_IN_MINUTE;
	}

	public static Date calculateProportionalDateInPeriod(Date periodStart, Date periodEnd, float proportion) {
		long millisStartDate = periodStart.getTime();
		long millisEndDate = periodEnd.getTime();
		long difference = millisEndDate - millisStartDate;
		return new Date((long) (difference * proportion) + millisStartDate);
	}
}
