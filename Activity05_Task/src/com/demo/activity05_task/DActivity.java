package com.demo.activity05_task;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d);
	}
	
	public void printTask(View v) {
		TaskUtil.printTask(getApplicationContext());
	}
	public void toB(View v){
		Intent intent=new Intent(getApplicationContext(),BActivity.class);
		startActivity(intent);
	}
}
