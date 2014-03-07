package at.rene8888.schooltoolforwebuntis.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.MainActivity;
import at.rene8888.schooltoolforwebuntis.R;

public class WelcomeSection extends Fragment implements OnClickListener {
	private ApplicationClass app;

	public WelcomeSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.welcome_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.welcome_section, container, false);
		app = (ApplicationClass) MainActivity.getMainActivity().getApplication();
		rootView.findViewById(R.id.buttonSave).setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View arg0) {
		EditText user = (EditText) MainActivity.getMainActivity().findViewById(R.id.editText1);
		EditText pw = (EditText) MainActivity.getMainActivity().findViewById(R.id.editText2);
		app.setUsername(user.getText().toString());
		app.setPassword(pw.getText().toString());
		app.saveChanges();
	}
}
