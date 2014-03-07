package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

import at.rene8888.schooltoolforwebuntis.data.Time;

public class Unit {

	private Time start;
	private Time end;

	private UnitTag tag;

	public Unit(Time start, Time end, UnitTag tag) {
		this.start = start;
		this.end = end;
		this.tag = tag;
	}

	public Time getStart() {
		return this.start;
	}

	public Time getEnd() {
		return this.end;
	}

	public UnitTag getTag() {
		return this.tag;
	}

	public String toString() {
		return "[Start=" + this.start.toString() + "; End=" + this.end.toString() + "; Tag=" + this.tag.toString() + "]";
	}

}
