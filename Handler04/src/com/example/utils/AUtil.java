package com.example.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class AUtil {
	public static final int TYPE_TXT=1;
	public static final int TYPE_IMG=2;
	
	private Handler mHandler;
	
	//声明一个线程池
	public static ExecutorService executor=Executors.newFixedThreadPool(5);
	
	public AUtil(Handler mHandler){
		this.mHandler=mHandler;
	}
	
	public void getAsy(final int type,final String url){
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try{
					HttpClient client=new DefaultHttpClient();
					HttpResponse response= client.execute(new HttpGet(url));
					if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
						byte[] data=EntityUtils.toByteArray(response.getEntity());
						
						Message msg=Message.obtain();
						msg.what=type;
						
						if(type==TYPE_TXT){
							String txt=new String(data,"utf-8");	
							msg.obj=txt;		//将JSON流保存给obj
						}else{
							Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
							msg.obj=bitmap;		//将图片流保存给obj
							Bundle bundle=new Bundle();
							bundle.putString("url", url);	//并且将图片的URL地址绑定到图片，以后可以用findViewWithTag来取得图片
							msg.setData(bundle);
						}
						mHandler.sendMessage(msg);		//向主线程发送数据
					}
				}catch(Exception e ){
					e.printStackTrace();
				}
			}
		});
	}
}
