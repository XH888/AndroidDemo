package com.demo.activity05_task;

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
	
	public void printTask(View v) {
		TaskUtil.printTask(getApplicationContext());
	}
	public void toB(View v){
		
		Intent intent=new Intent(getApplicationContext(),BActivity.class);
	
		ComponentName cm=new ComponentName(getApplicationContext(),BActivity.class);
		//intent.setComponent(cm);
		intent.setClass(getApplicationContext(), BActivity.class);
		
		startActivity(intent);
	}
}
