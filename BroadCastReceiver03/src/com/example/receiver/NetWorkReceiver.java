package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

//接受网络的方法类
public class NetWorkReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("info", intent.getAction());

		//取得系统服务连接管理
		ConnectivityManager conManger=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//网络信息管理
		NetworkInfo info = conManger.getActiveNetworkInfo();
		if(info!=null){
			if(info.getType() == ConnectivityManager.TYPE_MOBILE ){
				Log.i("info", "网络连接：移动");
			}else if(info.getType() == ConnectivityManager.TYPE_WIFI ){
				Log.i("info", "网络连接：Wifi");
			}
		}else{
			Log.i("info", "网络断开");
		}
	}
}
