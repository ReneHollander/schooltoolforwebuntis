package at.rene8888.schooltoolforwebuntis.gui.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
import at.rene8888.schooltoolforwebuntis.data.webuntis.room.Room;
import at.rene8888.schooltoolforwebuntis.data.webuntis.schoolclasses.SchoolClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.subject.Subject;
import at.rene8888.schooltoolforwebuntis.data.webuntis.teacher.Teacher;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid.Unit;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid.UnitType;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.TimeTableEntry;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.schoolclass.SchoolClassTimeTable;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.schoolclass.SchoolClassTimeTableUnit;
import at.rene8888.schooltoolforwebuntis.gui.activity.MainActivity;

public class TimeTableSection extends Fragment {

	public TimeTableSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.timetable_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.timetable_section, container, false);

		TextView tv = (TextView) rootView.findViewById(R.id.timetable_test_label);

		SchoolClassTimeTable sctt = Data.getData().getCurrentTimeTable();

		StringBuilder out = new StringBuilder();

		for (TimeTableEntry<SchoolClassTimeTableUnit> entry : sctt.getUnits()) {
			Unit unit = entry.getUnit();
			SchoolClassTimeTableUnit scttu = entry.getTimeTableUnit();

			if (unit.getTag() == UnitType.LESSON) {
				out.append(unit.getStart());
				out.append(" - ");
				out.append(unit.getEnd());
				out.append("\n");

				for (Subject s : scttu.getSubjects()) {
					out.append(s.getName());
					out.append(" ");
				}
				out.append("\n");

				for (SchoolClass sc : scttu.getSchoolclasses()) {
					out.append(sc.getName());
					out.append(" ");
				}
				out.append("\n");

				for (Teacher t : scttu.getTeachers()) {
					out.append(t.getName());
					out.append(" ");
				}
				out.append("\n");

				for (Room r : scttu.getRooms()) {
					out.append(r.getName());
					out.append(" ");
				}
				out.append("\n");

				out.append("\n");
			} else if (unit.getTag() == UnitType.BREAK) {
				out.append(unit.getStart());
				out.append(" - ");
				out.append(unit.getEnd());
				out.append("\nPause\n\n");
			} else if (unit.getTag() == UnitType.FREEHOUR) {
				out.append(unit.getStart());
				out.append(" - ");
				out.append(unit.getEnd());
				out.append("\nFreistunde\n\n");
			}

		}
		tv.setText(out.toString());
		return rootView;
	}
}
