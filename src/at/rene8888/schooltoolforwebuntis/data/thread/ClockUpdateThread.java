package at.rene8888.schooltoolforwebuntis.data.thread;

import at.rene8888.schooltoolforwebuntis.data.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.util.Time;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid.TimeGrid;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timegrid.Unit;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.TimeTableEntry;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.schoolclass.SchoolClassTimeTable;
import at.rene8888.schooltoolforwebuntis.data.webuntis.timetable.schoolclass.SchoolClassTimeTableUnit;
import at.rene8888.schooltoolforwebuntis.gui.section.ClockSection;

public class ClockUpdateThread extends Thread {

	private boolean running;

	private SchoolClassTimeTable sctt;
	private ClockSection clockSection;

	public ClockUpdateThread(SchoolClassTimeTable sctt, ClockSection clockSection) {
		this.running = true;
		this.sctt = sctt;
		this.sctt.getUnits();
		this.clockSection = clockSection;
	}

	public void run() {

		boolean firstCycle = true;

		TimeGrid tg = Data.getData().getTimeGrids().getTimeGridByCalendar(this.sctt.getCalendar());
		
		while (running) {

			Unit currentUnit = tg.getUnitByTime(new Time(ApplicationClass.getApplication().getDelay()));
			
			if (currentUnit.before(sctt.getFirstLesson().getUnit())) {
				
			} else if (currentUnit.after(sctt.getLastLesson().getUnit())) {
				
			}
			
			nextUnit: for (int i = 0; i < this.sctt.getUnits().size(); i++) {
				TimeTableEntry<SchoolClassTimeTableUnit> entry = this.sctt.getUnits().get(i);
				if (firstCycle) {
					if (entry.getUnit().getStart().before(new Time(ApplicationClass.getApplication().getDelay())) && entry.getUnit().getEnd().before(new Time(ApplicationClass.getApplication().getDelay()))) {
						continue nextUnit;
					}
				}

				while (running) {
					
					if (i == 0) {
						
					}
					
					if (entry.getUnit().getEnd().equals(new Time(ApplicationClass.getApplication().getDelay()))) {
						break;
					} else {
						try {

							this.clockSection.update(this.sctt, i, entry);
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
