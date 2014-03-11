package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

public class SchoolClass {

	private int id;
	private String name;
	private String longName;
	private Department department;
	private boolean active;

	public SchoolClass(int id, String name, String longName, Department department, boolean active) {
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.department = department;
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

	public Department getDepartment() {
		return this.department;
	}

	public boolean isActive() {
		return this.active;
	}

	public String toString() {
		return "[Id=" + this.id + "; Name=" + this.name + "; LongName=" + this.longName + "; Department=" + this.department + "; Active=" + this.active + "]";
	}

}
