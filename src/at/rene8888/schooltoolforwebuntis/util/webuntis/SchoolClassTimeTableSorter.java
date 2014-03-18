package at.rene8888.schooltoolforwebuntis.util.webuntis;

import java.util.Comparator;

import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable.SchoolClassTimeTableUnit;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable.TimeTableEntry;

public class SchoolClassTimeTableSorter implements Comparator<TimeTableEntry<SchoolClassTimeTableUnit>> {

	@Override
	public int compare(TimeTableEntry<SchoolClassTimeTableUnit> lhs, TimeTableEntry<SchoolClassTimeTableUnit> rhs) {
		return lhs.getUnit().compareTo(rhs.getUnit());
	}

}
