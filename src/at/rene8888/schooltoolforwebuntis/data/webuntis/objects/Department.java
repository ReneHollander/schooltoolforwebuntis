package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

public class Department {

	private int id;
	private String name;
	private String longName;

	public Department(int id, String name, String longName) {
		this.id = id;
		this.name = name;
		this.longName = longName;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getLongName() {
		return this.longName;
	}

	public String toString() {
		return "[Id=" + this.id + "; Name=" + this.name + "; LongName=" + this.longName + "]";
	}

}
