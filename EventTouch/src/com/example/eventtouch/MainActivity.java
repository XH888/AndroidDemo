package com.example.eventtouch;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",TAG + " - dispatchTouchEvent - " + EventUtils.getName(  event.getAction()) );
		return super.dispatchTouchEvent(event);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info",TAG + " - onTouchEvent - " + EventUtils.getName(  event.getAction()) );
		return super.onTouchEvent(event);
	}
}
