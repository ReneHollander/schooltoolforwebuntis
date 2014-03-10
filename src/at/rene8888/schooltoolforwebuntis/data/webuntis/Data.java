package at.rene8888.schooltoolforwebuntis.data.webuntis;

public class Data {

	private static Data DATA;

	private TimeGrid timeGrid;
	private TimeTable timeTable;

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

	public static Data getData() {
		return DATA;
	}
}
