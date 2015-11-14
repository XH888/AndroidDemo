package com.demo.activtiy02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class B_Activty extends Activity {
	private TextView firstView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		
		firstView=(TextView) findViewById(R.id.firstView);
		
		Intent intent=getIntent();
		String msg=intent.getStringExtra("msg");
		String name=intent.getStringExtra("mame");
		
		Bundle bundle=intent.getExtras();
		String sex=bundle.getString("sex");
		int age=bundle.getInt("age");
		
		firstView.setText(msg+"\n"+name+"\n"+sex+"\n"+age);
	}
}
