package at.rene8888.schooltoolforwebuntis.gui.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.gui.activity.MainActivity;

public class PrefsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		Preference button = (Preference) findPreference("resetButton");
		button.setOnPreferenceClickListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		MainActivity.getMainActivity().updateScreenLock();
		// TODO: Update Timer
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		ApplicationClass app = ApplicationClass.getApplication();
		if (preference.getKey().equals("resetButton")) {
			app.setDelay(0);
			app.saveChanges();
		}
		return true;
	}
}
