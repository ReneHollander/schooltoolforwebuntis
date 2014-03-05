package at.rene8888.schooltoolforwebuntis.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.MainActivity;
import at.rene8888.schooltoolforwebuntis.R;

public class ClockSection extends Fragment {

	public ClockSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getContext().getString(R.string.clock_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.clock_section, container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.clock_test_label);
		dummyTextView.setText("Clock!");
		return rootView;
	}
}
