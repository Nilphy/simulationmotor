package com.wesimulated.simulationmotor.des;

public interface Prioritized extends Comparable<Prioritized> {

	public enum Priority {
		LOW(0.1f), MED(0.5f), HIGH(0.90f), HIGHEST(0.99f);

		private Float numericValue;

		private Priority(Float numericValue) {
			this.numericValue = numericValue;
		}

		static public Priority fromValue(Float numericValue) {
			if (numericValue < LOW.get()) {
				return LOW;
			}
			if (numericValue < MED.get()) {
				return MED;
			}
			if (numericValue < HIGH.get()) {
				return HIGH;
			}
			return HIGHEST;
		}

		public Float get() {
			return this.numericValue;
		}
	}

	public Float calculatePriority();

	@Override
	public default int compareTo(Prioritized o) {
		return this.calculatePriority().compareTo(o.calculatePriority());
	}
}
