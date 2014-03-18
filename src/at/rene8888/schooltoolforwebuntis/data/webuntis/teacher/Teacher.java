package at.rene8888.schooltoolforwebuntis.data.webuntis.teacher;

import java.util.ArrayList;

import at.rene8888.schooltoolforwebuntis.data.webuntis.department.Department;

public class Teacher {

	private int id;
	private String name;
	private String forename;
	private String longName;
	private boolean active;
	private ArrayList<Department> departments;

	public Teacher(int id, String name, String forename, String longName, boolean active, ArrayList<Department> departments) {
		super();
		this.id = id;
		this.name = name;
		this.forename = forename;
		this.longName = longName;
		this.active = active;
		this.departments = departments;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getForename() {
		return forename;
	}

	public String getLongName() {
		return longName;
	}

	public boolean isActive() {
		return active;
	}

	public ArrayList<Department> getDepartments() {
		return departments;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", forename=" + forename + ", longName=" + longName + ", active=" + active + ", departments=" + departments + "]";
	}
}
