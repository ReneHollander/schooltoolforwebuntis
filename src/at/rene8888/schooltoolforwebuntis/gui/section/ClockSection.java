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
import at.rene8888.schooltoolforwebuntis.data.util.Time;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
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
		if ((currindex + 1) < sctt.getUnits().size()) {
			nextEntry = sctt.getUnits().get(currindex + 1);
		} else {
			nextEntry = currEntry;
		}
		
		Time t2 = (Time) currEntry.getUnit().getEnd().clone();
		t2.substract(new Time(ApplicationClass.getApplication().getDelay()));

		final String tvstring = t2.toString();
		final String descstring = "until the next " + nextEntry.getUnit().getTag().getName();
		
		MainActivity.getMainActivity().runOnUiThread(new Runnable() {
			public void run() {
				TextView tv = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewTime);
				TextView desc = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewHourOrBreak);
				tv.setText(tvstring);
				desc.setText(descstring);
			}
		});

	}
}
