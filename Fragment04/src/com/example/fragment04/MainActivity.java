package com.example.fragment04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.example.fragment.ContentFragment;

public class MainActivity extends Activity {
	private static final String TAG="MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("info",TAG + " -- > onCreate");
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		Log.i("info",TAG + " -- > onStart");  
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		Log.i("info",TAG + " -- > onResume");  
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		Log.i("info",TAG + " -- > onPause");  
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		Log.i("info",TAG + " -- > onStop");  
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.i("info",TAG + " -- > onDestroy");  
		super.onDestroy();
	}
}
