package com.example.service05_messenger_client;

import com.example.service05_messenger_client.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Messenger messager;
	
	//handle接受信使
	private Handler mHandle = new Handler(){
		public void handleMessage(Message msg) {
			Log.i("info", "客户端接收的数据：" + msg.getData().getString("msg"));
		};
	};
	
	//回传的信使
	private Messenger replayMessenger = new Messenger(mHandle);
	
	private ServiceConnection sConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Toast.makeText(getApplicationContext(), "连接打印机成功", 0).show();
			messager = new Messenger(service);
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void connectPrint(View v){
    	//绑定服务;
    	bindService(new Intent("com.example.service05_messenger.PrintService"), sConnection, BIND_AUTO_CREATE);
    }
    
    public void printImg(View v){
    	if(!checkIsConnect()){
    		return ;
    	}
    	Message msg = Message.obtain();
    	msg.what = 0;
    	Bundle data = new Bundle();
    	data.putString("txt", "这个是文本对象");
    	msg.setData(data);
    	
    	//将信使设置到消息参数中
    	msg.replyTo = replayMessenger;
    	//msg.obj = "我是文本对象";
    	try {
			messager.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public void printText(View v){
    	if(!checkIsConnect()){
    		return ;
    	}
    	Message msg = Message.obtain();
    	msg.what = 1;
    	Bundle data = new Bundle() ;
    	data.putByteArray("bitmap",new byte[(int) (Math.random()*1024)]);
    	msg.setData(data);
    	
    	//使用信使Messager 就不能使用obj传递Object，因为其没有实现Pracelable接口,Bundle则有实现
    	//msg.obj = new byte[(int)Math.random()*1024];
    	
    	//将信使设置到消息参数中
    	msg.replyTo = replayMessenger;
    	try {
			messager.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public boolean checkIsConnect(){
    	if(messager==null){
    		Toast.makeText(getApplicationContext(), "当前没有连接打印机", 0).show();
    		return false;
    	}
    	return true;
    }
    
    @Override
    protected void onDestroy() {
    	unbindService(sConnection);
    	super.onDestroy();
    }
}