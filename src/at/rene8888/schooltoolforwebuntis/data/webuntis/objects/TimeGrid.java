package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

import java.util.ArrayList;

import at.rene8888.schooltoolforwebuntis.util.Time;

public class TimeGrid {

	private ArrayList<Unit> unitList;

	public TimeGrid(ArrayList<Unit> unitList) {
		this.unitList = unitList;
	}

	public ArrayList<Unit> getUnitList() {
		return this.unitList;
	}

	public Unit getUnitByTime(Time start, Time end) {
		for (Unit u : this.getUnitList()) {
			if (u.getStart().equals(start) && u.getEnd().equals(end)) {
				return u;
			}
		}
		return null;
	}
}
