package at.rene8888.schooltoolforwebuntis;

import android.app.ActionBar;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class PageChangeListener implements OnPageChangeListener{
	private ActionBar actionBar;
	
	public PageChangeListener(ActionBar actionBar) {
		this.actionBar = actionBar;
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	@Override
	public void onPageSelected(int position) {
		actionBar.setSelectedNavigationItem(position);
	}

}
