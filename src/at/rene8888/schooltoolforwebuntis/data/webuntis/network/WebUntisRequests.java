package at.rene8888.schooltoolforwebuntis.data.webuntis.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.activity.MainActivity;
import at.rene8888.schooltoolforwebuntis.exception.UnexceptedErrorException;
import at.rene8888.schooltoolforwebuntis.exception.UnexpectedResultException;

public class WebUntisRequests {

	private ApplicationClass app;

	private String sessionID;

	private WebRequest webRequest;

	public WebUntisRequests(ApplicationClass app) {
		this.app = (ApplicationClass) MainActivity.getMainActivity().getApplication();
		this.webRequest = new WebRequest();
	}

	public void getSessionID() {
		JSONObject inparam = new JSONObject();
		JSONObject in = new JSONObject();
		JSONObject ret = null;
		try {

			inparam.put("user", this.app.getUsername());
			inparam.put("password", this.app.getPassword());
			inparam.put("client", "CLIENT");

			in.put("params", inparam);
			in.put("jsonrpc", "2.0");
			in.put("id", "1");
			in.put("method", "authenticate");

			String inputString = in.toString();

			Map<String, String> params = new HashMap<String, String>();
			params.put("Content-Type", "application/json");
			params.put("Content-Length", String.valueOf(inputString.length()));

			ret = new JSONObject(this.webRequest.execute(this.app.getURL(), inputString, params));
			if (ret.has("result")) {
				this.sessionID = ret.getJSONObject("result").getString("sessionId");
			} else if (ret.has("error")) {
				JSONObject jsonerror = ret.getJSONObject("error");
				throw new UnexpectedResultException(in, jsonerror.getString("message"), jsonerror.getInt("code"));
			} else {
				throw new UnexceptedErrorException();
			}
		} catch (Exception e) {
			Log.e("request", "error while trying to get sessionid", e);
			Log.d("json", in.toString());
			Log.d("json", ret.toString());
		}
	}

	public Object getData(String method, JSONObject inparam) throws JSONException, IOException {
		JSONObject in = new JSONObject();
		JSONObject ret = null;
		try {
			in.put("jsonrpc", "2.0");
			in.put("id", "1");
			in.put("method", method);
			in.put("params", inparam);

			String input = in.toString();

			Map<String, String> params = new HashMap<String, String>();
			params.put("Content-Type", "application/json");
			params.put("Content-Length", String.valueOf(input.length()));
			params.put("Cookie", "JSESSIONID=" + this.sessionID);

			ret = new JSONObject(this.webRequest.execute(this.app.getURL(), input, params));
			if (ret.has("result")) {
				return ret.get("result");
			} else if (ret.has("error")) {
				JSONObject jsonerror = ret.getJSONObject("error");
				throw new UnexpectedResultException(in, jsonerror.getString("message"), jsonerror.getInt("code"));
			} else {
				throw new UnexceptedErrorException();
			}
		} catch (Exception e) {
			Log.e("request", "error while trying to get data", e);
			Log.d("json", in.toString());
			Log.d("json", ret.toString());
			return null;
		}
	}
}
