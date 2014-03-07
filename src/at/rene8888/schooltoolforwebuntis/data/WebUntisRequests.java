package at.rene8888.schooltoolforwebuntis.data;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.MainActivity;

public class WebUntisRequests {

	private ApplicationClass app;

	private String sessionID;

	private GetDataTask gdt;

	public WebUntisRequests(ApplicationClass app) {
		this.app = (ApplicationClass) MainActivity.getMainActivity().getApplication();
	}

	public void getSessionID() {
		try {
			GetSessionTask gst = new GetSessionTask(this.app.getURL(), this.app.getUsername(), this.app.getPassword());
			this.sessionID = gst.execute().get();
			this.gdt = new GetDataTask(this.app.getURL(), this.sessionID);
		} catch (Exception e) {
			Log.d("request", "error while trying to get sessionid", e);
		}
	}

	public Object getData(String method, JSONObject params) throws JSONException, IOException {
		try {
			Parameter parameter = new Parameter(method, params);

			return this.gdt.execute(parameter).get().get("result");
		} catch (Exception e) {
			Log.d("request", "error while trying to get data", e);
			return null;
		}
	}

}
