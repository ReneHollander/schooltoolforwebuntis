package at.rene8888.schooltoolforwebuntis.data.webuntis.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import at.rene8888.schooltoolforwebuntis.data.Parameter;

public class GetDataTask extends AsyncTask<Parameter, Void, Object> {

	private URL url;
	private String sessionID;

	public GetDataTask(URL url, String sessionID) {
		this.url = url;
		this.sessionID = sessionID;
	}

	@Override
	protected Object doInBackground(Parameter... params) {
		if (params.length == 1) {
			Parameter param = params[0];
			JSONObject inparam = param.getParams();
			JSONObject in = new JSONObject();
			JSONObject ret = null;
			try {
				in.put("jsonrpc", "2.0");
				in.put("id", "1");
				in.put("method", param.getMethod());
				in.put("params", inparam);

				String injson = in.toString();

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("Content-Length", String.valueOf(injson.length()));
				connection.setRequestProperty("Cookie", "JSESSIONID=" + this.sessionID);

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
				return ret.get("result");
			} catch (Exception e) {
				Log.e("request", "errir while trying to get sessionid", e);
				Log.d("json", in.toString());
				Log.d("json", ret.toString());
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
