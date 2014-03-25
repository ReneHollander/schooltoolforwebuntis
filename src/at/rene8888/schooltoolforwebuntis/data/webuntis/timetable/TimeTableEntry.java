package at.rene8888.schooltoolforwebuntis.data.webuntis.timetable;

import at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid.Unit;

public class TimeTableEntry<T> {

	private int index;
	private Unit u;
	private T ttu;

	public TimeTableEntry(Unit u, T ttu) {
		this.u = u;
		this.ttu = ttu;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Unit getUnit() {
		return this.u;
	}

	public T getTimeTableUnit() {
		return this.ttu;
	}

}
