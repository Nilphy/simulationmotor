package com.wesimulated.simulation.hla;

import hla.rti1516e.LogicalTimeInterval;
import hla.rti1516e.exceptions.CouldNotEncode;
import hla.rti1516e.exceptions.IllegalTimeArithmetic;
import hla.rti1516e.exceptions.InvalidLogicalTimeInterval;

import java.time.Duration;

import net.sf.ohla.rti.i18n.ExceptionMessages;
import net.sf.ohla.rti.i18n.I18n;

public class DateLogicalTimeInterval implements LogicalTimeInterval<DateLogicalTimeInterval> {

	private static final long serialVersionUID = 7007476491020372272L;
	public static final byte ENCODED_LENGTH = Long.SIZE / 8; 
	  
	private final Duration value;

	public DateLogicalTimeInterval(Duration initialValue) {
		value = initialValue;
	}
	
	public Duration getValue() {
		return value;
	}
	
	@Override
	public DateLogicalTimeInterval add(DateLogicalTimeInterval intervalToAdd) throws IllegalTimeArithmetic, InvalidLogicalTimeInterval {
		return new DateLogicalTimeInterval(value.plus(intervalToAdd.getValue()));
	}
	
	@Override
	public DateLogicalTimeInterval subtract(DateLogicalTimeInterval intervalToSubstract) throws IllegalTimeArithmetic, InvalidLogicalTimeInterval {
		return new DateLogicalTimeInterval(value.minus(intervalToSubstract.getValue()));
	}

	@Override
	public int compareTo(DateLogicalTimeInterval other) {
		return value.compareTo(other.getValue());
	}

	@Override
	public void encode(byte[] buffer, int offset) throws CouldNotEncode {
		if (buffer == null) {
			throw new IllegalArgumentException(I18n.getMessage(ExceptionMessages.ENCODE_BUFFER_IS_NULL));
	    } else if ((buffer.length - offset) < ENCODED_LENGTH) {
	    	throw new IllegalArgumentException(I18n.getMessage(ExceptionMessages.ENCODE_BUFFER_IS_TOO_SHORT, ENCODED_LENGTH, buffer.length - offset));
	    }
		long time = this.getValue().toMillis();
		buffer[offset++] = (byte) (time >>> 56);
	    buffer[offset++] = (byte) (time >>> 48);
	    buffer[offset++] = (byte) (time >>> 40);
	    buffer[offset++] = (byte) (time >>> 32);
	    buffer[offset++] = (byte) (time >>> 24);
	    buffer[offset++] = (byte) (time >>> 16);
	    buffer[offset++] = (byte) (time >>> 8);
	    buffer[offset] = (byte) time;
	}

	@Override
	public int encodedLength() {
		return ENCODED_LENGTH;
	}

	@Override
	public boolean isEpsilon() {
		return value.toMillis() == 1;
	}

	@Override
	public boolean isZero() {
		return value.isZero();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateLogicalTimeInterval other = (DateLogicalTimeInterval) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
