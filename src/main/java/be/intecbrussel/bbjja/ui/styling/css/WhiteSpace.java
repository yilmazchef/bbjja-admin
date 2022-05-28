package be.intecbrussel.bbjja.ui.styling.css;

public enum WhiteSpace {

	NORMAL("normal"), NOWRAP("nowrap"), PRE("pre"), PRE_WRAP(
			"pre-wrap"), PRE_LINE("pre-line");

	private String value;

	WhiteSpace(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
