package com.example.bordercastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadCastDemo extends BroadcastReceiver {
	public static final String ACTION="com.example.bordercastdemo.intent.action.BroadCastDemo";
	@Override
	public void onReceive(Context arg0, Intent intent) {
		System.out.println("onReceive,date="+intent.getStringExtra("txt"));
	}
}
