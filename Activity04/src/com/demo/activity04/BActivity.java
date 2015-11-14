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
		// TODO �Զ����ɵķ������
		super.onStart();
		Log.i("info", TAG+" --onStart-- ");
	}
	
	
	@Override
	protected void onResume() {
		// TODO �Զ����ɵķ������
		super.onResume();
		Log.i("info", TAG+" --onResume-- ");
	}
	
	@Override
	protected void onPause() {
		// TODO �Զ����ɵķ������
		super.onPause();
		Log.i("info", TAG+" --onPause-- ");
	}

	@Override
	protected void onStop() {
		// TODO �Զ����ɵķ������
		super.onStop();
		Log.i("info", TAG+" --onStop-- ");
	}
	
	@Override
	protected void onRestart() {
		// TODO �Զ����ɵķ������
		super.onRestart();
		Log.i("info", TAG+" --onRestart-- ");
	}
	
	@Override
	protected void onDestroy() {
		// TODO �Զ����ɵķ������
		super.onDestroy();
		Log.i("info", TAG+" --onDestroy-- ");
	}
}
