package com.example.service02_mediaplayer;


import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyMediaPlayer extends Service {
	MediaPlayer myPlayer;
	SeekReceiver seekReceiver;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		//创建MediaPlayer，其中已经实现prepare()方法；在销毁必须release()释放方法；
		myPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a1);
		
		seekReceiver = new SeekReceiver();
		registerReceiver(seekReceiver, new IntentFilter(Config.ACTION_SEEK_BROADCAST));
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent.getBooleanExtra(Config.EXTRA_CHANGED,false)){
			//切换歌曲标识
			myPlayer.stop();
			myPlayer.reset();//重置player
			try {
				//设置元数据
				myPlayer.setDataSource(intent.getStringExtra(Config.EXTRA_PATH));
				myPlayer.prepare();	//设置完成，准备播放
				myPlayer.start();
				
				new ProgressThread().start();	//启动进度条广播
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		
			if(myPlayer.isPlaying()){
				myPlayer.pause();
			}else{
				myPlayer.start();
				new ProgressThread().start();
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		myPlayer.stop();
		//myPlayer.release();	//回收资源
		unregisterReceiver(seekReceiver);
	}
	
	//子线程向主线程发送的进度广播
	class ProgressThread extends Thread{
		@Override
		public void run() {
			while(myPlayer!=null && myPlayer.isPlaying()){
				Intent intent = new Intent(Config.ACTION_PROG_BROADCAST);

				//取得当前播放位置
				intent.putExtra(Config.CURRENT_POSITION, myPlayer.getCurrentPosition());

				//取得播放音乐的持续时间
				intent.putExtra(Config.MAX_LEN, myPlayer.getDuration());

				sendBroadcast(intent);
			}
		}
	}
	
	class SeekReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			int currentPosition = intent.getIntExtra(Config.ACTION_SEEK_BROADCAST,0);
			if(myPlayer!=null)
				myPlayer.seekTo(currentPosition);
		}
	}
}
