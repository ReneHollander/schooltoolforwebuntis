package at.rene8888.schooltoolforwebuntis.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import at.rene8888.schooltoolforwebuntis.ApplicationClass;

public class Time implements Serializable, Cloneable {

	private static final long serialVersionUID = 1396468218851613851L;

	private static final TimeZone TIMEZONE = TimeZone.getDefault();

	private int hr;
	private int min;
	private int sec;
	private int delay;

	public Time() {
		Calendar cal = Calendar.getInstance(TIMEZONE);
		this.setDelay(ApplicationClass.getApplication().getDelay());
		cal.add(Calendar.SECOND, delay);
		this.setHr(cal.get(Calendar.HOUR_OF_DAY));
		this.setMin(cal.get(Calendar.MINUTE));
		this.setSec(cal.get(Calendar.SECOND));
	}

	public Time(int hr, int min, int sec) {
		this.setHr(hr);
		this.setMin(min);
		this.setSec(sec);
	}

	public void setHr(int i) {
		this.hr = 0;
		this.addHr(i);
	}

	public void setMin(int i) {
		this.min = 0;
		this.addMin(i);
	}

	public void setSec(int i) {
		this.sec = 0;
		this.addSec(i);
	}

	public void addHr(int i) {
		this.hr = minimizeInt(this.hr + i, 24);
	}

	public void addMin(int i) {
		int j = this.min + i;
		this.min = minimizeInt(j, 60);
		this.addHr(j / 60);
	}

	public void addSec(int i) {
		int j = this.sec + i;
		this.sec = minimizeInt(j, 60);
		this.addMin(j / 60);
	}

	public int getHr() {
		return this.hr;
	}

	public int getMin() {
		return this.min;
	}

	public int getSec() {
		return this.sec;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getInSeconds() {
		return this.hr * 3600 + this.min * 60 + this.sec;
	}

	public String toString() {
		String out = "";
		if (this.getHr() <= 9) {
			out += "0" + this.getHr();
		} else {
			out += this.getHr();
		}
		out += ":";
		if (this.getMin() <= 9) {
			out += "0" + this.getMin();
		} else {
			out += this.getMin();
		}
		out += ":";
		if (this.getSec() <= 9) {
			out += "0" + this.getSec();
		} else {
			out += this.getSec();
		}
		return out;
	}

	public boolean before(Time t) {
		if (this.getInSeconds() < t.getInSeconds()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean after(Time t) {
		if (this.getInSeconds() > t.getInSeconds()) {
			return true;
		} else {
			return false;
		}
	}

	public void substract(Time t2) {
		int newsec = this.getInSeconds() - t2.getInSeconds();
		this.setSec(newsec % 60);
		this.setMin(newsec / 60 % 60);
		this.setHr(newsec / 60 / 60 % 60);
	}

	public void add(Time t2) {
		int newsec = this.getInSeconds() + t2.getInSeconds();
		this.setSec(newsec % 60);
		this.setMin(newsec / 60 % 60);
		this.setHr(newsec / 60 / 60 % 60);
	}

	public boolean equals(Object t) {
		if (t instanceof Time) {
			if (this.getInSeconds() == ((Time) t).getInSeconds()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	protected Object clone() {
		Time clone;
		try {
			clone = (Time) super.clone();
			clone.sec = this.sec;
			clone.min = this.min;
			clone.hr = this.hr;
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int minimizeInt(int input, int max) {
		return Math.abs(input) % max;
	}

}