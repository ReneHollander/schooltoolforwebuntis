package at.rene8888.schooltoolforwebuntis.data.webuntis.department;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;

public class Departments {

	private SparseArray<Department> departments;

	private void fillList() {
		try {
			this.departments = new SparseArray<Department>();
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getDepartments", null);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject curr = ja.getJSONObject(i);
				Department d = new Department(curr.getInt("id"), curr.getString("name"), curr.getString("longName"));
				this.addDepartment(d);
			}
		} catch (Exception e) {
			Log.e("request", "error while getting departments", e);
		}

	}

	public void addDepartment(Department d) {
		this.departments.put(d.getId(), d);
	}

	public Department getDepartmentById(int id) {
		if (this.departments == null) {
			this.fillList();
		}
		return this.departments.get(id);
	}

	public SparseArray<Department> getAllDepartments() {
		if (this.departments == null) {
			this.fillList();
		}
		return this.departments;
	}

	public ArrayList<Department> getAllDepartmentsAsArrayList() {
		if (this.departments == null) {
			this.fillList();
		}
		ArrayList<Department> ret = new ArrayList<Department>();
		for (int i = 0; i < this.departments.size(); i++) {
			ret.add(this.departments.get(this.departments.keyAt(i)));
		}
		return ret;
	}

}
