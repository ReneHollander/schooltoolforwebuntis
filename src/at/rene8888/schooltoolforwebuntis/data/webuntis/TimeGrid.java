package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.Util;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.UnitTag;

public class TimeGrid {

	private ArrayList<Unit> unitList;

	public ArrayList<Unit> getUnitList() {
		if (unitList == null) {
			try {
				this.fillList();
			} catch (Exception e) {
				Log.d("webuntis", "Error while trying to fill timegrid", e);
			}
		}
		return unitList;
	}

	private void fillList() throws JSONException, IOException {
		ArrayList<Unit> rawUnits = new ArrayList<Unit>();
		JSONArray arr = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getTimegridUnits", null);
		// TODO get right day
		JSONObject currjson = arr.getJSONObject(0);
		JSONArray units = currjson.getJSONArray("timeUnits");
		for (int j = 0; j < units.length(); j++) {
			JSONObject unit = units.getJSONObject(j);
			int startTime = unit.getInt("startTime");
			int endTime = unit.getInt("endTime");
			Unit u = new Unit(Util.createTime(startTime + ""), Util.createTime(endTime + ""), UnitTag.LESSON);
			rawUnits.add(u);
		}

		this.unitList = new ArrayList<Unit>();
		
		for (int i = 0; i < rawUnits.size(); i++) {
			if (i != 0) {
				Unit prev = rawUnits.get(i - 1);
				Unit curr = rawUnits.get(i);
				if (prev.getEnd().equals(curr.getStart()) == false) {
					Unit pause = new Unit(prev.getEnd(), curr.getStart(), UnitTag.BREAK);
					this.unitList.add(prev);
					this.unitList.add(pause);
				} else if (prev.getEnd().equals(curr.getStart())) {
					this.unitList.add(prev);
				}
			}
			if (i == (rawUnits.size() - 1)) {
				this.unitList.add(rawUnits.get(i));
			}
		}

	}

}
