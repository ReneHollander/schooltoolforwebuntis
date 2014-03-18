package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.io.File;
import java.io.FileOutputStream;

import org.json.JSONArray;

import android.os.Environment;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.department.Departments;
import at.rene8888.schooltoolforwebuntis.data.webuntis.room.Rooms;
import at.rene8888.schooltoolforwebuntis.data.webuntis.schoolclasses.SchoolClasses;
import at.rene8888.schooltoolforwebuntis.data.webuntis.subject.Subjects;
import at.rene8888.schooltoolforwebuntis.data.webuntis.teacher.Teachers;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid.TimeGrids;

public class Data {

	private static Data DATA;

	private TimeGrids timeGrids;
	private TimeTables timeTables;
	private Rooms rooms;
	private SchoolClasses schoolClasses;
	private Departments departments;
	private Subjects subjects;
	private Teachers teachers;

	// TODO ADD COLORS WHERE NEEDED
	//
	// try {
	// JSONArray ja = (JSONArray)
	// ApplicationClass.getApplication().getWebUntisRequests().getData("getTeachers",
	// null);
	// File sd = Environment.getExternalStorageDirectory();
	// File file = new File(sd, "testFile.txt");
	// FileOutputStream fos = new FileOutputStream(file);
	// fos.write(ja.toString(4).getBytes());
	// fos.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }

	public Data() {
		DATA = this;

		try {
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getTimegridUnits", null);
			File sd = Environment.getExternalStorageDirectory();
			File file = new File(sd, "testFile.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(ja.toString(4).getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void activateAll() {
		this.getDepartments();
		this.getRooms();
		this.getSchoolClasses();
		this.getSubjects();
		this.getTeachers();
		this.getTimeGrids();
	}

	public TimeGrids getTimeGrids() {
		if (this.timeGrids == null) {
			this.timeGrids = new TimeGrids();
		}
		return this.timeGrids;
	}

	public TimeTables getTimeTable() {
		if (this.timeTables == null) {
			this.timeTables = new TimeTables();
		}
		return this.timeTables;
	}

	public Rooms getRooms() {
		if (this.rooms == null) {
			this.rooms = new Rooms();
		}
		return this.rooms;
	}

	public SchoolClasses getSchoolClasses() {
		if (this.schoolClasses == null) {
			this.schoolClasses = new SchoolClasses();
		}
		return this.schoolClasses;
	}

	public Departments getDepartments() {
		if (this.departments == null) {
			this.departments = new Departments();
		}
		return this.departments;
	}

	public Subjects getSubjects() {
		if (this.subjects == null) {
			this.subjects = new Subjects();
		}
		return this.subjects;
	}

	public Teachers getTeachers() {
		if (this.teachers == null) {
			this.teachers = new Teachers();
		}
		return this.teachers;
	}

	public static Data getData() {
		if (DATA == null) {
			DATA = new Data();
		}
		return DATA;
	}
}
