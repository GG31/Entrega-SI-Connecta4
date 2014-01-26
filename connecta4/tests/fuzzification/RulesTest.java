package fuzzification;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RulesTest {
	Rules rules;

	@Before
	public void setUp() {
		rules = new Rules();
	}

	@After
	public void tearDown() {
		rules = null;
	}

	@Test
	public void test() {
		rules.affiche();
	}

	@Test
	public void testType() {
		assertTrue(rules.type(0.2f, 1).equals("MEDIUM"));
		assertTrue(rules.type(0, 1) == null);
		assertTrue(rules.type(0.5f, 0).equals("LOW"));
		assertTrue(rules.type(0.9f, 2).equals("HIGH"));
	}

	@Test
	public void testAnalyseRules() {
		HashMap<String, Float> rulesMin = new HashMap<>();
		rulesMin.put("HIGH HIGH", 0.2f);
		rulesMin.put("HIGH LOW", 0.75f);

		HashMap<String, Float> result = rules.analyseRules(rulesMin);
		assertTrue(result.size() == 2);
		assertTrue(result.containsKey("HIGH"));
		assertTrue(result.get("HIGH") == 0.2f);
		assertTrue(result.containsKey("MEDIUM"));
		assertTrue(result.get("MEDIUM") == 0.75f);

		assertFalse(result.containsKey("LOW"));
	}

	@Test
	public void testAnalyseRules1() {
		HashMap<String, Float> rulesMin = new HashMap<>();
		rulesMin.put("HIGH HIGH", 0.2f);
		rulesMin.put("HIGH HIGH", 0.75f);

		HashMap<String, Float> result = rules.analyseRules(rulesMin);
		assertTrue(result.size() == 1);
		assertTrue(result.containsKey("HIGH"));
		assertTrue(result.get("HIGH") == 0.75f);
		assertFalse(result.containsKey("MIDDLE"));

		assertFalse(result.containsKey("LOW"));
	}

	@Test
	public void testApplyRules() {
		float[][] entriesFuzzificated = new float[2][3];
		entriesFuzzificated[0][0] = 0;
		entriesFuzzificated[0][1] = 0.9f;
		entriesFuzzificated[0][2] = 0;
		entriesFuzzificated[1][0] = 0.1f;
		entriesFuzzificated[1][1] = 0.4f;
		entriesFuzzificated[1][2] = 0;

		HashMap<String, Float> result = rules.applyRules(entriesFuzzificated);
		assertTrue(result.size() == 2);
		assertTrue(result.containsKey("MEDIUM"));
		assertTrue(result.get("MEDIUM") == 0.4f);
	}
	
	@Test
	public void testApplyRules1() {
		float[][] entriesFuzzificated = new float[2][3];
		entriesFuzzificated[0][0] = 0.2f;
		entriesFuzzificated[0][1] = 0.9f;
		entriesFuzzificated[0][2] = 0.7f;
		entriesFuzzificated[1][0] = 0;
		entriesFuzzificated[1][1] = 0.4f;
		entriesFuzzificated[1][2] = 0.1f;

		HashMap<String, Float> result = rules.applyRules(entriesFuzzificated);
		assertTrue(result.size() == 2);
		assertTrue(result.containsKey("MEDIUM"));
		assertTrue(result.get("MEDIUM") == 0.4f);
		assertTrue(result.containsKey("HIGH"));
		assertTrue(result.get("HIGH") == 0.1f);
	}
}