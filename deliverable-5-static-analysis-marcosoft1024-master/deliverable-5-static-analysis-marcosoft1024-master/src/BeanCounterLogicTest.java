import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.nasa.jpf.vm.Verify;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Code by @author Wonsun Ahn
 * 
 * <p>Uses the Java Path Finder model checking tool to check BeanCounterLogic in
 * various modes of operation. It checks BeanCounterLogic in both "luck" and
 * "skill" modes for various numbers of slots and beans. It also goes down all
 * the possible random path taken by the beans during operation.
 */

public class BeanCounterLogicTest {
	private static BeanCounterLogic logic; // The core logic of the program
	private static Bean[] beans; // The beans in the machine
	private static String failString; // A descriptive fail string for assertions

	private static int slotCount; // The number of slots in the machine we want to test
	private static int beanCount; // The number of beans in the machine we want to test
	private static boolean isLuck; // Whether the machine we want to test is in "luck" or "skill" mode

	private int getInFlightBeanCount(BeanCounterLogic logic, int slotCount) {
		int inFlight = 0;
		for (int yPos = 0; yPos < slotCount; yPos++) {
			int xPos = logic.getInFlightBeanXPos(yPos);
			if (xPos != BeanCounterLogic.NO_BEAN_IN_YPOS) {
				inFlight++;
			}
		}
		return inFlight;
	}

	private int getInSlotsBeanCount(BeanCounterLogic logic, int slotCount) {
		int inSlots = 0;
		for (int i = 0; i < slotCount; i++) {
			inSlots += logic.getSlotBeanCount(i);
		}
		return inSlots;
	}

	/**
	 * Sets up the test fixture.
	 */
	@BeforeClass
	public static void setUp() {
		if (Config.getTestType() == TestType.JUNIT) {
			slotCount = 5;
			beanCount = 3;
			isLuck = true;
		} else if (Config.getTestType() == TestType.JPF_ON_JUNIT) {
			/*
			 * TODO: Use the Java Path Finder Verify API to generate choices for slotCount,
			 * beanCount, and isLuck: slotCount should take values 1-5, beanCount should
			 * take values 0-3, and isLucky should be either true or false. For reference on
			 * how to use the Verify API, look at:
			 * https://github.com/javapathfinder/jpf-core/wiki/Verify-API-of-JPF
			 */
			slotCount = Verify.getInt(1, 5);
			beanCount = Verify.getInt(0, 3);
			isLuck = Verify.getBoolean();
		} else {
			assert (false);
		}

		// Create the internal logic
		logic = BeanCounterLogic.createInstance(slotCount);
		// Create the beans
		beans = new Bean[beanCount];
		for (int i = 0; i < beanCount; i++) {
			beans[i] = Bean.createInstance(slotCount, isLuck, new Random(42));
		}

		// A failstring useful to pass to assertions to get a more descriptive error.
		failString = "Failure in (slotCount=" + slotCount
				+ ", beanCount=" + beanCount + ", isLucky=" + isLuck + "):";
	}

	@AfterClass
	public static void tearDown() {
	}

	/**
	 * Test case for void reset(Bean[] beans).
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 * Invariants: If beanCount is greater than 0,
	 *             remaining bean count is beanCount - 1
	 *             in-flight bean count is 1 (the bean initially at the top)
	 *             in-slot bean count is 0.
	 *             If beanCount is 0,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is 0.
	 */
	@Test
	public void testReset() {
		// TODO: Implement
		logic.reset(beans);
		if (beanCount > 0) {
			assertEquals(beanCount - 1, logic.getRemainingBeanCount());
			assertEquals(1, getInFlightBeanCount(logic, slotCount));
			assertEquals(0, getInSlotsBeanCount(logic, slotCount));
		} else if (beanCount == 0) {
			assertEquals(0, logic.getRemainingBeanCount());
			assertEquals(0, getInFlightBeanCount(logic, slotCount));
			assertEquals(0, getInSlotsBeanCount(logic, slotCount));
		}
		/*
		 * Currently, it just prints out the failString to demonstrate to you all the
		 * cases considered by Java Path Finder. If you called the Verify API correctly
		 * in setUp(), you should see all combinations of machines
		 * (slotCount/beanCount/isLucky) printed here:
		 * 
		 * Failure in (slotCount=1, beanCount=0, isLucky=false):
		 * Failure in (slotCount=1, beanCount=0, isLucky=true):
		 * Failure in (slotCount=1, beanCount=1, isLucky=false):
		 * Failure in (slotCount=1, beanCount=1, isLucky=true):
		 * ...
		 * 
		 * PLEASE REMOVE when you are done implementing.
		 */
		System.out.println(failString);
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             all positions of in-flight beans are legal positions in the logical coordinate system.
	 */
	@Test
	public void testAdvanceStepCoordinates() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {
			for (int i = 0; i < slotCount; i++) {
				int temp = logic.getInFlightBeanXPos(i);
				assertTrue(failString, (temp >= 0 && temp <= i) || temp == -1);
			}
		}
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             the sum of remaining, in-flight, and in-slot beans is equal to beanCount.
	 */
	@Test
	public void testAdvanceStepBeanCount() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {
			int temp = 0;
			temp += logic.getRemainingBeanCount();
			temp += getInFlightBeanCount(logic, slotCount);
			temp += getInSlotsBeanCount(logic, slotCount);
			assertEquals(failString, temp, beanCount);
		}
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 */
	@Test
	public void testAdvanceStepPostCondition() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {

		}
		assertEquals(failString, 0, logic.getRemainingBeanCount());
		assertEquals(failString, 0, getInFlightBeanCount(logic, slotCount));
		assertEquals(failString, beanCount, getInSlotsBeanCount(logic, slotCount));
	}
	
