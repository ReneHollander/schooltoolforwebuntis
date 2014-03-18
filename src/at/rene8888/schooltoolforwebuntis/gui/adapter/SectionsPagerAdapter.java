package at.rene8888.schooltoolforwebuntis.gui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.gui.section.ClockSection;
import at.rene8888.schooltoolforwebuntis.gui.section.SettingsSection;
import at.rene8888.schooltoolforwebuntis.gui.section.TimeTableSection;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private SparseArray<Fragment> items;

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		this.items = new SparseArray<Fragment>();
		this.items.put(this.items.size(), new ClockSection());
		this.items.put(this.items.size(), new TimeTableSection());
		this.items.put(this.items.size(), new SettingsSection());
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = this.items.get(position);
		return fragment;
	}

	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Fragment fragment = this.items.get(position);
		return fragment.getArguments().getString("title");
	}
}
