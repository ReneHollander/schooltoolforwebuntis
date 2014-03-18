package at.rene8888.schooltoolforwebuntis.util;

import java.util.Calendar;

public class Util {

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

		String month = String.valueOf(cal.get(Calendar.MONTH));
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
