package com.example.bordercastdemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	private Button btnBroadCast,btnStartBCR,btnStopBCR;
	private BroadCastDemo mybc=new BroadCastDemo();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnBroadCast=(Button) findViewById(R.id.btnBroadCast);
		btnStartBCR=(Button) findViewById(R.id.btnStartBCR);
		btnStopBCR=(Button) findViewById(R.id.btnStopBCR);
		btnBroadCast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent i=new Intent(MainActivity.this, BroadCastDemo.class);
				Intent i=new Intent(BroadCastDemo.ACTION);
				i.putExtra("txt", "send Msg");
				sendBroadcast(i);
			}
		});
		//停止广播和发送广播;
		btnStartBCR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				registerReceiver(mybc, new IntentFilter(BroadCastDemo.ACTION));
			}
		});
		
		btnStopBCR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				unregisterReceiver(mybc);
			}
		});
		
		
		
	}


}
