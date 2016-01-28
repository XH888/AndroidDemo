package com.example.receiver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkStatus {
	public static int NET_WIFI=ConnectivityManager.TYPE_WIFI;
	public static int NET_MOBILE=ConnectivityManager.TYPE_MOBILE;
	public static int NET_UNCON=-1;
	
	public static int getStatus(Context context){
		ConnectivityManager connectMgr=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
		
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		if(info!= null&& info.isAvailable()){
			int type=info.getType();
			if(type==NET_WIFI){
				return NET_WIFI;
			}else{
				return NET_MOBILE;
			}
		}
		return NET_UNCON;
	}
}
