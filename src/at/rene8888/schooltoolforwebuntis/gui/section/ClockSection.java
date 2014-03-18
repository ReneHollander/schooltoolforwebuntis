package at.rene8888.schooltoolforwebuntis.gui.section;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.data.thread.ClockUpdateThread;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
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
		TextView tv = (TextView) rootView.findViewById(R.id.textViewTime);
		TextView desc = (TextView) rootView.findViewById(R.id.textViewHourOrBreak);
		new ClockUpdateThread(Data.getData().getTimeGrids().getTimeGridByCalendar(Calendar.getInstance()).getUnitList(), tv, desc).start();
		return rootView;
	}
}
