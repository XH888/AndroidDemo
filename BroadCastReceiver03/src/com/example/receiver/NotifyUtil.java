package com.example.receiver;

import com.example.broadcastreceiver03.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class NotifyUtil {
	public static void notify(Context context,int id,String msg){
		//取得系统通知Notify管理对象
		NotificationManager notifyMgr=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		
		//实例化通知构造
		builder.setContentTitle("网络连接状态")
				.setContentText(msg)
				.setTicker(msg)
				.setSmallIcon(R.drawable.ic_launcher)
				.setDefaults(Notification.DEFAULT_SOUND);
		
		//发送通知
		notifyMgr.notify(id,builder.build());
	}
}
