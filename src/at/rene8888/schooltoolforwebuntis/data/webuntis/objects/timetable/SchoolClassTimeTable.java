package at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Room;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.SchoolClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Subject;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Teacher;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;
import at.rene8888.schooltoolforwebuntis.util.Time;
import at.rene8888.schooltoolforwebuntis.util.Util;
import at.rene8888.schooltoolforwebuntis.util.webuntis.SchoolClassTimeTableSorter;

public class SchoolClassTimeTable {

	public StringBuilder sb = new StringBuilder();

	private ArrayList<TimeTableEntry<SchoolClassTimeTableUnit>> units;

	private SchoolClass schoolClass;
	private String date;

	public SchoolClassTimeTable(SchoolClass schoolClass, String date) {
		this.schoolClass = schoolClass;
		this.date = date;
	}

	private void fillList() {
		try {
			this.units = new ArrayList<TimeTableEntry<SchoolClassTimeTableUnit>>();

			Data data = Data.getData();

			JSONObject params = new JSONObject();
			params.put("id", this.schoolClass.getId());
			params.put("type", "1");
			params.put("startDate", this.date);
			params.put("endDate", this.date);

			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getTimetable", params);

			for (int i = 0; i < ja.length(); i++) {
				JSONObject curr = ja.getJSONObject(i);

				Time start = Util.createTime(curr.getString("startTime"));
				Time end = Util.createTime(curr.getString("endTime"));
				Unit unit = data.getTimeGrid().getUnitByTime(start, end);

				ArrayList<Room> rooms = new ArrayList<Room>();
				JSONArray roomarr = curr.getJSONArray("ro");
				if (roomarr.length() == 1) {
					rooms.add(data.getRooms().getRoomById(roomarr.getJSONObject(0).getInt("id")));
				} else {
					for (int j = 0; j < roomarr.length(); j++) {
						JSONObject currroom = roomarr.getJSONObject(j);
						rooms.add(data.getRooms().getRoomById(currroom.getInt("id")));
					}
				}

				ArrayList<Subject> subjects = new ArrayList<Subject>();
				JSONArray subjectarr = curr.getJSONArray("su");
				if (subjectarr.length() == 1) {
					subjects.add(data.getSubjects().getSubjectById(subjectarr.getJSONObject(0).getInt("id")));
				} else {
					for (int j = 0; j < subjectarr.length(); j++) {
						JSONObject currsubject = subjectarr.getJSONObject(j);
						subjects.add(data.getSubjects().getSubjectById(currsubject.getInt("id")));
					}
				}

				ArrayList<Teacher> teachers = new ArrayList<Teacher>();
				JSONArray teacherarr = curr.getJSONArray("te");
				if (teacherarr.length() == 1) {
					teachers.add(data.getTeachers().getTeacherById(teacherarr.getJSONObject(0).getInt("id")));
				} else {
					for (int j = 0; j < teacherarr.length(); j++) {
						JSONObject currteacher = teacherarr.getJSONObject(j);
						teachers.add(data.getTeachers().getTeacherById(currteacher.getInt("id")));
					}
				}

				ArrayList<SchoolClass> schoolclasses = new ArrayList<SchoolClass>();
				JSONArray schoolclassarr = curr.getJSONArray("kl");
				if (schoolclassarr.length() == 1) {
					schoolclasses.add(data.getSchoolClasses().getSchoolClassById(schoolclassarr.getJSONObject(0).getInt("id")));
				} else {
					for (int j = 0; j < schoolclassarr.length(); j++) {
						JSONObject currschoolclass = schoolclassarr.getJSONObject(j);
						schoolclasses.add(data.getSchoolClasses().getSchoolClassById(currschoolclass.getInt("id")));
					}
				}

				TimeTableEntry<SchoolClassTimeTableUnit> tte = new TimeTableEntry<SchoolClassTimeTableUnit>(unit, new SchoolClassTimeTableUnit(unit, curr.getInt("id"), rooms, subjects, teachers, schoolclasses));
				this.units.add(tte);

			}

			Collections.sort(this.units, new SchoolClassTimeTableSorter());

			Unit first = this.units.get(0).getUnit();
			Unit last = this.units.get(this.units.size() - 1).getUnit();

			for (Unit u : data.getTimeGrid().getUnitList()) {
				if (u.after(first) && u.before(last)) {
					if (containsUnit(u, this.units) == false) {
						this.units.add(new TimeTableEntry<SchoolClassTimeTableUnit>(u, null));
					}
				}
			}

			Collections.sort(this.units, new SchoolClassTimeTableSorter());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<TimeTableEntry<SchoolClassTimeTableUnit>> getUnits() {
		if (this.units == null) {
			this.fillList();
		}
		return this.units;
	}

	private static boolean containsUnit(Unit other, List<TimeTableEntry<SchoolClassTimeTableUnit>> list) {
		for (TimeTableEntry<SchoolClassTimeTableUnit> entry : list) {
			if (entry.getUnit().equals(other)) {
				return true;
			}
		}
		return false;
	}

}
