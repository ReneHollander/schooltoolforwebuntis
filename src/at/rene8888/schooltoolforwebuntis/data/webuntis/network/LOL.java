package at.rene8888.schooltoolforwebuntis.data.webuntis.network;

import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class LOL {

	public LOL() {

		try {
			Task task = new Task();
			for (int i = 0; i < 10; i++) {
				task.execute().get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class Task extends AsyncTask<Void, Void, Void> {

		// TODO make it a loop to publish to progress
		
		protected Void doInBackground(Void... nothing) {
			// Creating HTTP client

			HttpClient httpClient = new DefaultHttpClient();

			// Creating HTTP Post
			HttpPost httpPost = new HttpPost("http://www.google.at/");
			BasicHttpParams params = new BasicHttpParams();
			httpPost.setParams(params);
			// params.setParameter("Content-Type", "application/json");
			// params.setParameter("Content-Length",
			// String.valueOf(injson.length()));
			// params.setParameter("Cookie", "JSESSIONID=" + this.sessionID);

			try {
				httpPost.setEntity(new StringEntity(""));
				HttpResponse response = httpClient.execute(httpPost);
				String body = EntityUtils.toString(response.getEntity());
				Log.w("Http Response:", body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
