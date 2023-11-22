package wfc;

import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import schmallcraft.util.Direction;

public class PatternTest {
	int[][][] rawPatterns;
	Pattern[] patterns;
	Random random;

	@Before
	public void init() {
		int[][][] rp = {
				{
						{ 0, 0, 0 },
						{ 1, 1, 1 },
						{ 0, 0, 0 },
				},
				{
						{ 0, 1, 0 },
						{ 0, 1, 0 },
						{ 0, 1, 0 },
				},
				{
						{ 1, 0, 0 },
						{ 1, 1, 1 },
						{ 1, 0, 0 },
				},
				{
						{ 1, 1, 1 },
						{ 0, 1, 0 },
						{ 0, 1, 0 },
				},
				{
						{ 1, 1, 1 },
						{ 1, 0, 0 },
						{ 1, 0, 0 },
				},
				{
						{ 1, 1, 1 },
						{ 0, 0, 0 },
						{ 0, 0, 0 },
				},
				{
						{ 1, 0, 0 },
						{ 1, 0, 0 },
						{ 1, 0, 0 },
				},
				{
						{ 0, 0, 0 },
						{ 0, 0, 0 },
						{ 0, 0, 0 },
				},
				{
						{ 0, 1, 0 },
						{ 1, 1, 1 },
						{ 0, 1, 0 },
				}
		};
		random = new Random(0);
		rawPatterns = rp;
		patterns = new Pattern[rawPatterns.length];
		for (int i = 0; i < rawPatterns.length; i++) {
			patterns[i] = new Pattern(rawPatterns[i]);
		}
		for (Pattern p : patterns) {
			p.calculateCompatibilities(patterns);
		}
	}

	@Test
	public void compatibilityTest() {
		Pattern ll = patterns[6];
		Pattern tl = patterns[5];
		Pattern clh = patterns[0];
		Pattern empty = patterns[7];
		Pattern cross = patterns[8];

		assertTrue(empty.compatible(tl, Direction.UP));
		assertFalse(empty.compatible(tl, Direction.DOWN));
		assertTrue(empty.compatible(clh, Direction.UP));
		assertTrue(tl.compatible(tl, Direction.LEFT));
		assertTrue(tl.compatible(tl, Direction.RIGHT));
		assertFalse(ll.compatible(empty, Direction.LEFT));
		assertTrue(ll.compatible(empty, Direction.RIGHT));
		assertFalse(ll.compatible(empty, Direction.DOWN));
		assertFalse(ll.compatible(empty, Direction.UP));

		assertTrue(empty.compatible(ll, Direction.LEFT));
		assertFalse(empty.compatible(ll, Direction.UP));

		assertFalse(cross.compatible(empty, Direction.RIGHT));
		assertFalse(cross.compatible(ll, Direction.LEFT));
	}

	@Test
	public void propagationTest() {
		for (Direction direction : Direction.values()) {
			for (int i = 0; i < patterns.length; i++) {
				Pattern p = patterns[i];
				WaveCell wcFix = new WaveCell(random, patterns);
				WaveCell wcNeighbour = new WaveCell(random, patterns);

				wcFix.collapseTo(p);
				assertTrue(wcNeighbour.propagate(wcFix, direction));
				for (Pattern state : wcNeighbour.getValidStates()) {
					if (state != null) {
						assertTrue(state.compatible(p, direction));
					}
				}
			}
		}
	}

	@Test
	public void collapseTest() {
		Pattern p = patterns[6];
		WaveCell wcFix = new WaveCell(random, patterns);
		WaveCell wcNeighbour = new WaveCell(random, patterns);
		WaveCell wcSeccondNeighbour = new WaveCell(random, patterns);

		wcFix.collapseTo(p);
		wcNeighbour.propagate(wcFix, Direction.UP);
		assertEquals(1, wcNeighbour.getEntropy());

		wcSeccondNeighbour.propagate(wcNeighbour, Direction.UP);
		assertEquals(1, wcSeccondNeighbour.getValidStates().length);

		wcSeccondNeighbour.collapse();

	}
}
