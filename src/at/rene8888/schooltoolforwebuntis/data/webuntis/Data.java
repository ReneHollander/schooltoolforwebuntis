package at.rene8888.schooltoolforwebuntis.data.webuntis;

public class Data {

	private static Data DATA;

	private TimeGrid timeGrid;
	private TimeTable timeTable;
	private Rooms rooms;
	private SchoolClasses schoolClasses;
	private Departments departments;
	private Subjects subjects;

	// TODO ADD COLORS WHERE NEEDED

	public Data() {
		DATA = this;
	}

	public TimeGrid getTimeGrid() {
		if (this.timeGrid == null) {
			this.timeGrid = new TimeGrid();
		}
		return this.timeGrid;
	}

	public TimeTable getTimeTable(String schoolClass) {
		if (this.timeTable == null) {
			this.timeTable = new TimeTable(schoolClass);
		}
		if (this.timeTable.getSchoolClass().equals(schoolClass)) {
			return this.timeTable;
		} else {
			return new TimeTable(schoolClass);
		}
	}

	public Rooms getRooms() {
		if (this.rooms == null) {
			this.rooms = new Rooms();
		}
		return this.rooms;
	}

	public SchoolClasses getSchoolClasses() {
		if (this.schoolClasses == null) {
			this.schoolClasses = new SchoolClasses();
		}
		return this.schoolClasses;
	}

	public Departments getDepartments() {
		if (this.departments == null) {
			this.departments = new Departments();
		}
		return this.departments;
	}

	public Subjects getSubjects() {
		if (this.subjects == null) {
			this.subjects = new Subjects();
		}
		return this.subjects;
	}

	public static Data getData() {
		return DATA;
	}
}
