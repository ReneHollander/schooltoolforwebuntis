package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.type.UnitType;
import at.rene8888.schooltoolforwebuntis.util.Time;

public class Unit implements Comparable<Unit> {

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

	public boolean before(Unit another) {
		if (this.equals(another)) {
			return false;
		} else if (this.compareTo(another) == -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean after(Unit another) {
		if (this.equals(another)) {
			return false;
		} else if (this.compareTo(another) == -1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean equals(Unit another) {
		return this.getTag().equals(another.getTag()) && this.getStart().equals(another.getStart()) && this.getEnd().equals(another.getEnd());
	}

	@Override
	public String toString() {
		return "Unit [start=" + start + ", end=" + end + ", tag=" + tag + "]";
	}

	@Override
	public int compareTo(Unit another) {
		// return this.getStart().compareTo(another.getStart()) &
		// this.getStart().compareTo(another.getEnd()) &
		// this.getEnd().compareTo(another.getStart()) &
		// this.getEnd().compareTo(another.getEnd());
		return this.getStart().compareTo(another.getStart()) & this.getEnd().compareTo(another.getEnd());
	}
}
