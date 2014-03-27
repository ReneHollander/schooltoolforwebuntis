package at.rene8888.schooltoolforwebuntis.gui.activity;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;

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
		EditText url = (EditText) this.findViewById(R.id.editText1);
		EditText user = (EditText) this.findViewById(R.id.editText2);
		EditText pw = (EditText) this.findViewById(R.id.editText3);
		try {
			app.setURL(new URL(url.getText().toString()));
			app.setUsername(user.getText().toString());
			app.setPassword(pw.getText().toString());
			app.saveChanges();
			goToMain();
		} catch (MalformedURLException e) {
			Log.e("WelcomeActivity", "Invalid URL", e);
			Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show();
		}
		
	}

	public void goToMain() {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

}
