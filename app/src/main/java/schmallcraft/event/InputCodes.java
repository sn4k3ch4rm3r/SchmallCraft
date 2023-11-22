package schmallcraft.event;

public enum InputCodes {
	UP(0), DOWN(1), LEFT(2), RIGHT(3);

	private final int value;

	private InputCodes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
