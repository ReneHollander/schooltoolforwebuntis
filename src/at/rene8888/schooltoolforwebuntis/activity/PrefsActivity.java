package at.rene8888.schooltoolforwebuntis.activity;

import android.app.Activity;
import android.os.Bundle;
import at.rene8888.schooltoolforwebuntis.section.PrefsFragment;

public class PrefsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment()).commit();
	}
}
