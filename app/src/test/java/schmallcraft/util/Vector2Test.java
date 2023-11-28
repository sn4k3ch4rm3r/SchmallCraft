package schmallcraft.util;

import java.awt.Point;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector2Test {
	@Test
	public void testAdd() {
		Vector2 v1 = new Vector2(2, 3);
		Vector2 v2 = new Vector2(1, 2);
		Vector2 v3 = v1.add(v2);
		assertEquals(3, v3.x, 0.0001);
		assertEquals(5, v3.y, 0.0001);
	}

	@Test
	public void testSubtract() {
		Vector2 v1 = new Vector2(2, 3);
		Vector2 v2 = new Vector2(1, 2);
		Vector2 v3 = v1.subtract(v2);
		assertEquals(1, v3.x, 0.0001);
		assertEquals(1, v3.y, 0.0001);
	}

	@Test
	public void testMultiply() {
		Vector2 v1 = new Vector2(2, 3);
		Vector2 v2 = v1.multiply(2);
		assertEquals(4, v2.x, 0.0001);
		assertEquals(6, v2.y, 0.0001);
	}

	@Test
	public void testMagnitude() {
		Vector2 v1 = new Vector2(3, 4);
		assertEquals(5, v1.magnitude(), 0.0001);
	}

	@Test
	public void testNormalize() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = v1.normalize();
		assertEquals(1, v2.magnitude(), 0.0001);
		assertEquals(0.6, v2.x, 0.0001);
		assertEquals(0.8, v2.y, 0.0001);

		Vector2 v3 = new Vector2();
		Vector2 v4 = v3.normalize();
		assertEquals(0, v4.magnitude(), 0.0001);
	}

	@Test
	public void testFloor() {
		Vector2 v1 = new Vector2(2.3, 3.7);
		Vector2 v2 = v1.floor();
		assertEquals(2, v2.x, 0.0001);
		assertEquals(3, v2.y, 0.0001);
	}

	@Test
	public void testToPoint() {
		Vector2 v1 = new Vector2(2, 3);
		assertEquals(new Point(2, 3), v1.toPoint());
	}

	@Test
	public void testToString() {
		Vector2 v1 = new Vector2(2, 3);
		assertEquals("Vector2(2.0, 3.0)", v1.toString());
	}

}
