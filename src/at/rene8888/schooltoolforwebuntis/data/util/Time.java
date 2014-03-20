package at.rene8888.schooltoolforwebuntis.data.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Calendar;
import java.util.TimeZone;

public class Time implements Externalizable, Cloneable, Comparable<Time> {
	private int sec;

	public Time() {
		this(TimeZone.getDefault(), 0);
	}

	public Time(int offset) {
		this(TimeZone.getDefault(), offset);
	}

	public Time(TimeZone tz, int offset) {
		Calendar cal = Calendar.getInstance(tz);
		cal.add(Calendar.SECOND, offset);
		this.setHr(cal.get(Calendar.HOUR_OF_DAY));
		this.setMin(cal.get(Calendar.MINUTE));
		this.setSec(cal.get(Calendar.SECOND));
	}

	public Time(int hr, int min, int sec) {
		this.setHr(hr);
		this.setMin(min);
		this.setSec(sec);
	}

	public void set(int hr, int min, int sec) {
		this.setHr(hr);
		this.setMin(min);
		this.setSec(sec);
	}

	public void setHr(int i) {
		if (i > 0) {
			i = this.minimizeInt(i, 24);
			int sec = this.getSec();
			int min = this.getMin();
			int newsec = i * 60 * 60 + min * 60 + sec;
			this.sec = this.minimizeInt(newsec, 86400);
		}
	}

	public void setMin(int i) {
		if (i > 0) {
			int sec = this.getSec();
			int hr = this.getHr();
			int newsec = hr * 60 * 60 + i * 60 + sec;
			this.sec = this.minimizeInt(newsec, 86400);
		}
	}

	public void setSec(int i) {
		if (i > 0) {
			int min = this.getMin();
			int hr = this.getHr();
			int newsec = hr * 60 * 60 + min * 60 + i;
			this.sec = this.minimizeInt(newsec, 86400);
		}
	}

	public void addHr(int i) {
		if (i > 0) {
			this.addMin(i * 60);
		}
	}

	public void addMin(int i) {
		if (i > 0) {
			this.addSec(i * 60);
		}
	}

	public void addSec(int i) {
		if (i > 0) {
			this.sec = minimizeInt(this.getInSeconds() + i, 86400);
		}
	}

	public void subHr(int i) {
		if (i > 0) {
			this.subMin(i * 60);
		}
	}

	public void subMin(int i) {
		if (i > 0) {
			this.subSec(i * 60);
		}
	}

	public void subSec(int i) {
		if (i > 0) {
			int j = this.getInSeconds() - i;
			while (j < 0) {
				j += 86400;
			}
			this.sec = j;
		}
	}

	public int getHr() {
		return this.sec / 3600 % 60;
	}

	public int getMin() {
		return this.sec / 60 % 60;
	}

	public int getSec() {
		return this.sec % 60;
	}

	public int getInSeconds() {
		return this.sec;
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

	public void substract(Time t2) {
		this.subSec(t2.getInSeconds());
	}

	public void add(Time t2) {
		this.addSec(t2.getInSeconds());
	}

	public Object clone() {
		Time clone;
		try {
			clone = (Time) super.clone();
			clone.sec = this.sec;
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int minimizeInt(int input, int max) {
		return Math.abs(input) % max;
	}

	@Override
	public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
		this.sec = oi.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput oo) throws IOException {
		oo.writeInt(this.sec);
	}

	@Override
	public int compareTo(Time another) {
		if (this.equals(another)) {
			return 0;
		} else if (this.after(another)) {
			return 1;
		} else {
			return -1;
		}
	}

	public static Time createTime(String time) {
		if (time.length() == 3) {
			int hr = Integer.parseInt(time.substring(0, 1));
			int min = Integer.parseInt(time.substring(1));
			return new Time(hr, min, 0);
		} else if (time.length() == 4) {
			int hr = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(2));
			return new Time(hr, min, 0);
		} else {
			return null;
		}
	}

	public static String convertToYYYYMMDD(Calendar cal) {
		String out = "";

		String year = String.valueOf(cal.get(Calendar.YEAR));
		while (year.length() != 4) {
			year = "0" + year;
		}
		out += year;

		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		while (month.length() != 2) {
			month = "0" + month;
		}
		out += month;

		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		while (day.length() != 2) {
			day = "0" + day;
		}
		out += day;

		return out;
	}

}