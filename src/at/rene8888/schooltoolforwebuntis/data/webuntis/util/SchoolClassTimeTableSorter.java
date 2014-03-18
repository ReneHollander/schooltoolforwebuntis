package at.rene8888.schooltoolforwebuntis.data.webuntis.util;

import java.util.Comparator;

import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.TimeTableEntry;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.schoolclass.SchoolClassTimeTableUnit;

public class SchoolClassTimeTableSorter implements Comparator<TimeTableEntry<SchoolClassTimeTableUnit>> {

	@Override
	public int compare(TimeTableEntry<SchoolClassTimeTableUnit> lhs, TimeTableEntry<SchoolClassTimeTableUnit> rhs) {
		return lhs.getUnit().compareTo(rhs.getUnit());
	}

}
