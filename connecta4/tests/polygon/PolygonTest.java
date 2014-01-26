package polygon;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PolygonTest {
	Polygon polygon;

	@Before
	public void setUp() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(0, 0.5f));
		points.add(new Point(0.5f, 0.5f));
		points.add(new Point(0.5f, 0));
		polygon = new Polygon(points);
	}

	@After
	public void tearDown() {
		polygon = null;
	}

	@Test
	public void testCentroid() {
		Point c = polygon.centroid();
		assertTrue(c.getX() == 0.25f);
		assertTrue(c.getY() == 0.25f);
	}

	@Test
	public void testArea() {
		assertTrue(polygon.area() == 0.25f);
	}

}
