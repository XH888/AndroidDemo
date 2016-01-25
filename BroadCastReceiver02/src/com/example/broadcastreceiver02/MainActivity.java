package com.example.broadcastreceiver02;

import com.example.receiver.MyReceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	BroadcastReceiver br;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		br=new MyReceiver();
		Intent intent = new Intent("com.example.receiver.MY_RECEIVER");  
	    intent.putExtra("msg", "hello receiver.");  
		sendBroadcast(intent);
	}
	
	@Override
	protected void onDestroy() {  
	    super.onDestroy();  
	    unregisterReceiver(br);  
	}
}
