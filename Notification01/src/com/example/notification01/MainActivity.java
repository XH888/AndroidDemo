package com.example.notification01;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class MainActivity extends Activity {
	private NotificationManager notifyManager; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//由系统组件自动启动，直接通过getSystemService获取服务
		notifyManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void notify01(View view){
		NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext());
		builder.setSmallIcon(R.drawable.ic_launcher)	//设置小图标
				.setContentTitle("Notification 提示信息")
				.setContentText("通知的内容")
				.setTicker("标题栏滚动信息")
				.setAutoCancel(true)	//只有点击以后才消失（一直保存在通知栏：如腾许管家的状态下拉栏）
				.setOngoing(true)		//是否一直停留在状态栏	若是false:点击Clear则清除
				.setPriority(NotificationCompat.PRIORITY_HIGH)	//设置相关的优先级
				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)	//默认的提示信息
				.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 100, new Intent(Intent.ACTION_CALL,Uri.parse("tel:10086")),PendingIntent.FLAG_ONE_SHOT));
		notifyManager.notify(1, builder.build());		//第一个参数是本地程序中的notification的标识
	}
	
	public void notify02(View view){
		new Thread(){
			public void run() {
				NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext());
				builder.setSmallIcon(R.drawable.ic_launcher)	//设置小图标
						.setContentTitle("Notification 提示信息")
						.setContentText("正在下载...")
						.setProgress(100, 0, false)
						.setTicker("正在下载...")
						.setAutoCancel(false)
						//.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)	//默认的提示信息
						.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 100, new Intent(Intent.ACTION_CALL,Uri.parse("tel:10086")),PendingIntent.FLAG_ONE_SHOT));
				
				for(int i=0;i<=100;i+=5){
					builder.setProgress(100, i, false);	//设置成不确定性进度条
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					notifyManager.notify(2, builder.build());
				}
				builder.setTicker("下载完成").setContentText("下载完成");
				
				notifyManager.notify(2, builder.build());
			};
		}.start();
	}
	
	public void notify03(View view){
		NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext());
		builder.setSmallIcon(R.drawable.ic_launcher)	//设置小图标
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
				.setContentTitle("Notification 提示信息")
				.setContentText("加载大视图信息");
		
		NotificationCompat.InboxStyle style=new NotificationCompat.InboxStyle(builder);
		style.addLine("第一条信息..").addLine("第二条信息..").addLine("第三条信息..");
		style.setSummaryText("总数99+");
		
		builder.setStyle(style);
		
		notifyManager.notify(3, builder.build());
	}
	
	public void notify04(View view){
		NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext());
		builder.setSmallIcon(R.drawable.ic_launcher)	//设置小图标
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
			   	.setContentTitle("Notification 提示信息");
		
		NotificationCompat.BigPictureStyle style=new NotificationCompat.BigPictureStyle(builder);
		style.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.logo))
			.setSummaryText("加载大图片信息");
		
		builder.setStyle(style);
		
		notifyManager.notify(3, builder.build());
	}
}
