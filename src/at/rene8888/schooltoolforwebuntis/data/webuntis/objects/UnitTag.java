package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

public enum UnitTag {

	LESSON("lesson"), BREAK("break");

	private String name;

	UnitTag(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.name;
	}

}
