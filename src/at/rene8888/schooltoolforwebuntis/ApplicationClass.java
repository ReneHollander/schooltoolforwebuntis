package at.rene8888.schooltoolforwebuntis;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ApplicationClass extends Application {
	private String username;
	private String password;
	private SharedPreferences prefs;
	private Editor editor;
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.prefs = getSharedPreferences("at.rene8888.schooltoolforwebuntis", Context.MODE_PRIVATE);
		this.editor = prefs.edit();
		this.update();
	}

	public void update() {
		this.username = prefs.getString("username", null);
		this.password = prefs.getString("password", null);
	}
	
	public void saveChanges() {
		editor.putString("username", this.getUsername());
		editor.putString("password", this.getPassword());
		editor.commit();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
