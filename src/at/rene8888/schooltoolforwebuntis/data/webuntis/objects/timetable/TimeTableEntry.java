package at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable;

import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;

public class TimeTableEntry<T> {

	private Unit u;
	private T ttu;

	public TimeTableEntry(Unit u, T ttu) {
		this.u = u;
		this.ttu = ttu;
	}

	public Unit getUnit() {
		return this.u;
	}

	public T getTimeTableUnit() {
		return this.ttu;
	}

}
