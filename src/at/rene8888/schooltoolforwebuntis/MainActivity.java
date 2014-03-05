package at.rene8888.schooltoolforwebuntis;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	private SectionsPagerAdapter mSectionsPagerAdapter;

	private ViewPager mViewPager;

	private static Context CONTEXT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CONTEXT = this;

		this.setContentView(R.layout.activity_main);

		this.mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());

		this.mViewPager = (ViewPager) findViewById(R.id.pager);
		this.mViewPager.setAdapter(this.mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static Context getContext() {
		return CONTEXT;
	}

}
