package at.rene8888.schooltoolforwebuntis.thread;

import java.util.List;

import android.widget.TextView;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.activity.MainActivity;
import at.rene8888.schooltoolforwebuntis.data.Time;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Unit;

public class ClockUpdateThread extends Thread {

	private List<Unit> unitList;

	private boolean running;

	private TextView tv;

	public ClockUpdateThread(List<Unit> unitList, TextView tv) {
		this.unitList = unitList;
		this.running = true;
		this.tv = tv;
	}

	public void run() {

		boolean firstCycle = true;

		while (running) {

			nextUnit: for (int i = 0; i < unitList.size(); i++) {
				Unit u = unitList.get(i);
				if (firstCycle) {
					if (u.getStart().before(new Time(ApplicationClass.getApplication().getDelay())) && u.getEnd().before(new Time(ApplicationClass.getApplication().getDelay()))) {
						continue nextUnit;
					}
				}

				while (running) {
					if (u.getEnd().equals(new Time(ApplicationClass.getApplication().getDelay()))) {
						break;
					} else {
						try {
							final Time t2 = (Time) u.getEnd().clone();
							t2.substract(new Time(ApplicationClass.getApplication().getDelay()));
							MainActivity.getMainActivity().runOnUiThread(new Runnable() {
								public void run() {
									tv.setText(t2.toString());
								}
							});
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							break;
						}
					}
				}
			}
			firstCycle = false;
		}

	}
}
