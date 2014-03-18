package at.rene8888.schooltoolforwebuntis.section;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.activity.MainActivity;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Room;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.SchoolClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Subject;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Teacher;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable.SchoolClassTimeTable;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable.SchoolClassTimeTableUnit;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable.TimeTableEntry;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.type.UnitType;
import at.rene8888.schooltoolforwebuntis.util.Util;

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

		SchoolClassTimeTable sctt = new SchoolClassTimeTable(Data.getData().getSchoolClasses().getSchoolClassById(591), Util.convertToYYYYMMDD(Calendar.getInstance()));

		StringBuilder out = new StringBuilder();

		for (TimeTableEntry<SchoolClassTimeTableUnit> entry : sctt.getUnits()) {
			Unit unit = entry.getUnit();
			SchoolClassTimeTableUnit scttu = entry.getTimeTableUnit();

			if (scttu != null) {
				out.append(unit.getStart());
				out.append(" - ");
				out.append(unit.getEnd());
				out.append("\n");

				for (Subject s : scttu.getSubjects()) {
					out.append(s.getName());
				}
				out.append("\n");

				for (SchoolClass sc : scttu.getSchoolclasses()) {
					out.append(sc.getName());
				}
				out.append("\n");

				for (Teacher t : scttu.getTeachers()) {
					out.append(t.getName());
				}
				out.append("\n");

				for (Room r : scttu.getRooms()) {
					out.append(r.getName());
				}
				out.append("\n");

				out.append("\n");
			} else {

				if (unit.getTag() == UnitType.BREAK) {
					out.append(unit.getStart());
					out.append(" - ");
					out.append(unit.getEnd());
					out.append("\nPause\n\n");
				} else {
					out.append(unit.getStart());
					out.append(" - ");
					out.append(unit.getEnd());
					out.append("\nFreistunde\n\n");
				}
			}

		}
		tv.setText(out.toString());
		return rootView;
	}

}
