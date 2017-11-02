package com.wesimulated.simulationmotor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final int MILLIES_IN_MINUTE = 1000 * 60;
	private static final int MILLIES_IN_DAY = MILLIES_IN_MINUTE * 60 * 24;
	private static int START_HOUR = 9;
	private static int START_MINUTE = 0;
	private static int END_HOUR = 18;
	private static int END_MINUTE = 0;

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

	public static int calculateDifferenceInDays(Date oldest, Date newest) {
		return (int) (newest.getTime() - oldest.getTime()) / MILLIES_IN_DAY;
	}

	public static Date calculateProportionalDateInPeriod(Date periodStart, Date periodEnd, float proportion) {
		long millisStartDate = periodStart.getTime();
		long millisEndDate = periodEnd.getTime();
		long difference = millisEndDate - millisStartDate;
		return new Date((long) (difference * proportion) + millisStartDate);
	}

	public static Date findEndOfLaboralDay(Date actualDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(actualDate);
		calendar.set(Calendar.HOUR_OF_DAY, END_HOUR);
		calendar.set(Calendar.MINUTE, END_MINUTE);
		return calendar.getTime();
	}

	public static Date findStartOfNextLaboralDay(Date actualDate) {
		Date tomorrow = addOneDay(actualDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tomorrow);
		calendar.set(Calendar.HOUR_OF_DAY, START_HOUR);
		calendar.set(Calendar.MINUTE, START_MINUTE);
		return calendar.getTime();
	}

	public static void changeStartHour(int hour, int minute) {
		START_HOUR = hour;
		START_MINUTE = minute;
	}

	public static void changeEndHour(int hour, int minute) {
		END_HOUR = hour;
		END_MINUTE = minute;
	}

	public static String asLog(Date date) {
		Date logDate = date;
		if (date == null) {
			logDate = new Date();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  hh:mm");
		return formatter.format(logDate);
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
