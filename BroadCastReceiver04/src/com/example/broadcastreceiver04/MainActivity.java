package com.example.broadcastreceiver04;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView countId;
	MyBroadCastReceiver receiver;
	
	private static final String EXTRA_TEXT="text";
	//广播的频道
	private static final String ACTION_TIMER="com.example.broadreciver04.timer";
	//权限
	private static final String ACTION_PERMISSION="com.example.broadreciver04.timer.PERMISSION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countId=(TextView) findViewById(R.id.countId);
        receiver=new MyBroadCastReceiver();
        registerReceiver(receiver, new IntentFilter(ACTION_TIMER));
    }
    
    @Override
    protected void onDestroy() {
    	unregisterReceiver(receiver);
    	super.onDestroy();
    }
    
    public void sendBroadCast(View v){
    	new Thread(){
			@Override
			public void run() {
				int count=0;
				//开启一个线程，发送定时广播；
				while(count<=50){
					Intent intent=new Intent(ACTION_TIMER);
					intent.putExtra(EXTRA_TEXT, count++);
					
					//设置广播发送的所在包名称
					//intent.setPackage(getPackageName());	
		
					sendBroadcast(intent,ACTION_PERMISSION);
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
    }
    
    public class MyBroadCastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			int count = intent.getIntExtra(EXTRA_TEXT, 0);
			countId.setText(String.valueOf(count));
		}
    }
}
