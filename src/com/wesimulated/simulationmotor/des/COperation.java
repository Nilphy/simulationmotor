package com.wesimulated.simulationmotor.des;

import java.util.Date;

public abstract class COperation implements Operation {

	public abstract boolean testIfRequirementsAreMet();

	public abstract void doAction();

	public abstract Date getDateOfOccurrence(Date actualDate);

	public boolean dateOfOccurrenceIsBetween(Date actualDate, Date nextDate) {
		if (this.getDateOfOccurrence(actualDate) == null) {
			return false;
		}
		if (nextDate == null) {
			return true;
		}
		if (this.getDateOfOccurrence(actualDate).before(actualDate) && this.getDateOfOccurrence(actualDate).after(nextDate)) {
			return true;
		}
		return false;
	}
}
