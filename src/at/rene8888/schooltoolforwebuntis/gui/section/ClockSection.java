package at.rene8888.schooltoolforwebuntis.gui.section;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.thread.ClockUpdateThread;
import at.rene8888.schooltoolforwebuntis.data.util.Time;
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

public class ClockSection extends Fragment {

	public ClockSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.clock_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.clock_section, container, false);
		new ClockUpdateThread(new SchoolClassTimeTable(Data.getData().getSchoolClasses().getSchoolClassById(591), Calendar.getInstance()), this).start();
		return rootView;
	}

	public void update(SchoolClassTimeTable sctt, int currindex, TimeTableEntry<SchoolClassTimeTableUnit> currEntry) {
		TimeTableEntry<SchoolClassTimeTableUnit> nextEntry;
		TimeTableEntry<SchoolClassTimeTableUnit> nextLesson = null;
		for (int i = currindex; i < sctt.getUnits().size(); i++) {
			TimeTableEntry<SchoolClassTimeTableUnit> tte = sctt.getUnits().get(i);
			if (tte.getUnit().getTag() == UnitType.LESSON) {
				nextLesson = tte;
				break;
			}
		}
		if ((currindex + 1) < sctt.getUnits().size()) {
			nextEntry = sctt.getUnits().get(currindex + 1);
		} else {
			nextEntry = currEntry;
		}

		Time t2 = (Time) currEntry.getUnit().getEnd().clone();
		t2.substract(new Time(ApplicationClass.getApplication().getDelay()));

		final String tvstring = t2.toString();
		final String descstring = "until the next " + nextEntry.getUnit().getTag().getName();
		final String currlessonstring = this.stringify(currEntry);
		final String nextlessonstring = this.stringify(nextLesson);

		MainActivity.getMainActivity().runOnUiThread(new Runnable() {
			public void run() {
				try {
					TextView tv = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewTime);
					TextView desc = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewHourOrBreak);
					TextView currlesson = (TextView) MainActivity.getMainActivity().findViewById(R.id.tvCurrentLesson);
					TextView nextlesson = (TextView) MainActivity.getMainActivity().findViewById(R.id.tvNextLesson);
					tv.setText(tvstring);
					desc.setText(descstring);
					currlesson.setText(currlessonstring);
					nextlesson.setText(nextlessonstring);
				} catch (Exception e) {
					Log.d("clock", "error while trying to set clock", e);
				}
			}
		});

	}

	public String stringify(TimeTableEntry<SchoolClassTimeTableUnit> tte) {
		StringBuilder out = new StringBuilder();
		Unit unit = tte.getUnit();
		if (unit.getTag() == UnitType.LESSON) {
			out.append(unit.getStart());
			out.append(" - ");
			out.append(unit.getEnd());
			out.append("\n");

			for (Subject s : tte.getTimeTableUnit().getSubjects()) {
				out.append(s.getName());
				out.append(" ");
			}
			out.append("\n");

			for (SchoolClass sc : tte.getTimeTableUnit().getSchoolclasses()) {
				out.append(sc.getName());
				out.append(" ");
			}
			out.append("\n");

			for (Teacher t : tte.getTimeTableUnit().getTeachers()) {
				out.append(t.getName());
				out.append(" ");
			}
			out.append("\n");

			for (Room r : tte.getTimeTableUnit().getRooms()) {
				out.append(r.getName());
				out.append(" ");
			}
		} else if (unit.getTag() == UnitType.BREAK) {
			out.append(unit.getStart());
			out.append(" - ");
			out.append(unit.getEnd());
			out.append("\nPause");
		} else if (unit.getTag() == UnitType.FREEHOUR) {
			out.append(unit.getStart());
			out.append(" - ");
			out.append(unit.getEnd());
			out.append("\nFreistunde");
		}
		return out.toString();
	}
}
