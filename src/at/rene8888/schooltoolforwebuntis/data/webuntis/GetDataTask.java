package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import at.rene8888.schooltoolforwebuntis.data.Parameter;

public class GetDataTask extends AsyncTask<Parameter, Void, JSONObject> {

	private URL url;
	private String sessionID;

	public GetDataTask(URL url, String sessionID) {
		this.url = url;
		this.sessionID = sessionID;
	}

	@Override
	protected JSONObject doInBackground(Parameter... params) {
		if (params.length == 1) {
			try {
				Parameter param = params[0];

				JSONObject packet = new JSONObject();
				packet.put("method", param.getMethod());
				if (param.getParams() != null) {
					packet.put("params", param.getParams());
				}
				packet.put("jsonrpc", "2.0");
				packet.put("id", "1");

				String json = packet.toString();

				HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("Content-Length", String.valueOf(json.length()));
				connection.setRequestProperty("Cookie", "JSESSIONID=" + this.sessionID);

				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

				writer.write(json.toString());
				writer.flush();

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				StringBuffer sb = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				writer.close();
				reader.close();

				return new JSONObject(sb.toString());
			} catch (Exception e) {
				Log.e("request", "error while trying to get data", e);
				return null;
			}
		} else {
			return null;
		}
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

}
