package at.rene8888.schooltoolforwebuntis.gui.section;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.gui.activity.MainActivity;
import at.rene8888.schooltoolforwebuntis.gui.activity.PrefsActivity;

public class SettingsSection extends Fragment implements View.OnClickListener {
	private MainActivity ma;

	public SettingsSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.settings_section_title));
		this.setArguments(bundle);
		this.ma = MainActivity.getMainActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.settings_section, container, false);
		rootView.findViewById(R.id.bSettings).setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		ma.startActivity(new Intent(ma, PrefsActivity.class));
	}
}
