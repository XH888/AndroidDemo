package com.example.service06_intentservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Intent intent;
	
	ImageView img1View,img2View;
	ViewGroup parent;
	String urls[] = {"http://v4.vcimg.com/global/images/logo.png","http://bbs.a9vg.com/static/image/common/logo.png"};

	MyBroadcast mybroadCast;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1View = (ImageView) findViewById(R.id.img1View);
        img1View.setTag(urls[0]);
        
        img2View = (ImageView) findViewById(R.id.img2View);
        img2View.setTag(urls[1]);
        
        parent = (ViewGroup) img2View.getParent();
        
        intent = new Intent(getApplicationContext(),ImageDownloadService.class);
        
        mybroadCast = new MyBroadcast();
        
        registerReceiver(mybroadCast, new IntentFilter(Config.ACTION_IMG));
    }

    
    public void downloadPic(View v){
    	img1View.setImageResource(R.drawable.ic_launcher);
    	img2View.setImageResource(R.drawable.ic_launcher);
    	
    	//启动服务
    	intent.putExtra(Config.EXT_PATH, urls[0]);
    	startService(intent);
    	//启动服务
    	intent.putExtra(Config.EXT_PATH, urls[1]);
    	startService(intent);
    }
    
    class MyBroadcast extends BroadcastReceiver{
    	
		@Override
		public void onReceive(Context context, Intent intent) {
			byte[] imgbyte = intent.getByteArrayExtra(Config.EXT_IMG);
			
			ImageView img = (ImageView) parent.findViewWithTag(intent.getStringExtra(Config.EXT_PATH));
			
			if(img!=null)
				img.setImageBitmap(BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length));
		}
    }
}
