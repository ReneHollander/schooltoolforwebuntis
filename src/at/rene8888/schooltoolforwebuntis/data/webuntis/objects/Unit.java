package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

import at.rene8888.schooltoolforwebuntis.data.Time;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.type.UnitType;

public class Unit {

	private Time start;
	private Time end;

	private UnitType tag;

	public Unit(Time start, Time end, UnitType tag) {
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

	public UnitType getTag() {
		return this.tag;
	}

	public String toString() {
		return "[Start=" + this.start.toString() + "; End=" + this.end.toString() + "; Tag=" + this.tag.toString() + "]";
	}

}
