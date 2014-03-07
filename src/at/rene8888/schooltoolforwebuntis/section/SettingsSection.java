package at.rene8888.schooltoolforwebuntis.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.activity.MainActivity;

public class SettingsSection extends Fragment implements View.OnClickListener {
	private ApplicationClass app;

	public SettingsSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.settings_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.settings_section, container, false);
		app = (ApplicationClass) MainActivity.getMainActivity().getApplication();
		rootView.findViewById(R.id.buttonSaveSettings).setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		EditText user = (EditText) MainActivity.getMainActivity().findViewById(R.id.etusernameSettings);
		EditText pw = (EditText) MainActivity.getMainActivity().findViewById(R.id.etpasswordSettings);
		app.setUsername(user.getText().toString());
		app.setPassword(pw.getText().toString());
		app.saveChanges();
		updateSettings();
	}
	
	public void updateSettings() {
		// TODO
	}
}
