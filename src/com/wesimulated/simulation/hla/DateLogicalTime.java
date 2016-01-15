package com.wesimulated.simulation.hla;

import hla.rti1516e.LogicalTime;
import hla.rti1516e.exceptions.CouldNotEncode;
import hla.rti1516e.exceptions.IllegalTimeArithmetic;
import hla.rti1516e.exceptions.InvalidLogicalTime;
import hla.rti1516e.exceptions.InvalidLogicalTimeInterval;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sf.ohla.rti.i18n.ExceptionMessages;
import net.sf.ohla.rti.i18n.I18n;

public class DateLogicalTime implements LogicalTime<DateLogicalTime, DateLogicalTimeInterval> {

	private static final long serialVersionUID = 3140557420447710971L;
	public static final byte ENCODED_LENGTH = Long.SIZE / 8;
	public static final long INITIAL = 0L;
	public static final long FINAL = Long.MAX_VALUE;
	
	
	private final Date date;
	
	public DateLogicalTime(Date date) {
		this.date = date;
	}
	
	@Override
	public DateLogicalTime add(DateLogicalTimeInterval timeToAdd) throws IllegalTimeArithmetic, InvalidLogicalTimeInterval {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.getValue());
		calendar.add(Calendar.MILLISECOND, Math.toIntExact(timeToAdd.getValue().toMillis()));
		return new DateLogicalTime(calendar.getTime());
	}
	
	@Override
	public DateLogicalTime subtract(DateLogicalTimeInterval timeToSubstract) throws IllegalTimeArithmetic, InvalidLogicalTimeInterval {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.getValue());
		calendar.add(Calendar.MILLISECOND, - Math.toIntExact(timeToSubstract.getValue().toMillis()));
		return new DateLogicalTime(calendar.getTime());
	}

	@Override
	public int compareTo(DateLogicalTime other) {
		return this.getValue().compareTo(other.getValue());
	}

	@Override
	public DateLogicalTimeInterval distance(DateLogicalTime other) throws InvalidLogicalTime {
		return new DateLogicalTimeInterval(Duration.between(this.getValue().toInstant(), other.getValue().toInstant()));
	}

	@Override
	public void encode(byte[] buffer, int offset) throws CouldNotEncode {
		if (buffer == null) {
			throw new IllegalArgumentException(I18n.getMessage(ExceptionMessages.ENCODE_BUFFER_IS_NULL));
	    } else if ((buffer.length - offset) < ENCODED_LENGTH) {
	    	throw new IllegalArgumentException(I18n.getMessage(ExceptionMessages.ENCODE_BUFFER_IS_TOO_SHORT, ENCODED_LENGTH, buffer.length - offset));
	    }
		long time = this.getValue().getTime();
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
	public boolean isFinal() {
		GregorianCalendar maxgc = new GregorianCalendar();
		maxgc.setTime(new Date(FINAL));
		return maxgc.equals(this.getValue());
	}

	@Override
	public boolean isInitial() {
		GregorianCalendar mingc = new GregorianCalendar();
		mingc.setTime(new Date(INITIAL));
		return mingc.equals(this.getValue());
	}

	public Date getValue() {
		return this.date;
	}
}
