package at.rene8888.schooltoolforwebuntis.data.webuntis.objects.timetable;

import java.util.ArrayList;

import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Room;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.SchoolClass;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Subject;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Teacher;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;

public class SchoolClassTimeTableUnit {

	private Unit unit;
	private int id;
	private ArrayList<Room> rooms;
	private ArrayList<Subject> subjects;
	private ArrayList<Teacher> teachers;
	private ArrayList<SchoolClass> schoolclasses;

	public SchoolClassTimeTableUnit(Unit unit, int id, ArrayList<Room> rooms, ArrayList<Subject> subjects, ArrayList<Teacher> teachers, ArrayList<SchoolClass> schoolclasses) {
		this.unit = unit;
		this.id = id;
		this.rooms = rooms;
		this.subjects = subjects;
		this.teachers = teachers;
		this.schoolclasses = schoolclasses;
	}

	public Unit getUnit() {
		return unit;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public ArrayList<SchoolClass> getSchoolclasses() {
		return schoolclasses;
	}

	@Override
	public String toString() {
		return "SchoolClassTimeTableUnit [unit=" + unit + ", id=" + id + ", rooms=" + rooms + ", subjects=" + subjects + ", teachers=" + teachers + ", schoolclasses=" + schoolclasses + "]";
	}
}
