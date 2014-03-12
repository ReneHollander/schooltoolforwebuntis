package at.rene8888.schooltoolforwebuntis.data.webuntis.objects.type;

public enum LessonType {

	LESSON("ls", "lesson"), OFFICEHOUR("oh", "office hour"), STANDBY("sb", "standby"), BREAKSUPERVISION("bs", "break supervision");

	private static LessonType[] lessonTypes;

	private String shortName;
	private String longName;

	private LessonType(String shortName, String longName) {
		this.shortName = shortName;
		this.longName = longName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public String getLongName() {
		return this.longName;
	}

	public String toString() {
		return this.longName;
	}

	public static LessonType getLessonTypeByShortName(String shortName) {
		if (lessonTypes == null) {
			lessonTypes = LessonType.values();
		}
		for (LessonType lt : lessonTypes) {
			if (lt.getShortName().equals(shortName)) {
				return lt;
			}
		}
		return null;
	}

}
