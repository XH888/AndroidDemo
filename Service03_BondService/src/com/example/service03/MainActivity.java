package com.example.service03;


import com.example.service03.TimerService.TimerBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	//用来监控Service状态的回调方法、连接Service状态；
	private ServiceConnection sconnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO 系统崩溃时候断开服务连接
			Log.i("info", "-- onServiceDisconnected --");
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO 绑定组件连接成功
			Log.i("info", "-- onServiceConnected --");
			tBinder =(TimerBinder) service;
			tBinder.startTimer("时间到了。", 5*1000);
		}
	};
	private Intent timerIntent;
	private TimerBinder tBinder;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        timerIntent=new Intent(getApplicationContext(),TimerService.class);
        
    }

    public void bindService(View v){
    	//绑定服务
    	bindService(timerIntent, sconnection, BIND_AUTO_CREATE);
    }

    public void unBindService(View v){
    	if(tBinder!=null){
    		tBinder.stopTimer();
    		tBinder=null;
    		unbindService(sconnection);
    	}
    }
    
    public void startService(View v){
    	startService(timerIntent);
    }
    
    public void stopService(View v){
    	stopService(timerIntent);
    }
}
