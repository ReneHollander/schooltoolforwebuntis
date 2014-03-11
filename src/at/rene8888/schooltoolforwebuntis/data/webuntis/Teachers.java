package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Environment;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Teacher;

public class Teachers {

	private Map<String, Teacher> teachers;

	public Teachers() {

		try {
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getDepartments", null);

			// for (int i = 0; i < ja.length(); i++) {
			// JSONObject curr = ja.getJSONObject(i);
			//
			// }
			File sd = Environment.getExternalStorageDirectory();
			File file = new File(sd, "testFile.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(ja.toString(4).getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
