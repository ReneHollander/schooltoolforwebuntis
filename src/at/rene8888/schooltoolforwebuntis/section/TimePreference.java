package at.rene8888.schooltoolforwebuntis.section;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;
import at.rene8888.schooltoolforwebuntis.ApplicationClass;
import at.rene8888.schooltoolforwebuntis.data.Time;

public class TimePreference extends DialogPreference {
	private int lastHour = 0;
	private int lastMinute = 0;
	private TimePicker picker = null;

	public static int getHour(String time) {
		String[] pieces = time.split(":");

		return (Integer.parseInt(pieces[0]));
	}

	public static int getMinute(String time) {
		String[] pieces = time.split(":");

		return (Integer.parseInt(pieces[1]));
	}

	public TimePreference(Context ctxt, AttributeSet attrs) {
		super(ctxt, attrs);

		setPositiveButtonText("Set");
		setNegativeButtonText("Cancel");
	}

	@Override
	protected View onCreateDialogView() {
		picker = new TimePicker(getContext());

		return (picker);
	}

	@Override
	protected void onBindDialogView(View v) {
		super.onBindDialogView(v);

		picker.setCurrentHour(lastHour);
		picker.setCurrentMinute(lastMinute);
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);

		if (positiveResult) {

			lastHour = picker.getCurrentHour();
			lastMinute = picker.getCurrentMinute();

			String time = String.valueOf(lastHour) + ":" + String.valueOf(lastMinute);

			if (callChangeListener(time)) {
				persistString(time);
			}
			ApplicationClass app = ApplicationClass.getApplication();
			app.setDelay(0);
			Time t1 = new Time(lastHour, lastMinute, 0);
			Time now = new Time(ApplicationClass.getApplication().getDelay());
			if (t1.after(now)) {
				t1.substract(now);
				app.setDelay(t1.getInSeconds());
			} else {
				t1.substract(now);
				app.setDelay(t1.getInSeconds() * (-1));
			}
			app.saveChanges();
		}
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return (a.getString(index));
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		String time = null;

		if (restoreValue) {
			if (defaultValue == null) {
				time = getPersistedString("00:00");
			} else {
				time = getPersistedString(defaultValue.toString());
			}
		} else {
			time = defaultValue.toString();
		}

		lastHour = getHour(time);
		lastMinute = getMinute(time);
	}
}
