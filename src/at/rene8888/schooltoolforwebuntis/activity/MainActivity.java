package at.rene8888.schooltoolforwebuntis.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.PageChangeListener;
import at.rene8888.schooltoolforwebuntis.R;
import at.rene8888.schooltoolforwebuntis.SectionsPagerAdapter;
import at.rene8888.schooltoolforwebuntis.TabListener;
import at.rene8888.schooltoolforwebuntis.data.webuntis.Teacher;

public class MainActivity extends FragmentActivity {

	private SectionsPagerAdapter mSectionsPagerAdapter;

	private ViewPager mViewPager;

	private static MainActivity MAIN_ACTIVITY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MAIN_ACTIVITY = this;
		super.onCreate(savedInstanceState);

		new Teacher();

		ApplicationClass app = (ApplicationClass) this.getApplication();
		if (app.getUsername() == null || app.getUsername().equals("") || app.getPassword() == null || app.getPassword().equals("")) {
			goToWelcome();
		} else {
			setContentView(R.layout.main_activity);
			ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowTitleEnabled(false);
			this.mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mSectionsPagerAdapter);
			mViewPager.setOnPageChangeListener(new PageChangeListener(actionBar));
			for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
				actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(new TabListener(this.mViewPager)));
			}
		}
	}

	public void goToWelcome() {
		Intent i = new Intent(this, WelcomeActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static MainActivity getMainActivity() {
		return MAIN_ACTIVITY;
	}

}
