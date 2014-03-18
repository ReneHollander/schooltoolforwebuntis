package at.rene8888.schooltoolforwebuntis.data.webuntis.schoolclasses;

import at.rene8888.schooltoolforwebuntis.data.webuntis.department.Department;

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

	@Override
	public String toString() {
		return "SchoolClass [id=" + id + ", name=" + name + ", longName=" + longName + ", department=" + department + ", active=" + active + "]";
	}

}
