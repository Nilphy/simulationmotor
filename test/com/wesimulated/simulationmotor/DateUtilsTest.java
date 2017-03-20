package com.wesimulated.simulationmotor;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void differenceInMinutesTest() {
		Date now = new Date();
		Date later = new Date();
		later.setTime(now.getTime() + 3 * DateUtils.MILLIES_IN_MINUTE);
		Assert.assertSame(3, DateUtils.calculateDifferenceInMinutes(now, later));
	}

}
