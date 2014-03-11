package at.rene8888.schooltoolforwebuntis.data.webuntis.objects;

public class Room {

	private int id;
	private String name;
	private String longName;
	private String building;
	private boolean active;

	public Room(int id, String name, String longName, String building, boolean active) {
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.building = building;
		this.active = active;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getLongName() {
		return this.longName;
	}

	public String getBuilding() {
		return this.building;
	}

	public boolean isActive() {
		return this.active;
	}

	public String toString() {
		return "[Id=" + this.id + "; Name=" + this.name + "; LongName=" + this.longName + "; Building=" + this.building + "; Active=" + this.active + "]";
	}

}
