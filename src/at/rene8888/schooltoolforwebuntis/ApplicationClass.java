package at.rene8888.schooltoolforwebuntis;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import at.rene8888.schooltoolforwebuntis.data.webuntis.network.WebUntisRequests;

public class ApplicationClass extends Application {

	private static ApplicationClass app;

	private URL url;
	private String username;
	private String password;
	private SharedPreferences prefs;
	private Editor editor;
	private WebUntisRequests req;

	public ApplicationClass() {
		app = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.prefs = getSharedPreferences("at.rene8888.schooltoolforwebuntis", Context.MODE_PRIVATE);
		this.editor = prefs.edit();
		this.update();

		try {
			this.url = new URL("https://stpl.tgm.ac.at/WebUntis/jsonrpc.do?school=tgm");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

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

	public URL getURL() {
		return this.url;
	}

	public WebUntisRequests getWebUntisRequests() {
		if (req == null) {
			this.req = new WebUntisRequests(this);
		}
		if (this.getPassword() != null && this.getUsername() != null) {
			this.req.getSessionID();
		}
		return this.req;
	}

	public static ApplicationClass getApplication() {
		return app;
	}
}
