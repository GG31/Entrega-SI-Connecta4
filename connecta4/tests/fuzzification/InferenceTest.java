package fuzzification;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import polygon.Polygon;

public class InferenceTest {
	Inference inference;
	int max = 15;

	@Before
	public void setUp() {
		ArrayList<float[][]> entries = new ArrayList<>();
		float[][] entriesFuzzificated = new float[2][3];
		entriesFuzzificated[0][0] = 0.2f;
		entriesFuzzificated[0][1] = 0.9f;
		entriesFuzzificated[0][2] = 0.7f;
		entriesFuzzificated[1][0] = 0;
		entriesFuzzificated[1][1] = 0.4f;
		entriesFuzzificated[1][2] = 0.1f;
		entries.add(entriesFuzzificated);
		inference = new Inference(1, 4, 7, 9);
		inference.apply(entries);
	}

	@After
	public void tearDown() {
		inference = null;
	}

	@Test
	public void testAgregationLow1() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.2f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 5);
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 3.4f);
		assertTrue(polygon.getPoints()[3].getX() == 4);
		assertTrue(polygon.getPoints()[4].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.2f);
		assertTrue(polygon.getPoints()[2].getY() == 0.2f);
		assertTrue(polygon.getPoints()[3].getY() == 0);
		assertTrue(polygon.getPoints()[4].getY() == 0);
	}
	
	@Test
	public void testAgregationLowMedium1() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.2f);
		liste.put("MEDIUM", 0.1f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 7);
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 3.4f);
		assertTrue(polygon.getPoints()[3].getX() == 3.7f);
		assertTrue(polygon.getPoints()[4].getX() == 8.8f);
		assertTrue(polygon.getPoints()[5].getX() == 9);
		assertTrue(polygon.getPoints()[6].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.2f);
		assertTrue(polygon.getPoints()[2].getY() == 0.2f);
		assertTrue(polygon.getPoints()[3].getY() == 0.1f);
		assertTrue(polygon.getPoints()[4].getY() == 0.1f);
		assertTrue(polygon.getPoints()[5].getY() == 0);
		assertTrue(polygon.getPoints()[6].getY() == 0);
	}
	
	@Test
	public void testAgregationLowMedium2() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.75f);
		liste.put("MEDIUM", 0.61f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 8);
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 1.75f);
		assertTrue(polygon.getPoints()[3].getX() == 2.5f);
		assertTrue(polygon.getPoints()[4].getX() == 2.83f);
		assertTrue(polygon.getPoints()[5].getX() == 7.7799997f);
		assertTrue(polygon.getPoints()[6].getX() == 9);
		assertTrue(polygon.getPoints()[7].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.75f);
		assertTrue(polygon.getPoints()[2].getY() == 0.75f);
		assertTrue(polygon.getPoints()[3].getY() == 0.5f);
		assertTrue(polygon.getPoints()[4].getY() == 0.61f);
		assertTrue(polygon.getPoints()[5].getY() == 0.61f);
		assertTrue(polygon.getPoints()[6].getY() == 0);
		assertTrue(polygon.getPoints()[7].getY() == 0);
	}
	
	@Test
	public void testAgregationLowMedium3() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.4f);
		liste.put("MEDIUM", 0.61f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 7);
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 2.2f);
		assertTrue(polygon.getPoints()[3].getX() == 2.83f);
		assertTrue(polygon.getPoints()[4].getX() == 7.7799997f);
		assertTrue(polygon.getPoints()[5].getX() == 9);
		assertTrue(polygon.getPoints()[6].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.4f);
		assertTrue(polygon.getPoints()[2].getY() == 0.4f);
		assertTrue(polygon.getPoints()[3].getY() == 0.61f);
		assertTrue(polygon.getPoints()[4].getY() == 0.61f);
		assertTrue(polygon.getPoints()[5].getY() == 0f);
		assertTrue(polygon.getPoints()[6].getY() == 0);
	}

	@Test
	public void testAgregationLowMedium4() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.5f);
		liste.put("MEDIUM", 0.5f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 5);
		
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 8);
		assertTrue(polygon.getPoints()[3].getX() == 9f);
		assertTrue(polygon.getPoints()[4].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.5f);
		assertTrue(polygon.getPoints()[2].getY() == 0.5);
		assertTrue(polygon.getPoints()[3].getY() == 0);
		assertTrue(polygon.getPoints()[4].getY() == 0);
	}
	
	@Test
	public void testAgregationLowMedium5() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.75f);
		liste.put("MEDIUM", 0.5f);

		Polygon polygon = inference.analysePoints(liste);
		
		assertTrue(polygon.getPoints().length == 7);
		
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 1.75f);
		assertTrue(polygon.getPoints()[3].getX() == 2.5f);
		assertTrue(polygon.getPoints()[4].getX() == 8);
		assertTrue(polygon.getPoints()[5].getX() == 9);
		assertTrue(polygon.getPoints()[6].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.75f);
		assertTrue(polygon.getPoints()[2].getY() == 0.75);
		assertTrue(polygon.getPoints()[3].getY() == 0.5f);
		assertTrue(polygon.getPoints()[4].getY() == 0.5f);
		assertTrue(polygon.getPoints()[5].getY() == 0);
		assertTrue(polygon.getPoints()[6].getY() == 0);
	}
	
	@Test
	public void testAgregationMediumHigh1() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("MEDIUM", 0.5f);
		liste.put("HIGH", 0.5f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 5);
		
		assertTrue(polygon.getPoints()[0].getX() == 1);
		assertTrue(polygon.getPoints()[1].getX() == 2.5f);
		assertTrue(polygon.getPoints()[2].getX() == max);
		assertTrue(polygon.getPoints()[3].getX() == max);
		assertTrue(polygon.getPoints()[4].getX() == 1);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.5f);
		assertTrue(polygon.getPoints()[2].getY() == 0.5);
		assertTrue(polygon.getPoints()[3].getY() == 0);
		assertTrue(polygon.getPoints()[4].getY() == 0);
	}
	
	@Test
	public void testAgregationMediumHigh2() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("MEDIUM", 0.61f);
		liste.put("HIGH", 0.4f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 7);
		
		assertTrue(polygon.getPoints()[0].getX() == 1);
		assertTrue(polygon.getPoints()[1].getX() == 2.83f);
		assertTrue(polygon.getPoints()[2].getX() == 7.7799997f);
		assertTrue(polygon.getPoints()[3].getX() == 8.2f);
		assertTrue(polygon.getPoints()[4].getX() == max);
		assertTrue(polygon.getPoints()[5].getX() == max);
		assertTrue(polygon.getPoints()[6].getX() == 1);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.61f);
		assertTrue(polygon.getPoints()[2].getY() == 0.61f);
		assertTrue(polygon.getPoints()[3].getY() == 0.4f);
		assertTrue(polygon.getPoints()[4].getY() == 0.4f);
		assertTrue(polygon.getPoints()[5].getY() == 0);
		assertTrue(polygon.getPoints()[6].getY() == 0);
	}
	
	@Test
	public void testAgregationMediumHigh3() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("MEDIUM", 0.2f);
		liste.put("HIGH", 0.7f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 7);
		
		assertTrue(polygon.getPoints()[0].getX() == 1);
		assertTrue(polygon.getPoints()[1].getX() == 1.6f);
		assertTrue(polygon.getPoints()[2].getX() == 7.4f);
		assertTrue(polygon.getPoints()[3].getX() == 8.4f);
		assertTrue(polygon.getPoints()[4].getX() == max);
		assertTrue(polygon.getPoints()[5].getX() == max);
		assertTrue(polygon.getPoints()[6].getX() == 1);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.2f);
		assertTrue(polygon.getPoints()[2].getY() == 0.2f);
		assertTrue(polygon.getPoints()[3].getY() == 0.7f);
		assertTrue(polygon.getPoints()[4].getY() == 0.7f);
		assertTrue(polygon.getPoints()[5].getY() == 0);
		assertTrue(polygon.getPoints()[6].getY() == 0);
	}
	
	@Test
	public void testAgregationLowMediumHigh1() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.75f);
		liste.put("MEDIUM", 0.2f);
		liste.put("HIGH", 0.7f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 9);
		
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == 1.75f);
		assertTrue(polygon.getPoints()[3].getX() == 3.4f);
		assertTrue(polygon.getPoints()[4].getX() == 7.4f);
		assertTrue(polygon.getPoints()[5].getX() == 8.4f);
		assertTrue(polygon.getPoints()[6].getX() == max);
		assertTrue(polygon.getPoints()[7].getX() == max);
		assertTrue(polygon.getPoints()[8].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.75f);
		assertTrue(polygon.getPoints()[2].getY() == 0.75f);
		assertTrue(polygon.getPoints()[3].getY() == 0.2f);
		assertTrue(polygon.getPoints()[4].getY() == 0.2f);
		assertTrue(polygon.getPoints()[5].getY() == 0.7f);
		assertTrue(polygon.getPoints()[6].getY() == 0.7f);
		assertTrue(polygon.getPoints()[7].getY() == 0);
		assertTrue(polygon.getPoints()[8].getY() == 0);
	}
	
	@Test
	public void testAgregationLowMediumHigh2() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("LOW", 0.5f);
		liste.put("MEDIUM", 0.5f);
		liste.put("HIGH", 0.5f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 5);
		
		assertTrue(polygon.getPoints()[0].getX() == 0);
		assertTrue(polygon.getPoints()[1].getX() == 0);
		assertTrue(polygon.getPoints()[2].getX() == max);
		assertTrue(polygon.getPoints()[3].getX() == max);
		assertTrue(polygon.getPoints()[4].getX() == 0);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.5f);
		assertTrue(polygon.getPoints()[2].getY() == 0.5f);
		assertTrue(polygon.getPoints()[3].getY() == 0);
		assertTrue(polygon.getPoints()[4].getY() == 0);
	}
	
	@Test
	public void testAgregationHigh() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("HIGH", 0.4f);

		Polygon polygon = inference.analysePoints(liste);
		
		assertTrue(polygon.getPoints().length == 5);
		
		assertTrue(polygon.getPoints()[0].getX() == 7);
		assertTrue(polygon.getPoints()[1].getX() == 7.8f);
		assertTrue(polygon.getPoints()[2].getX() == max);
		assertTrue(polygon.getPoints()[3].getX() == max);
		assertTrue(polygon.getPoints()[4].getX() == 7);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.4f);
		assertTrue(polygon.getPoints()[2].getY() == 0.4f);
		assertTrue(polygon.getPoints()[3].getY() == 0);
		assertTrue(polygon.getPoints()[4].getY() == 0);
	}
	
	@Test
	public void testAgregationMedium() {
		HashMap<String, Float> liste = new HashMap<>();
		liste.put("MEDIUM", 0.4f);

		Polygon polygon = inference.analysePoints(liste);
		assertTrue(polygon.getPoints().length == 5);
		
		assertTrue(polygon.getPoints()[0].getX() == 1);
		assertTrue(polygon.getPoints()[1].getX() == 2.2f);
		assertTrue(polygon.getPoints()[2].getX() == 8.2f);
		assertTrue(polygon.getPoints()[3].getX() == 9);
		assertTrue(polygon.getPoints()[4].getX() == 1);
		assertTrue(polygon.getPoints()[0].getY() == 0);
		assertTrue(polygon.getPoints()[1].getY() == 0.4f);
		assertTrue(polygon.getPoints()[2].getY() == 0.4f);
		assertTrue(polygon.getPoints()[3].getY() == 0);
		assertTrue(polygon.getPoints()[4].getY() == 0);
	}
	
	@Test
	public void testAnteL() {
		assertTrue(inference.anteL(0) == 4);
		assertTrue(inference.anteL(1) == 1);
		assertTrue(inference.anteL(0.5f) == 2.5f);
		assertTrue(inference.anteL(0.75f) == 1.75);
	}

	@Test
	public void testAnteM() {
		assertTrue(inference.anteM(0)[0] == 1);
		assertTrue(inference.anteM(0)[1] == 9);
		assertTrue(inference.anteM(1)[0] == 4);
		assertTrue(inference.anteM(1)[1] == 7);
		assertTrue(inference.anteM(0.5f)[0] == 2.5f);
		assertTrue(inference.anteM(0.5f)[1] == 8);
		assertTrue(inference.anteM(0.75f)[0] == 3.25);
		assertTrue(inference.anteM(0.75f)[1] == 7.5);
	}

	@Test
	public void testAnteH() {
		assertTrue(inference.anteH(0) == 7);
		assertTrue(inference.anteH(1) == 9);
		assertTrue(inference.anteH(0.5f) == 8);
		assertTrue(inference.anteH(0.75f) == 8.5);
	}

}
