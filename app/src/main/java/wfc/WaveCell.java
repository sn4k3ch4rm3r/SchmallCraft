package wfc;

import java.util.Arrays;
import java.util.Random;

import schmallcraft.util.Direction;

public class WaveCell {
	private Random random;
	private Pattern[] allStates;
	private boolean[] stateValid;
	private Pattern state;
	private int entropy;

	public WaveCell(Random random, Pattern[] allStates) {
		this.random = random;
		this.allStates = allStates;
		this.stateValid = new boolean[allStates.length];
		Arrays.fill(stateValid, true);
		this.state = null;
		this.entropy = allStates.length;
	}

	public int getEntropy() {
		return entropy;
	}

	public Pattern[] getValidStates() {
		Pattern[] validStates = new Pattern[entropy];
		if (state != null) {
			validStates[0] = state;
			return validStates;
		}
		int index = 0;
		for (int i = 0; i < stateValid.length; i++) {
			if (stateValid[i]) {
				validStates[index] = allStates[i];
				index++;
			}
		}
		return validStates;
	}

	public boolean propagate(WaveCell neighbour, Direction neighbourDirection) {
		if (state != null) {
			return false;
		}
		boolean changed = false;
		for (int i = 0; i < stateValid.length; i++) {
			if (stateValid[i]) {
				boolean valid = false;
				for (Pattern neighbourPattern : neighbour.getValidStates()) {
					if (allStates[i].compatible(neighbourPattern, neighbourDirection)) {
						valid = true;
						break;
					}
				}
				if (!valid) {
					stateValid[i] = false;
					entropy--;
					changed = true;
				}
			}
		}
		if (entropy == 1) {
			state = getValidStates()[0];
		}
		if (entropy == 0) {
			System.out.println("ERROR: Entropy is 0");
		}
		return changed;
	}

	public void collapse() {
		Pattern[] validStates = getValidStates();
		int[] weights = new int[validStates.length];
		int totalWeight = 0;
		for (int i = 0; i < validStates.length; i++) {
			weights[i] = validStates[i].getWeight();
			totalWeight += weights[i];
		}
		int randomWeight = random.nextInt(totalWeight);
		for (int i = 0; i < weights.length; i++) {
			randomWeight -= weights[i];
			if (randomWeight <= 0) {
				collapseTo(validStates[i]);
				return;
			}
		}
	}

	public void collapseTo(Pattern p) {
		state = p;
		entropy = 1;
		for (int i = 0; i < stateValid.length; i++) {
			if (allStates[i].equals(p)) {
				stateValid[i] = true;
			} else {
				stateValid[i] = false;
			}
		}
	}

	public Pattern getState() {
		return state;
	}
}
