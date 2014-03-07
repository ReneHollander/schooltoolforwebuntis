package at.rene8888.schooltoolforwebuntis.section;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.MainActivity;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.test.Unit;
import at.rene8888.schooltoolforwebuntis.test.UnitTag;
import at.rene8888.schooltoolforwebuntis.test.Updater;
import at.rene8888.schooltoolforwebuntis.test.Util;

public class ClockSection extends Fragment {
	private ApplicationClass app;

	public ClockSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.clock_section_title));
		this.setArguments(bundle);
		this.app = (ApplicationClass) MainActivity.getMainActivity().getApplication();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.clock_section, container, false);

		getData();
		return rootView;
	}

	public void getData() {

		TextView tv = (TextView) MainActivity.getMainActivity().findViewById(R.id.textViewTime);

		List<Unit> unitList = new ArrayList<Unit>();

		try {
			JSONArray arr = (JSONArray) this.app.getWebUntisRequests().getData("getTimegridUnits", null);
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

			new Updater(newUnitList, tv).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
