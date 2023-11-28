package wfc;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Random;

import schmallcraft.util.Direction;

/**
 * Wave Function Collapse algoritmust megvalósító osztály
 */
public class WaveFunctionCollapse {
	private Random random;
	WaveCell[][] wave;
	private Pattern[] patterns;

	private int patternWidth;
	private int patternHeight;

	private int resultWidth;
	private int resultHeight;

	/**
	 * Inicializálja az algoritmushoz használt mintákat.
	 * 
	 * @param inputPattern
	 * @param patternWidth
	 * @param patternHeight
	 * @param resultWidth
	 * @param resultHeight
	 * @param random
	 */
	public WaveFunctionCollapse(int[][] inputPattern, int patternWidth, int patternHeight, int resultWidth,
			int resultHeight, Random random) {
		this.random = random;
		this.patternWidth = patternWidth;
		this.patternHeight = patternHeight;
		this.resultWidth = resultWidth;
		this.resultHeight = resultHeight;

		// Find patterns in input sample
		HashSet<Pattern> ptns = new HashSet<Pattern>();
		for (int iy = 0; iy < inputPattern.length - patternHeight; iy++) {
			for (int ix = 0; ix < inputPattern[iy].length - patternWidth; ix++) {
				int[][] pattern = new int[patternHeight][patternWidth];
				for (int py = 0; py < patternHeight; py++) {
					for (int px = 0; px < patternWidth; px++) {
						pattern[py][px] = inputPattern[iy + py][ix + px];
					}
				}
				Pattern p = new Pattern(pattern);
				if (ptns.contains(p)) {
					ptns.stream().filter(x -> x.equals(p)).findFirst().get().increaseWeight();
				} else {
					ptns.add(p);
				}
			}
		}

		// Calculate what patterns can be next to eachother
		this.patterns = ptns.toArray(new Pattern[ptns.size()]);
		for (Pattern pattern : this.patterns) {
			pattern.calculateCompatibilities(this.patterns);
		}
		System.out.println("Found " + patterns.length + " patterns");

		// Setup wave
		int waveWidth = resultWidth / patternWidth + 1;
		int waveHeight = resultHeight / patternHeight + 1;
		wave = new WaveCell[waveHeight][waveWidth];
		for (int y = 0; y < wave.length; y++) {
			for (int x = 0; x < wave[y].length; x++) {
				wave[y][x] = new WaveCell(random, patterns);
			}
		}
	}

	/**
	 * Kényszerít egy adott cellát, úgy hogy a végső eredményben adott érték legyen
	 * adott x,y pozícióban.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setFixed(int x, int y, int value) {
		int wx = x / patternWidth;
		int wy = y / patternHeight;
		int px = x % patternWidth;
		int py = y % patternHeight;

		for (int i = 0; i < patterns.length; i++) {
			if (patterns[i].getRawPattern()[py][px] == value) {
				wave[wy][wx].collapseTo(patterns[i]);
				propagate(new Point(wx, wy));
				break;
			}
		}
	}

	/**
	 * Változások kiterjesztése szomszédos cellákra
	 * 
	 * @param index A cella koordinátái
	 * @return Igaz, ha nem volt hiba.
	 */
	private boolean propagate(Point index) {
		Deque<Point> stack = new ArrayDeque<>();
		stack.push(index);
		while (!stack.isEmpty()) {
			Point p = stack.pop();
			int x = p.x;
			int y = p.y;

			for (Direction direction : Direction.values()) {
				int nx = x - direction.dx;
				int ny = y - direction.dy;

				if (nx < 0 || ny >= wave.length || ny < 0 || nx >= wave[ny].length) {
					continue;
				}
				WaveCell neighbour = wave[ny][nx];
				boolean changed = neighbour.propagate(wave[y][x], direction);
				if (changed) {
					stack.push(new Point(nx, ny));
					if (neighbour.getEntropy() == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Térkép generálása
	 * 
	 * @return A bemeneti mintához hasonló, adott méretű int tömb
	 */
	public int[][] generateMap() {

		int entropy;
		ArrayList<Point> minEntropyPoints = new ArrayList<>();
		boolean done = false;
		while (!done) {
			// Find minimum entropy cells
			minEntropyPoints.clear();
			entropy = Integer.MAX_VALUE;
			for (int y = 0; y < wave.length; y++) {
				for (int x = 0; x < wave[y].length; x++) {
					if (wave[y][x].getEntropy() < entropy && wave[y][x].getEntropy() > 1) {
						entropy = wave[y][x].getEntropy();
						minEntropyPoints.clear();
						minEntropyPoints.add(new Point(x, y));
					} else if (wave[y][x].getEntropy() == entropy) {
						minEntropyPoints.add(new Point(x, y));
					}
				}
			}
			if (minEntropyPoints.isEmpty()) {
				done = true;
			} else {
				// Chose a random cell if there are multiple with the same entropy
				// Collapse it to a valid state and propagate the result
				int indexToCollapse = random.nextInt(minEntropyPoints.size());
				Point pointToCollapse = minEntropyPoints.get(indexToCollapse);
				WaveCell cellToCollapse = wave[pointToCollapse.y][pointToCollapse.x];
				cellToCollapse.collapse();
				propagate(pointToCollapse);
			}
		}

		// Write patterns to an array
		int[][] map = new int[resultHeight][resultWidth];
		for (int y = 0; y < wave.length; y++) {
			for (int x = 0; x < wave[y].length; x++) {
				Pattern p = wave[y][x].getState();
				for (int py = 0; py < patternHeight && y * patternHeight + py < resultHeight; py++) {
					for (int px = 0; px < patternWidth && x * patternWidth + px < resultWidth; px++) {
						if (p != null) {
							map[y * patternHeight + py][x * patternWidth + px] = p.getRawPattern()[py][px];
						} else {
							map[y * patternHeight + py][x * patternWidth + px] = 255;
						}
					}
				}
			}
		}
		return map;
	}
}
