package com.example.intent01;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void toB(View v) {
		Intent intent=new Intent();
		intent.setComponent(new ComponentName(getApplicationContext(), BActivity.class));
		startActivity(intent);
	}
}