	/**
	 * Test case for void lowerHalf()().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.lowerHalf().
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 *             After calling logic.lowerHalf(),
	 *             slots in the machine contain only the lower half of the original beans.
	 *             Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             Check each slot for the expected number of beans after having called logic.lowerHalf().
	 */
	@Test
	public void testLowerHalf() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {

		}
		assertEquals(failString, 0, logic.getRemainingBeanCount());
		assertEquals(failString, 0, getInFlightBeanCount(logic, slotCount));
		assertEquals(failString, beanCount, getInSlotsBeanCount(logic, slotCount));
		int []expectedSlotCounts = new int[slotCount];
		for (int i = 0; i < slotCount; i++) {
			expectedSlotCounts[i] = logic.getSlotBeanCount(i);
		}
		int count = beanCount / 2;
		for (int i = slotCount - 1; i >= 0; i--) {
			if (count > expectedSlotCounts[i]) {
				count -= expectedSlotCounts[i];
				expectedSlotCounts[i] = 0;
			} else {
				expectedSlotCounts[i] -= count;
				break;
			}
		}
		logic.lowerHalf();
		int []observedSlotCounts = new int[slotCount];
		for (int i = 0; i < slotCount; i++) {
			observedSlotCounts[i] = logic.getSlotBeanCount(i);
		}
		assertArrayEquals(failString, expectedSlotCounts, observedSlotCounts);
	}
	
	/**
	 * Test case for void upperHalf().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.upperHalf().
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 *             After calling logic.upperHalf(),
	 *             slots in the machine contain only the upper half of the original beans.
	 *             Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             Check each slot for the expected number of beans after having called logic.upperHalf().
	 */
	@Test
	public void testUpperHalf() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {

		}
		assertEquals(failString, 0, logic.getRemainingBeanCount());
		assertEquals(failString, 0, getInFlightBeanCount(logic, slotCount));
		assertEquals(failString, beanCount, getInSlotsBeanCount(logic, slotCount));
		int []expectedSlotCounts = new int[slotCount];
		for (int i = 0; i < slotCount; i++) {
			expectedSlotCounts[i] = logic.getSlotBeanCount(i);
		}
		int count = beanCount / 2;
		for (int i = 0; i < slotCount; i++) {
			if (count > expectedSlotCounts[i]) {
				count -= expectedSlotCounts[i];
				expectedSlotCounts[i] = 0;
			} else {
				expectedSlotCounts[i] -= count;
				break;
			}
		}
		logic.upperHalf();
		int []observedSlotCounts = new int[slotCount];
		for (int i = 0; i < slotCount; i++) {
			observedSlotCounts[i] = logic.getSlotBeanCount(i);
		}
		assertArrayEquals(failString, expectedSlotCounts, observedSlotCounts);
	}
	
	/**
	 * Test case for void repeat().
	 * Preconditions: The machine is operating in skill mode.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.repeat();
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: Bean count in each slot is identical after the first run and second run of the machine. 
	 */
	@Test
	public void testRepeat() {
		if (!isLuck) {
			// TODO: Implement
			logic.reset(beans);
			while (logic.advanceStep()) {

			}
			int []expectedSlotCounts = new int[slotCount];
			for (int i = 0; i < slotCount; i++) {
				expectedSlotCounts[i] = logic.getSlotBeanCount(i);
			}
			logic.repeat();
			while (logic.advanceStep()) {

			}
			int []observedSlotCounts = new int[slotCount];
			for (int i = 0; i < slotCount; i++) {
				observedSlotCounts[i] = logic.getSlotBeanCount(i);
			}
			assertArrayEquals(failString, expectedSlotCounts, observedSlotCounts);
		}
	}

	/**
	 * Extra Invariant Test getAverageSlotBeanCount() in luck mode.
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Store an expectedAverage, an average of the slot number for each bean.
	 *                  Store an observedAverage, the result of logic.getAverageSlotBeanCount().
	 * Invariants: Math.abs(expectedAverage - observedAverage) < 0.01.
	 */
	@Test
	public void testAverage() {
		logic.reset(beans);
		while (logic.advanceStep()) {

		}
		double expectedAverage = 0;
		for (int i = 0; i < slotCount; i++) {
			expectedAverage += i * logic.getSlotBeanCount(i);
		}
		double observedAverage = logic.getAverageSlotBeanCount();
		if (beanCount != 0) {
			expectedAverage /= beanCount;
		}
		assertTrue(failString + ". expectedAverage: " + expectedAverage + " observedAverage: "
				+ observedAverage, Math.abs(expectedAverage - observedAverage) < 0.01);
	}
}
