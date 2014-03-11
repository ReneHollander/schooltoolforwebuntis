package at.rene8888.schooltoolforwebuntis.test;

import at.rene8888.schooltoolforwebuntis.data.webuntis.Data;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Subject;

public class Test {

	public Test() {
		Data data = new Data();
		for (Subject s : data.getSubjects().getAllSubjectsAsArrayList()) {
			System.out.println(s);
		}
	}
}
