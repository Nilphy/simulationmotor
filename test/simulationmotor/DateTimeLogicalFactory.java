package simulationmotor;

import static org.junit.Assert.assertTrue;
import hla.rti1516e.exceptions.CouldNotDecode;
import hla.rti1516e.exceptions.CouldNotEncode;

import org.junit.Test;

import com.wesimulated.simulation.hla.DateLogicalTime;
import com.wesimulated.simulation.hla.DateLogicalTimeFactory;
import com.wesimulated.simulation.hla.DateLogicalTimeInterval;

public class DateTimeLogicalFactory {

	@Test
	public void test() {
		DateLogicalTimeFactory logicalTimeFactory = new DateLogicalTimeFactory();
		boolean validated = false;
		try {
			DateLogicalTime initialTime = logicalTimeFactory.makeInitial();
			byte[] buffer = new byte[initialTime.encodedLength()];
			initialTime.encode(buffer, 0);
			if (validated = initialTime.equals(logicalTimeFactory.decodeTime(buffer, 0))) {
				DateLogicalTimeInterval zero = logicalTimeFactory.makeZero();
				buffer = new byte[zero.encodedLength()];
				zero.encode(buffer, 0);
				validated = zero.equals(logicalTimeFactory.decodeInterval(buffer, 0));
			}
		} catch (CouldNotDecode | CouldNotEncode e) {
			System.out.println(e.getMessage());
			validated = false;
		}
		assertTrue(validated);
	}
}
