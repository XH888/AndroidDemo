package com.demo.activity05_task;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_c);
	}
	public void printTask(View v) {
		TaskUtil.printTask(getApplicationContext());
	}
	public void toD(View v){
		Intent intent=new Intent(getApplicationContext(),DActivity.class);
		startActivity(intent);
	}
}
