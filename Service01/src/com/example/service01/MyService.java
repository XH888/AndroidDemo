package com.example.service01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		//绑定生命周期的方法
		return null;
	}
	
	@Override
	public void onCreate() {
		//初始化
		super.onCreate();
		Log.i("info", "-- onCreate --");
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//启动Service
		Log.i("info", "-- onStartCommand --"+ intent.getExtras().getString("msg"));
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		//销毁
		super.onDestroy();
		Log.i("info", "-- onDestroy --");
	}
}
