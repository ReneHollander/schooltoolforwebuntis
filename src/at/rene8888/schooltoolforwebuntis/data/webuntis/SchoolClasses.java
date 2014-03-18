package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Department;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.SchoolClass;

public class SchoolClasses {

	private SparseArray<SchoolClass> schoolClasses;

	private void fillList() {
		try {
			this.schoolClasses = new SparseArray<SchoolClass>();
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getKlassen", null);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject curr = ja.getJSONObject(i);

				Department d = null;
				if (curr.has("did")) {
					d = Data.getData().getDepartments().getDepartmentById(curr.getInt("did"));
				}
				SchoolClass s = new SchoolClass(curr.getInt("id"), curr.getString("name"), curr.getString("longName"), d, curr.getBoolean("active"));
				this.addSchoolClass(s);
			}
		} catch (Exception e) {
			Log.e("request", "error while getting departments", e);
		}

	}

	public void addSchoolClass(SchoolClass r) {
		this.schoolClasses.put(r.getId(), r);
	}

	public SchoolClass getSchoolClassById(int id) {
		if (this.schoolClasses == null) {
			this.fillList();
		}
		return this.schoolClasses.get(id);
	}

	public SparseArray<SchoolClass> getAllSchoolClasses() {
		if (this.schoolClasses == null) {
			this.fillList();
		}
		return this.schoolClasses;
	}

	public ArrayList<SchoolClass> getAllSchoolClassesAsArrayList() {
		if (this.schoolClasses == null) {
			this.fillList();
		}
		ArrayList<SchoolClass> ret = new ArrayList<SchoolClass>();
		for (int i = 0; i < this.schoolClasses.size(); i++) {
			ret.add(this.schoolClasses.get(this.schoolClasses.keyAt(i)));
		}
		return ret;
	}

}
