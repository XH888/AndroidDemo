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
		String msg="网络状态：";
		int status=NetWorkStatus.getStatus(context);
		
		
		if(status!=NetWorkStatus.NET_UNCON){
			if(status == NetWorkStatus.NET_MOBILE ){
				Log.i("info", "网络连接：移动");
				msg+="移动";
			}else if(status == NetWorkStatus.NET_WIFI ){
				Log.i("info", "网络连接：Wifi");
				msg+="Wifi";
			}
		}else{
			Log.i("info", "网络断开");
			msg+="网络断开";
		}
		
		NotifyUtil.notify(context, 2, msg);
	}
}
