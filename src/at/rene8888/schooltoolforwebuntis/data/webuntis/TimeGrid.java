package at.rene8888.schooltoolforwebuntis.data.webuntis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		this.unitList = new ArrayList<Unit>();
		JSONArray arr = (JSONArray) ApplicationClass.getApplication().getWebUntisRequests().getData("getTimegridUnits", null);
		// TODO get right day
		JSONObject currjson = arr.getJSONObject(0);
		JSONArray units = currjson.getJSONArray("timeUnits");
		for (int j = 0; j < units.length(); j++) {
			JSONObject unit = units.getJSONObject(j);
			int startTime = unit.getInt("startTime");
			int endTime = unit.getInt("endTime");
			Unit u = new Unit(Util.createTime(startTime + ""), Util.createTime(endTime + ""), UnitTag.LESSON);
			unitList.add(u);
		}

		List<Unit> newUnitList = new ArrayList<Unit>(unitList.size());

		for (int i = 0; i < unitList.size(); i++) {
			if (i != 0) {
				Unit prev = unitList.get(i - 1);
				Unit curr = unitList.get(i);
				if (prev.getEnd().equals(curr.getStart()) == false) {
					Unit pause = new Unit(prev.getEnd(), curr.getStart(), UnitTag.BREAK);
					newUnitList.add(prev);
					newUnitList.add(pause);
				} else if (prev.getEnd().equals(curr.getStart())) {
					newUnitList.add(prev);
				}
			}
			if (i == (unitList.size() - 1)) {
				newUnitList.add(unitList.get(i));
			}
		}
		
	}

}
