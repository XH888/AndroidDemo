package com.example.broadcastreceiver01;

import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	private TextView msgTv;
	private BroadcastReceiver myBroadCastReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		msgTv=(TextView) findViewById(R.id.msgId);
		//实例化广播
		myBroadCastReceiver = new MyBroadCastReceiver();
		
		//实例化广播过滤器
		IntentFilter filter = new IntentFilter();
		
		//添加系统的广播  电量改变  充电状态
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);	
		filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
		
		//注册广播
		registerReceiver(myBroadCastReceiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(myBroadCastReceiver);
	}
	
	class MyBroadCastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			//接收到广播的处理方法，可以接收多个广播
			if(intent.getAction()==Intent.ACTION_BATTERY_CHANGED){
				int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
				msgTv.setText("电量状态改变: "+level+"%");
			}else if(intent.getAction()==Intent.ACTION_POWER_DISCONNECTED){
				msgTv.setText("电源已经断开（是否是充电状态）");
			}
		}
	}
}
