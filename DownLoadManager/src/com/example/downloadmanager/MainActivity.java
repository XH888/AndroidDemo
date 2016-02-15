package com.example.downloadmanager;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DownloadManager;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private String downloadUrl = "https://www.baidu.com/img/270_7573fb368053e6805e63b56352ce7287.gif";
	
	DownloadManager dlManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
    }

    public void downLoad(View v){ 
    	DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
    	
    	//设置下载文件路径
    	request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,"1.gif");
    
    	
    	//设置通知是否可见
    	request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
    
    	request.setTitle("正在下载文件 1.gif");
    	
    	//压入队列 开始下载文件；
    	long id = dlManager.enqueue(request);	
    	
    	//通过query查询下载队列是否成功；
    	Cursor cursor = dlManager.query(new DownloadManager.Query().setFilterById(id).setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL));
    	
    	//通过cursor查询出变量值
    	if( cursor.moveToNext() ){

    		//获取本地文件位置；
    		String path = cursor.getString(cursor.getColumnIndex( DownloadManager.COLUMN_LOCAL_FILENAME ) );
    		
    		//获取文件名称；
    		String title = cursor.getString(cursor.getColumnIndex( DownloadManager.COLUMN_LOCAL_FILENAME ) );
    	}
    	
    }
}
