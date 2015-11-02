package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ExtraService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate Service");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy Service");
	}
}
