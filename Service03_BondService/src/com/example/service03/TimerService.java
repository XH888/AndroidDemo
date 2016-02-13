package com.example.service03;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class TimerService extends Service {

	private Timer timer;	//定时工具
	
	private NotificationManager notifiyManager;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", " -- onCreate -- ");
		timer = new Timer();
		notifiyManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO 绑定方法；
		Log.i("info", " -- onBind -- ");
		return new TimerBinder();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO 解除绑定
		Log.i("info", " -- onUnbind -- ");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onRebind(Intent intent) {
		// TODO 重新绑定
		super.onRebind(intent);
		Log.i("info", " -- onRebind -- ");
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", " -- onDestroy -- ");
		timer.cancel();timer=null;
	}
	
	//IBinder的子类，向Activity提供功能性方法；
	class TimerBinder extends Binder{
		//启动定时任务
		void startTimer(final String info , long ms){
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					//显示通知
					NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
					
					builder.setSmallIcon(R.drawable.ic_launcher)
							.setContentTitle("定时提醒")
							.setContentText(info)
							.setTicker(info)
							.setDefaults(Notification.DEFAULT_SOUND);
					notifiyManager.notify(0, builder.build());
				}
			}, ms ,10*1000);
		}
		
		void stopTimer(){
			timer.cancel();
		}
	}
}
