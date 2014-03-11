package at.rene8888.schooltoolforwebuntis.test;

import at.rene8888.schooltoolforwebuntis.data.webuntis.Rooms;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Room;

public class Test {

	public Test() {

		for (Room r : new Rooms().getAllRoomsAsArrayList()) {
			System.out.println(r);
		}
	}

}
