package at.rene8888.schooltoolforwebuntis.gui.section;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.thread.ClockUpdateThread;
import at.rene8888.schooltoolforwebuntis.data.util.time.Time;
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
		if (Data.getData().getCurrentTimeTable() == null) {
			Data.getData().setCurrentTimeTable(new SchoolClassTimeTable(Data.getData().getSchoolClasses().getSchoolClassById(591), Calendar.getInstance()));
		}
		new ClockUpdateThread(this).start();
		return rootView;
	}

	public void update() {
		Data data = Data.getData();

		SchoolClassTimeTable sctt = data.getCurrentTimeTable();

		Unit currentUnit = data.getTimeGrids().getTimeGridByCalendar(sctt.getCalendar()).getUnitByTime(new Time(ApplicationClass.getApplication().getDelay()));

		TimeTableEntry<SchoolClassTimeTableUnit> currentEntry = sctt.getUnitbyUnit(currentUnit);
		TimeTableEntry<SchoolClassTimeTableUnit> nextEntry = sctt.getUnitByIndex(currentEntry.getIndex() + 1);

		TimeTableEntry<SchoolClassTimeTableUnit> nextLesson = null;
		if (nextEntry.getUnit().getTag() == UnitType.LESSON) {
			nextLesson = currentEntry;
		} else {
			for (int i = currentEntry.getIndex() + 1; i < sctt.getUnits().size(); i++) {
				TimeTableEntry<SchoolClassTimeTableUnit> entry = sctt.getUnits().get(i);
				if (entry.getUnit().getTag() == UnitType.LESSON) {
					nextLesson = entry;
					break;
				}
			}
		}

		Time t2 = (Time) currentEntry.getUnit().getEnd().clone();
		t2.substract(new Time(ApplicationClass.getApplication().getDelay()));

		final String tvstring = t2.toString();
		final String descstring = "until the next " + nextEntry.getUnit().getTag().getName();
		final String currlessonstring = this.stringify(currentEntry);
		final String nextlessonstring = this.stringify(nextLesson);

		TextView tv = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewTime);
		TextView desc = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewHourOrBreak);
		TextView currlesson = (TextView) MainActivity.getMainActivity().findViewById(R.id.tvCurrentLesson);
		TextView nextlesson = (TextView) MainActivity.getMainActivity().findViewById(R.id.tvNextLesson);
		tv.setText(tvstring);
		desc.setText(descstring);
		currlesson.setText(currlessonstring);
		nextlesson.setText(nextlessonstring);
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
