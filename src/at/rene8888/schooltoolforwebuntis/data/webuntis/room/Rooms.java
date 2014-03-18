package at.rene8888.schooltoolforwebuntis.data.webuntis.room;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;

public class Rooms {

	private SparseArray<Room> rooms;

	private void fillList() {
		try {
			this.rooms = new SparseArray<Room>();
			JSONArray ja = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getRooms", null);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject curr = ja.getJSONObject(i);
				Room d = new Room(curr.getInt("id"), curr.getString("name"), curr.getString("longName"), curr.getString("building"), curr.getBoolean("active"));
				this.addRoom(d);
			}
		} catch (Exception e) {
			Log.e("request", "error while getting departments", e);
		}

	}

	public void addRoom(Room r) {
		this.rooms.put(r.getId(), r);
	}

	public Room getRoomById(int id) {
		if (this.rooms == null) {
			this.fillList();
		}
		return this.rooms.get(id);
	}

	public SparseArray<Room> getAllRooms() {
		if (this.rooms == null) {
			this.fillList();
		}
		return this.rooms;
	}

	public ArrayList<Room> getAllRoomsAsArrayList() {
		if (this.rooms == null) {
			this.fillList();
		}
		ArrayList<Room> ret = new ArrayList<Room>();
		for (int i = 0; i < this.rooms.size(); i++) {
			ret.add(this.rooms.get(this.rooms.keyAt(i)));
		}
		return ret;
	}

}
