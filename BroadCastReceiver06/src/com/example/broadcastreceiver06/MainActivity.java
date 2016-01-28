package com.example.broadcastreceiver06;

import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static String ACTION_NAME="com.example.broadcastreceiver06.Action";
	private static String EXTRA_INFO="info";
	
	private TextView msgView;
	BroadcastReceiver myReciver;
	//support包中的本地广播管理；
	LocalBroadcastManager localBcastMgr;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgView=(TextView) findViewById(R.id.msgTv);
        
        //初始化本地广播管理器
        localBcastMgr=LocalBroadcastManager.getInstance(getApplicationContext());
        
        //注册广播
        myReciver = new MyBroadcastReceiver();
        localBcastMgr.registerReceiver(myReciver, new IntentFilter(ACTION_NAME));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		localBcastMgr.unregisterReceiver(myReciver);
	}
	
    public void send(View v){
    	Intent intent=new Intent(ACTION_NAME);
    	intent.putExtra(EXTRA_INFO, "LocalBroadcastManager：本地广播"+new Date());
    	
    	//发送广播
    	localBcastMgr.sendBroadcast(intent);
    }
    
    //创建广播接收器
    class MyBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			msgView.setText(intent.getStringExtra(EXTRA_INFO));
		}
    	
    }
}
