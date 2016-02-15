package com.example.service05_messenger_server;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class PrintService extends Service {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Log.i("info", "正在打印文本 " + msg.getData().getString("txt"));
				break;
			case 1:
				Log.i("info", "正在打印图片 " + msg.getData().getByteArray("bitmap").length);
			default:
				break;
			}
			
			Message replayMsg = Message.obtain();
			Bundle data = new Bundle();
			data.putString("msg", "我是Server返回的数据,打印成功；");
			replayMsg.setData(data);

			//取得客户端的发送信使 从服务端在发送给客户端；
			try {
				msg.replyTo.send(replayMsg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		};
	};

	private Messenger messenger = new Messenger(handler);

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return messenger.getBinder();
	}
}
