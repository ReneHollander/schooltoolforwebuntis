package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class GetSessionTask extends AsyncTask<Void, Void, String> {

	private URL url;
	private String username;
	private String password;

	public GetSessionTask(URL url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	protected String doInBackground(Void... param) {
		try {
			JSONObject json = new JSONObject();
			JSONObject params = new JSONObject();
			params.put("user", this.username);
			params.put("password", this.password);

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

			Log.d("json", json.toString());
			Log.d("json", jsonobject.toString());

			return jsonobject.getJSONObject("result").getString("sessionId");
		} catch (Exception e) {
			Log.e("request", "errir while trying to get sessionid", e);
			return null;
		}
	}

}
