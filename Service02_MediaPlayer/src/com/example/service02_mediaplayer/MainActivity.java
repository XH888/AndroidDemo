package com.example.service02_mediaplayer;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor>{
	Intent playerIntent;
	SeekBar seekBar;
	ProgressReceiver progressReceiver;
	TextView timerId;
	
	//文件列表
	ListView lv;
	Cursor cursor;
	SimpleCursorAdapter adapter;
	
	//音乐文件Provider接口字段；
	String colums[] = {MediaColumns._ID,MediaColumns.DISPLAY_NAME,MediaColumns.DATA};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        timerId = (TextView) findViewById(R.id.timerId);
        lv = (ListView) findViewById(R.id.listViewId);
        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.item_layout, cursor, 
        		new String[]{colums[1],colums[2]}, 
        		new int[]{R.id.fileName,R.id.fileUrl},
        		SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        
        lv.setAdapter(adapter);
        getLoaderManager().initLoader(1, null, this);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				playerIntent.putExtra(Config.EXTRA_CHANGED, true);
		
				cursor.moveToPosition(position);
		
				String path = cursor.getString(2);
				playerIntent.putExtra(Config.EXTRA_PATH, path);
				
				startService(playerIntent);
			}
        	
		});
        
        progressReceiver=new ProgressReceiver();
        registerReceiver(progressReceiver, new IntentFilter(Config.ACTION_PROG_BROADCAST));
        
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO 拖动结束的事件
				int progress = seekBar.getProgress();
				Intent intent = new Intent(Config.ACTION_SEEK_BROADCAST);
				intent.putExtra(Config.ACTION_SEEK_BROADCAST, progress);
				
				sendBroadcast(intent);
				//拖动结束回复注册
				registerReceiver(progressReceiver, new IntentFilter(Config.ACTION_PROG_BROADCAST));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 开始拖动
				//开始拖动取消注册，防止卡顿
				unregisterReceiver(progressReceiver);
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				// TODO 正在拖动之中
			}
		});
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(progressReceiver);
    	
    	NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    
    	RemoteViews rView =new  RemoteViews(getPackageName(),R.layout.notification_player);
    	
    	//设置标题内容
    	rView.setTextViewText(R.id.titleId, cursor.getString(1));
    	
    	playerIntent.putExtra(Config.EXTRA_CHANGED, false);
    	
    	// 延迟意图 处理播放/暂停 按钮的事件；
    	rView.setOnClickPendingIntent(R.id.playId, PendingIntent.getService(getApplicationContext(), 10, playerIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    	
    	Notification.Builder builder = new Notification.Builder(getApplicationContext());
    	
    	//通过remoteViews 设置通知
    	builder.setSmallIcon(R.drawable.ic_launcher)
    			.setContent(rView);
    	
    	notifyManager.notify(10, builder.build());
    }
    
    public void start(View v){
    	playerIntent=new Intent(getApplicationContext(),MyMediaPlayer.class);
    	startService(playerIntent);
    }
    
    public void stop(View v){
    	stopService(playerIntent);
    }
    
    
    //用来接受service中广播发送的当前播放进度数据
	class ProgressReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			int currentPosition = intent.getIntExtra(Config.CURRENT_POSITION,0);
			int maxLen = intent.getIntExtra(Config.MAX_LEN,0);
			
			seekBar.setMax(maxLen);
			seekBar.setProgress(currentPosition);
			
			timerId.setText(currentPosition/1000+"--"+maxLen/1000);
		}
    }


	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		// TODO Auto-generated method stub
		return new CursorLoader(getApplicationContext(), 
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,	//从扩展卡下读取所有的音频文件；
				colums, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// TODO Auto-generated method stub
		adapter.swapCursor(cursor);
		this.cursor= cursor;
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		adapter.swapCursor(null);
	}
}
