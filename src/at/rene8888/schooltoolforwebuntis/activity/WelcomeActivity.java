package at.rene8888.schooltoolforwebuntis.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.R;

public class WelcomeActivity extends Activity implements View.OnClickListener {

	private ApplicationClass app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		app = (ApplicationClass) this.getApplication();
		findViewById(R.id.buttonSave).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		EditText user = (EditText) this.findViewById(R.id.editText1);
		EditText pw = (EditText) this.findViewById(R.id.editText2);
		app.setUsername(user.getText().toString());
		app.setPassword(pw.getText().toString());
		app.saveChanges();
		goToMain();
	}

	public void goToMain() {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

}
