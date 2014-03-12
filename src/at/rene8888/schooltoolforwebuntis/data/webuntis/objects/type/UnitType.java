package at.rene8888.schooltoolforwebuntis.data.webuntis.objects.type;

public enum UnitType {

	LESSON("lesson"), BREAK("break");

	private String name;

	UnitType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.name;
	}

}
