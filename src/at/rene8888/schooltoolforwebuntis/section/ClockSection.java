package at.rene8888.schooltoolforwebuntis.section;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.MainActivity;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.test.Unit;
import at.rene8888.schooltoolforwebuntis.test.UnitTag;
import at.rene8888.schooltoolforwebuntis.test.Updater;
import at.rene8888.schooltoolforwebuntis.test.Util;
import at.rene8888.schooltoolforwebuntis.test.WebUntisRequests;

public class ClockSection extends Fragment implements OnClickListener {

	public ClockSection() {
		Bundle bundle = new Bundle();
		bundle.putString("title", MainActivity.getMainActivity().getString(R.string.clock_section_title));
		this.setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.clock_section, container, false);

		rootView.findViewById(R.id.button1).setOnClickListener(this);

		TextView dummyTextView = (TextView) rootView.findViewById(R.id.clock_test_label);
		dummyTextView.setText("Clock!");
		return rootView;
	}

	private class GetDataTask extends AsyncTask<String, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(String... params) {
			try {
				URL url = new URL("https://stpl.tgm.ac.at/WebUntis/jsonrpc.do?school=tgm");
				JSONObject input = new JSONObject();

				input.put("method", "getTimegridUnits");
				input.put("jsonrpc", "2.0");
				input.put("id", "2");

				String sessionID = WebUntisRequests.getSessionID(url, params[0], params[1]);
				JSONObject data = WebUntisRequests.getData(url, sessionID, input);
				return data;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@Override
	public void onClick(View v) {
		try {
			EditText user = (EditText) MainActivity.getMainActivity().findViewById(R.id.editText1);
			EditText pw = (EditText) MainActivity.getMainActivity().findViewById(R.id.editText2);
			TextView tv = (TextView) MainActivity.getMainActivity().findViewById(R.id.textView1);

			List<Unit> unitList = new ArrayList<Unit>();

			JSONObject data = new GetDataTask().execute(user.getText().toString(), pw.getText().toString()).get();
			JSONArray arr = data.getJSONArray("result");
			JSONObject currjson = arr.getJSONObject(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
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
