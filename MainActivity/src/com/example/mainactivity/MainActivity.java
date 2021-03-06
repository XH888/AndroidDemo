package com.example.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity {
	private Button butMain,butNotification;
	private TextView txtReturnMsg;
	private NotificationManager nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butMain= (Button) findViewById(R.id.butActivity1);
        butNotification= (Button) findViewById(R.id.butNotification);
        txtReturnMsg= (TextView) findViewById(R.id.txtReturnMsg);
        
        nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(R.layout.activity_main);
        butNotification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Notification n=new Notification(R.drawable.ic_launcher,"ticker Text",System.currentTimeMillis());	//ticker Text 是显示时候滚动的文字
				n.setLatestEventInfo(MainActivity.this, "contentTitle", "content",PendingIntent.getActivity(MainActivity.this, 1, getIntent(),0 ));
				nm.notify(R.layout.activity_main, n);
			}
		});
        
        butMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Activity 之间传递值,获取值
				Intent i=new Intent(MainActivity.this,Aty1.class);
				//i.putExtra("txt", "这是传过来的值");
				Bundle bundle=new Bundle();
				bundle.putString("txt", "这是bundle传过来的值");
				i.putExtras(bundle);
				//startActivity(i);
				startActivityForResult(i, 0);
			}
		});
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	String returnMsg =data.getStringExtra("txtReturn");
    	txtReturnMsg.setText(returnMsg);
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
}
