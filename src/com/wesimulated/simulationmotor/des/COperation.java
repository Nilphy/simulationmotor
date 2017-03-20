package com.wesimulated.simulationmotor.des;

import java.util.Date;


public abstract class COperation {

	public abstract boolean testIfRequirementsAreMet();

	public abstract void doAction();

	public abstract Date getDateOfOccurrence();

	public boolean dateOfOccurrenceIsBetween(Date actualDate, Date nextDate) {
		if (this.getDateOfOccurrence() == null) {
			return false;
		}
		if (nextDate == null) {
			return true;
		}
		if (this.getDateOfOccurrence().before(actualDate) && this.getDateOfOccurrence().after(nextDate)) {
			return true;
		}
		return false;
	};
}
