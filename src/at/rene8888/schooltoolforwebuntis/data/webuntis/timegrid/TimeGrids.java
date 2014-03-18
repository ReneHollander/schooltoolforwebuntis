package at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.util.Time;

public class TimeGrids {

	private SparseArray<TimeGrid> timeGrids;

	private void fillList() throws JSONException, IOException {

		this.timeGrids = new SparseArray<TimeGrid>();
		JSONArray arr = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getTimegridUnits", null);

		for (int i = 0; i < arr.length(); i++) {
			JSONObject currjson = arr.getJSONObject(i);

			ArrayList<Unit> rawUnits = new ArrayList<Unit>();
			JSONArray units = currjson.getJSONArray("timeUnits");

			Unit curr = null;
			Unit last = null;
			for (int j = 0; j < units.length(); j++) {
				JSONObject unit = units.getJSONObject(j);
				int startTime = unit.getInt("startTime");
				int endTime = unit.getInt("endTime");
				curr = new Unit(Time.createTime(startTime + ""), Time.createTime(endTime + ""), UnitType.LESSON);

				if (j != 0) {
					if (last.getEnd().equals(curr.getStart()) == false) {
						Unit pause = new Unit(last.getEnd(), curr.getStart(), UnitType.BREAK);
						rawUnits.add(last);
						rawUnits.add(pause);
					} else if (last.getEnd().equals(curr.getStart())) {
						rawUnits.add(last);
					}
				}

				if (j == (rawUnits.size() - 1)) {
					rawUnits.add(curr);
				}

				last = curr;
			}

			this.timeGrids.put(currjson.getInt("day"), new TimeGrid(rawUnits));

		}
	}

	public SparseArray<TimeGrid> getTimeGrids() {
		if (this.timeGrids == null) {
			try {
				this.fillList();
			} catch (Exception e) {
				Log.d("webuntis", "Error while trying to fill timegrid", e);
			}
		}
		return this.timeGrids;
	}

	public TimeGrid getTimeGridByKey(int key) {
		return this.getTimeGrids().get(key);
	}

	public TimeGrid getTimeGridByCalendar(Calendar cal) {
		return this.getTimeGrids().get(cal.get(Calendar.DAY_OF_WEEK));
	}

	public int getTimeGridCount() {
		return this.getTimeGrids().size();
	}

}
