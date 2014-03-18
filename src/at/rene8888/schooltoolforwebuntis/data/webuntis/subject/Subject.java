package at.rene8888.schooltoolforwebuntis.data.webuntis.subject;

public class Subject {

	private int id;
	private String name;
	private String longName;
	private boolean active;

	public Subject(int id, String name, String longName, boolean active) {
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.active = active;
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

	public boolean isActive() {
		return this.active;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", longName=" + longName + ", active=" + active + "]";
	}

}
