package at.rene8888.schooltoolforwebuntis.data.webuntis.network;

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
		JSONObject inparam = new JSONObject();
		JSONObject in = new JSONObject();
		JSONObject ret = null;
		try {
			inparam.put("user", this.username);
			inparam.put("password", this.password);

			in.put("jsonrpc", "2.0");
			in.put("id", "1");
			in.put("method", "authenticate");
			in.put("params", inparam);

			String injson = in.toString();

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", String.valueOf(injson.length()));

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			writer.write(injson);
			writer.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			writer.close();
			reader.close();
			ret = new JSONObject(sb.toString());
			return ret.getJSONObject("result").getString("sessionId");
		} catch (Exception e) {
			Log.e("request", "error while trying to get sessionid", e);
			Log.d("json", in.toString());
			Log.d("json", ret.toString());
			return null;
		}
	}

}
