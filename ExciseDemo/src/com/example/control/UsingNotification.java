package com.example.control;

import com.example.excisedemo.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class UsingNotification extends Activity {
	private NotificationManager nm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_use_notification);
		nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(R.layout.aty_use_notification);
		findViewById(R.id.notificationBtn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Notification n=new Notification(R.drawable.ic_launcher,"��������ʾͼ��",System.currentTimeMillis());
				n.setLatestEventInfo(UsingNotification.this, "title", "text",PendingIntent.getActivity(UsingNotification.this, 1, getIntent(), 0));
				nm.notify(R.layout.aty_use_notification, n);
			}
		});
	}
}
