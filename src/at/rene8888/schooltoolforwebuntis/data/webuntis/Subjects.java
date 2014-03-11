package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Subject;

public class Subjects {

	private SparseArray<Subject> subjects;

	private void fillList() {
		try {
			this.subjects = new SparseArray<Subject>();
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getSubjects", null);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject curr = ja.getJSONObject(i);
				Subject s = new Subject(curr.getInt("id"), curr.getString("name"), curr.getString("longName"), curr.getBoolean("active"));
				this.addSubject(s);
			}
		} catch (Exception e) {
			Log.e("request", "error while getting departments", e);
		}

	}

	public void addSubject(Subject s) {
		this.subjects.put(s.getId(), s);
	}

	public Subject getSubjectById(int id) {
		if (this.subjects == null) {
			this.fillList();
		}
		return this.subjects.get(id);
	}

	public SparseArray<Subject> getAllSubjects() {
		if (this.subjects == null) {
			this.fillList();
		}
		return this.subjects;
	}

	public ArrayList<Subject> getAllSubjectsAsArrayList() {
		if (this.subjects == null) {
			this.fillList();
		}
		ArrayList<Subject> ret = new ArrayList<Subject>();
		for (int i = 0; i < this.subjects.size(); i++) {
			ret.add(this.subjects.get(this.subjects.keyAt(i)));
		}
		return ret;
	}

}
