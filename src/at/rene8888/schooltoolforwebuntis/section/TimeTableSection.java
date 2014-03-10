package at.rene8888.schooltoolforwebuntis.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.activity.MainActivity;
import at.rene8888.schooltoolforwebuntis.data.webuntis.TimeGrid;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;

public class TimeTableSection extends Fragment {

	public TimeTableSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.timetable_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.timetable_section, container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.timetable_test_label);

		String out = "";

		for (Unit u : new TimeGrid().getUnitList()) {
			out += "\r\n" + u.toString();
		}

		dummyTextView.setText(out);

		return rootView;
	}

}
