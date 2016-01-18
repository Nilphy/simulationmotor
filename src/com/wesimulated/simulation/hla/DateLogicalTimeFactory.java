package com.wesimulated.simulation.hla;

import hla.rti1516e.LogicalTimeFactory;
import hla.rti1516e.exceptions.CouldNotDecode;

import java.time.Duration;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sf.ohla.rti.hla.rti.Integer64Time;
import net.sf.ohla.rti.i18n.ExceptionMessages;
import net.sf.ohla.rti.i18n.I18n;

public class DateLogicalTimeFactory implements LogicalTimeFactory<DateLogicalTime, DateLogicalTimeInterval> {

	private static final long serialVersionUID = -8195547672920977871L;
	public final static String NAME = "DateLogicalTime";

	@Override
	public DateLogicalTimeInterval decodeInterval(byte[] buffer, int offset) throws CouldNotDecode {
		return new DateLogicalTimeInterval(Duration.ofMillis(this.decodeMillisecondsValue(buffer, offset)));
	}

	@Override
	public DateLogicalTime decodeTime(byte[] buffer, int offset) throws CouldNotDecode {
		return new DateLogicalTime(new Date(this.decodeMillisecondsValue(buffer, offset)));
	}

	private long decodeMillisecondsValue(byte[] buffer, int offset) {
		if (buffer == null) {
			throw new IllegalArgumentException(I18n.getMessage(ExceptionMessages.DECODE_BUFFER_IS_NULL));
		} else if ((buffer.length - offset) < Integer64Time.ENCODED_LENGTH) {
			throw new IllegalArgumentException(I18n.getMessage(ExceptionMessages.DECODE_BUFFER_IS_TOO_SHORT, Integer64Time.ENCODED_LENGTH, buffer.length - offset));
		}
		long l = ((long) buffer[offset++] & 0xFF) << 56 | 
				((long) buffer[offset++] & 0xFF) << 48 | 
				((long) buffer[offset++] & 0xFF) << 40 | 
				((long) buffer[offset++] & 0xFF) << 32 |
				((long) buffer[offset++] & 0xFF) << 24 | 
				((long) buffer[offset++] & 0xFF) << 16 | 
				((long) buffer[offset++] & 0xFF) << 8 | 
				((long) buffer[offset] & 0xFF);
		return l;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public DateLogicalTimeInterval makeEpsilon() {
		return new DateLogicalTimeInterval(Duration.ofMillis(1));
	}

	@Override
	public DateLogicalTime makeFinal() {
		GregorianCalendar maxgc = new GregorianCalendar();
		maxgc.setTime(new Date(DateLogicalTime.FINAL));
		return new DateLogicalTime(maxgc.getTime());
	}

	@Override
	public DateLogicalTime makeInitial() {
		GregorianCalendar mingc = new GregorianCalendar();
		mingc.setTime(new Date(DateLogicalTime.INITIAL));
		return new DateLogicalTime(mingc.getTime());
	}

	@Override
	public DateLogicalTimeInterval makeZero() {
		return new DateLogicalTimeInterval(Duration.ZERO);
	}
}
