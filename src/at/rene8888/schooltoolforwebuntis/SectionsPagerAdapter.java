package at.rene8888.schooltoolforwebuntis;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import at.rene8888.schooltoolforwebuntis.section.ClockSection;
import at.rene8888.schooltoolforwebuntis.section.SettingsSection;
import at.rene8888.schooltoolforwebuntis.section.TimeTableSection;
import at.rene8888.schooltoolforwebuntis.section.WelcomeSection;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private SparseArray<Fragment> items;

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		this.items = new SparseArray<Fragment>();
		SharedPreferences prefs = MainActivity.getMainActivity().getSharedPreferences("at.rene8888.schooltoolforwebuntis", Context.MODE_PRIVATE);
		if (prefs.getBoolean("firstStart", true)) {
			this.items.put(this.items.size(), new WelcomeSection());
			Editor editor = prefs.edit();
			editor.putBoolean("firstStart", false);
			editor.commit();
		}
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
