package fuzzification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcessTest {
	Process process;

	@Before
	public void setUp() {
		float[][] entries = new float[2][4];
		entries[0][0] = 1;
		entries[0][1] = 2;
		entries[0][2] = 3;
		entries[0][3] = 6;
		entries[1][0] = 2;
		entries[1][1] = 3;
		entries[1][2] = 0;
		entries[1][3] = 8;
		process = new Process(1, 4, 7, 9);
		process.apply(entries);
	}

	@After
	public void tearDown() {
		process = null;
	}

	@Test
	public void test() {
		System.out.println(process.chooseColumn());
	}
	

}
