package com.demo.activity04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BActivity extends Activity {
	private final String TAG=".BActivity";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		Log.i("info", TAG+" --onCreate-- ");
	}

	@Override
	protected void onStart() {
		// TODO 自动生成的方法存根
		super.onStart();
		Log.i("info", TAG+" --onStart-- ");
	}
	
	
	@Override
	protected void onResume() {
		// TODO 自动生成的方法存根
		super.onResume();
		Log.i("info", TAG+" --onResume-- ");
	}
	
	@Override
	protected void onPause() {
		// TODO 自动生成的方法存根
		super.onPause();
		Log.i("info", TAG+" --onPause-- ");
	}

	@Override
	protected void onStop() {
		// TODO 自动生成的方法存根
		super.onStop();
		Log.i("info", TAG+" --onStop-- ");
	}
	
	@Override
	protected void onRestart() {
		// TODO 自动生成的方法存根
		super.onRestart();
		Log.i("info", TAG+" --onRestart-- ");
	}
	
	@Override
	protected void onDestroy() {
		// TODO 自动生成的方法存根
		super.onDestroy();
		Log.i("info", TAG+" --onDestroy-- ");
	}
}
