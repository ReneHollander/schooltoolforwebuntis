package at.rene8888.schooltoolforwebuntis.data.webuntis.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import android.os.AsyncTask;

public class WebRequest {

	public String execute(URL url, String input, Map<String, String> params) throws Exception {
		return new Task(url, input, params).execute().get().getResult();
	}

	private class Task extends AsyncTask<Void, Void, TaskResult<String>> {
		private URL url;
		private String input;
		private Map<String, String> params;

		public Task(URL url, String input, Map<String, String> params) {
			this.url = url;
			this.input = input;
			this.params = params;
			// System.out.println(input);
		}

		protected TaskResult<String> doInBackground(Void... nothing) {
			try {

				HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setUseCaches(false);

				for (Map.Entry<String, String> entry : this.params.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}

				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

				writer.write(this.input);
				writer.flush();

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				StringBuffer sb = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				writer.close();
				reader.close();

				return new TaskResult<String>(sb.toString());
			} catch (Exception e) {
				return new TaskResult<String>(e);
			}
		}
	}

	private class TaskResult<T> {

		private T result;
		private Exception error;

		public TaskResult(T result) {
			this.result = result;
			this.error = null;
		}

		public TaskResult(Exception error) {
			this.result = null;
			this.error = error;
		}

		public T getResult() throws Exception {
			if (this.error == null) {
				return this.result;
			} else {
				throw error;
			}
		}
	}

}