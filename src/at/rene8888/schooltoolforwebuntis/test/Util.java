package at.rene8888.schooltoolforwebuntis.test;

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

}
