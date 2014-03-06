package at.rene8888.schooltoolforwebuntis.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class WebUntisRequests {

	public static String getSessionID(URL url, String username, String password) throws JSONException, IOException {

		JSONObject json = new JSONObject();
		JSONObject params = new JSONObject();

		params.put("user", username);
		params.put("password", password);

		json.put("jsonrpc", "2.0");
		json.put("id", "1");
		json.put("method", "authenticate");
		json.put("params", params);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length", String.valueOf(json.toString().length()));

		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

		writer.write(json.toString());
		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String output = "";
		String line = "";
		while ((line = reader.readLine()) != null) {
			output += line;
		}

		writer.close();
		reader.close();

		JSONObject jsonobject = new JSONObject(output);

		Log.d("request", jsonobject.toString());

		return jsonobject.getJSONObject("result").getString("sessionId");

	}

	public static JSONObject getData(URL url, String sessionID, JSONObject json) throws JSONException, IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length", String.valueOf(json.toString().length()));
		connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionID);

		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

		writer.write(json.toString());
		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String output = "";
		String line = "";
		while ((line = reader.readLine()) != null) {
			output += line;
		}

		writer.close();
		reader.close();

		return new JSONObject(output);

	}

}
