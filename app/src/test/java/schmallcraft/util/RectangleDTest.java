package schmallcraft.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleDTest {
	@Test
	public void testGetters() {
		RectangleD r = new RectangleD(new Vector2(1, 2), 3, 4);
		assertEquals(1, r.getX(), 0.0001);
		assertEquals(2, r.getY(), 0.0001);
		assertEquals(3, r.getWidth(), 0.0001);
		assertEquals(4, r.getHeight(), 0.0001);
	}

	@Test
	public void testGetOverlap() {
		RectangleD r1 = new RectangleD(new Vector2(1, 2), 3, 4);
		RectangleD r2 = new RectangleD(new Vector2(2, 3), 3, 4);
		Vector2 overlap = r1.getOverlap(r2);
		assertEquals(2, overlap.x, 0.0001);
		assertEquals(3, overlap.y, 0.0001);
	}

	@Test
	public void testIntersects() {
		RectangleD r1 = new RectangleD(new Vector2(1, 2), 3, 4);
		RectangleD r2 = new RectangleD(new Vector2(2, 3), 3, 4);
		assertTrue(r1.intersects(r2));

		RectangleD r3 = new RectangleD(new Vector2(5, 6), 3, 4);
		assertFalse(r1.intersects(r3));
	}

	@Test
	public void testGetCenter() {
		RectangleD r = new RectangleD(new Vector2(1, 2), 3, 4);
		Vector2 center = r.getCenter();
		assertEquals(2.5, center.x, 0.0001);
		assertEquals(4, center.y, 0.0001);
	}

	@Test
	public void testContains() {
		RectangleD r = new RectangleD(new Vector2(1, 2), 3, 4);
		assertTrue(r.contains(new Vector2(2, 3)));
		assertFalse(r.contains(new Vector2(0, 0)));
	}
}
