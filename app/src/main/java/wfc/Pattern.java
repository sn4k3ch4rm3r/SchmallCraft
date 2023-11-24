package wfc;

import java.util.Arrays;
import java.util.Map;

import schmallcraft.util.Direction;

import java.util.HashSet;
import java.util.EnumMap;

public class Pattern {
	private int[][] rawPattern;
	private int weight;
	private int width;
	private int height;
	private Map<Direction, HashSet<Pattern>> compatiblePatterns;

	public Pattern(int[][] pattern) {
		this.rawPattern = pattern;
		this.width = pattern[0].length;
		this.height = pattern.length;
		this.weight = 1;
	}

	public boolean compatible(Pattern neigbour, Direction neighbourDirection) {
		return compatiblePatterns.get(neighbourDirection).contains(neigbour);
	}

	public void calculateCompatibilities(Pattern[] allPatterns) {
		compatiblePatterns = new EnumMap<>(Direction.class);
		for (Direction direction : Direction.values()) {
			compatiblePatterns.put(direction, new HashSet<>());
			for (Pattern other : allPatterns) {
				// Construct the combination of both patterns
				int w = direction.isHorizontal() ? width * 2 : width;
				int h = direction.isVertical() ? height * 2 : height;
				int[][] combined = new int[h][w];
				int[][] otherPattern = other.getRawPattern();
				if (direction.isVertical()) {
					int offset = direction == Direction.UP ? height : 0;
					for (int y = 0; y < height; y++) {
						System.arraycopy(rawPattern[y], 0, combined[y + offset], 0, width);
						System.arraycopy(otherPattern[y], 0, combined[y + height - offset], 0,
								width);
					}
				} else {
					int offset = direction == Direction.LEFT ? width : 0;
					for (int y = 0; y < height; y++) {
						System.arraycopy(rawPattern[y], 0, combined[y], offset, width);
						System.arraycopy(otherPattern[y], 0, combined[y], width - offset, width);
					}
				}

				// Check if the resulting pattern in between is valid
				int[][] window = new int[height][width];
				int startX = 0;
				int startY = 0;
				boolean valid = true;
				for (int offset = 0; offset <= (direction.isHorizontal() ? width : height) % 2; offset++) {
					switch (direction) {
						case UP:
						case DOWN:
							startY = (height + offset) / 2;
							break;
						case LEFT:
						case RIGHT:
							startX = (width + offset) / 2;
							break;
						default:
							break;
					}
					for (int y = startY; y < startY + height; y++) {
						for (int x = startX; x < startX + width; x++) {
							window[y - startY][x - startX] = combined[y][x];
						}
					}

					Pattern windowPattern = new Pattern(window);
					boolean contains = false;
					for (Pattern pattern : allPatterns) {
						if (pattern.equals(windowPattern)) {
							contains = true;
						}
					}
					if (!contains) {
						valid = false;
					}
				}
				if (valid) {
					compatiblePatterns.get(direction).add(other);
				}
			}
		}
	}

	public Pattern rotate(int amount) {
		int w = width;
		int h = height;
		int[][] res = rawPattern;
		for (int r = 0; r < amount; r++) {
			int[][] rotated = new int[w][h];
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					rotated[x][y] = res[y][x];
				}
			}
			res = rotated;
			int temp = w;
			w = h;
			h = temp;
		}
		return new Pattern(res);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getRawPattern() {
		return rawPattern;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				sb.append(rawPattern[y][x] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof Pattern)) {
			return false;
		}
		Pattern otherPattern = (Pattern) other;
		if (this.width != otherPattern.width || this.height != otherPattern.height) {
			return false;
		}
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				if (this.rawPattern[y][x] != otherPattern.rawPattern[y][x])
					return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(rawPattern);
	}

	public void increaseWeight() {
		weight++;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
