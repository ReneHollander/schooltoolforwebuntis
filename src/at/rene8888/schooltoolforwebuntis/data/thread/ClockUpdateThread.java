package at.rene8888.schooltoolforwebuntis.data.thread;

import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.gui.activity.MainActivity;
import at.rene8888.schooltoolforwebuntis.gui.section.ClockSection;

public class ClockUpdateThread extends Thread {

	private boolean running;

	private ClockSection clockSection;

	private Thread thread;

	public ClockUpdateThread(ClockSection clockSection) {
		this.clockSection = clockSection;
		this.running = true;
	}

	public void run() {
		this.thread = Thread.currentThread();
		while (this.running) {
			try {
				long processingStart = System.currentTimeMillis();

				MainActivity.getMainActivity().runOnUiThread(new Runnable() {
					public void run() {
						clockSection.update();
					}
				});

				long processingFinish = System.currentTimeMillis();
				long processTime = ApplicationClass.getApplication().getClockUpdateTime() - (processingFinish - processingStart);
				if (processTime > 0) {
					Thread.sleep(processTime);
				}
			} catch (InterruptedException ex) {
				return;
			}
		}
	}

	public void shutdown() {
		this.running = false;
		this.thread.interrupt();
	}

	/*
	 * 
	 * 
	 * boolean firstCycle = true;
	 * 
	 * TimeGrid tg =
	 * Data.getData().getTimeGrids().getTimeGridByCalendar(this.sctt
	 * .getCalendar());
	 * 
	 * while (running) {
	 * 
	 * Unit currentUnit = tg.getUnitByTime(new
	 * Time(ApplicationClass.getApplication().getDelay()));
	 * 
	 * if (currentUnit.before(sctt.getFirstLesson().getUnit())) {
	 * 
	 * } else if (currentUnit.after(sctt.getLastLesson().getUnit())) {
	 * 
	 * }
	 * 
	 * nextUnit: for (int i = 0; i < this.sctt.getUnits().size(); i++) {
	 * TimeTableEntry<SchoolClassTimeTableUnit> entry =
	 * this.sctt.getUnits().get(i); if (firstCycle) { if
	 * (entry.getUnit().getStart().before(new
	 * Time(ApplicationClass.getApplication().getDelay())) &&
	 * entry.getUnit().getEnd().before(new
	 * Time(ApplicationClass.getApplication().getDelay()))) { continue nextUnit;
	 * } }
	 * 
	 * while (running) {
	 * 
	 * if (i == 0) {
	 * 
	 * }
	 * 
	 * if (entry.getUnit().getEnd().equals(new
	 * Time(ApplicationClass.getApplication().getDelay()))) { break; } else {
	 * try {
	 * 
	 * this.clockSection.update(this.sctt, i, entry); Thread.sleep(1000); }
	 * catch (InterruptedException e) { break; } } } } firstCycle = false; }
	 */
}
