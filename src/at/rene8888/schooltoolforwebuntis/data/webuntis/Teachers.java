package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Department;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Teacher;

public class Teachers {

	private SparseArray<Teacher> teachers;

	private void fillList() {
		try {
			this.teachers = new SparseArray<Teacher>();
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getTeachers", null);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject curr = ja.getJSONObject(i);

				ArrayList<Department> dlist = new ArrayList<Department>();
				JSONArray dids = curr.getJSONArray("dids");
				for (int j = 0; j < dids.length(); j++) {
					dlist.add(Data.getData().getDepartments().getDepartmentById(dids.getJSONObject(j).getInt("id")));
				}
				Teacher t = new Teacher(curr.getInt("id"), curr.getString("name"), curr.getString("foreName"), curr.getString("longName"), curr.getBoolean("active"), dlist);
				this.addTeacher(t);
			}
		} catch (Exception e) {
			Log.e("request", "error while getting departments", e);
		}
	}

	public void addTeacher(Teacher t) {
		this.teachers.put(t.getId(), t);
	}

	public Teacher getTeacherById(int id) {
		if (this.teachers == null) {
			this.fillList();
		}
		return this.teachers.get(id);
	}

	public SparseArray<Teacher> getAllTeachers() {
		if (this.teachers == null) {
			this.fillList();
		}
		return this.teachers;
	}

	public ArrayList<Teacher> getAllTeachersAsArrayList() {
		if (this.teachers == null) {
			this.fillList();
		}
		ArrayList<Teacher> ret = new ArrayList<Teacher>();
		for (int i = 0; i < this.teachers.size(); i++) {
			ret.add(this.teachers.get(this.teachers.keyAt(i)));
		}
		return ret;
	}

}
