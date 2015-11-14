package com.demo.activity04;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private final String TAG=".MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("info", TAG+" --onCreate-- ");
	}
	
	public void forwardBActivity(View v){
		Intent i=new Intent(getApplicationContext(),BActivity.class);
		startActivity(i);
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
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		
		super.onRestoreInstanceState(savedInstanceState);
	}
	
}
