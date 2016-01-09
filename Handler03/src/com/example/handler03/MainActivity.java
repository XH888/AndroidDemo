package com.example.handler03;

import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Connection().start();
		
	}
	public void Threadbtn(View view){
		if(mHandler!=null){
			Message msg=Message.obtain();
			msg.obj="我是主线程；";
			mHandler.sendMessage(msg);	//向子线程发送数据 实际上是发送到MessageQueue
		}
	}
	class Connection extends Thread{
		@Override
		public void run() {
			Looper.prepare();	//此时会创建MessageQueue
			mHandler=new Handler(){		//将Handler 中mQueue会指向  创建的MessageQueue列中
				@Override
				public void handleMessage(Message msg) {
					Log.i("info", new Date()+"-->"+msg.obj);
				}
			};
			Looper.loop();	//循环读取MessageQueue 中的Message
		}
	}
}
