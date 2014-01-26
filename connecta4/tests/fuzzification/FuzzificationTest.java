package fuzzification;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FuzzificationTest {
	Fuzzification fuzzification;

	@Before
	public void setUp() {
		fuzzification = new Fuzzification(0, 4, 7, 9);
	}

	@After
	public void tearDown() {
		fuzzification = null;
	}

	@Test
	public void testF() {
		assertTrue(fuzzification.low(-1) == 1);
		assertTrue(fuzzification.low(1) == 0.75);
		assertTrue(fuzzification.low(2) == 0.5);
		assertTrue(fuzzification.low(4) == 0);
		assertTrue(fuzzification.low(7) == 0);
		assertTrue(fuzzification.low(9) == 0);
	}

	@Test
	public void testG() {
		assertTrue(fuzzification.medium(-1) == 0);
		assertTrue(fuzzification.medium(0) == 0);
		assertTrue(fuzzification.medium(1) == 0.25);
		assertTrue(fuzzification.medium(2) == 0.5);
		assertTrue(fuzzification.medium(4) == 1);
		assertTrue(fuzzification.medium(5) == 1);
		assertTrue(fuzzification.medium(6) == 1);
		assertTrue(fuzzification.medium(7) == 1);
		assertTrue(fuzzification.medium(8) == 0.5);
		assertTrue(fuzzification.medium(9) == 0);
	}

	@Test
	public void testH() {
		assertTrue(fuzzification.high(-1) == 0);
		assertTrue(fuzzification.high(4) == 0);
		assertTrue(fuzzification.high(7) == 0);
		assertTrue(fuzzification.high(8) == 0.5);
		assertTrue(fuzzification.high(9) == 1);
		assertTrue(fuzzification.high(10) == 1);
	}

}
