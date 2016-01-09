package com.example.handler02;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_V1 extends Activity {
	private Button startBtn,stopBtn;
	private TextView timeView;
	
	boolean isStart=true;	//是否停止循环的标识
	
	private Handler hander=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			timeView.setText(String.valueOf(msg.what));
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timeView=(TextView) findViewById(R.id.timeView);
		startBtn=(Button) findViewById(R.id.startBtn);
		stopBtn=(Button) findViewById(R.id.stopBtn);
		stopBtn.setEnabled(false);
		startBtn.setEnabled(true);
	}
	
	public void startTime(View view){
		stopBtn.setEnabled(true);
		startBtn.setEnabled(false);
		
		new Thread(){
			public void run() {
				isStart=true;
				int count=0;
				while(isStart){
					//发送一个空消息给主线程
					hander.sendEmptyMessage(count++);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	//停止计数，但是子线程没有停止
	public void stopTime(View view){
		stopBtn.setEnabled(false);
		startBtn.setEnabled(true);
		isStart=false;	
	}
}
