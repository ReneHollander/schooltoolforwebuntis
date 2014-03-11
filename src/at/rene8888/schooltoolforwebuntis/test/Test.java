package at.rene8888.schooltoolforwebuntis.test;

import android.util.Log;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Departments;
import at.rene8888.schooltoolforwebuntis.data.webuntis.objects.Department;

public class Test {

	public Test() {

		System.out.println("pumawo");
		
		SparseArray<Department> departments = new Departments().getAllDepartments();
		
		for (int i = 0; i < departments.size(); i++) {
			Department curr = departments.get(departments.keyAt(i));
			Log.d("dep", curr.toString());
		}
	}

}
