package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

public class TimeTableUnit {

	private Unit unit;

	private String subject;

	public TimeTableUnit(Unit unit, String subject) {
		this.unit = unit;
		this.subject = subject;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public String getSubject() {
		return this.subject;
	}
}
