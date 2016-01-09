package com.example.handler01;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imgView;
	private String uri="http://newtab.firefoxchina.cn/img/sitenav/logo.png";
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			//主线程接收处理消息
			Bitmap bitmap=(Bitmap) msg.obj;
			imgView.setImageBitmap(bitmap);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgView=(ImageView) findViewById(R.id.imgId);
	}
	
	//子线程向主线程发送消息
	public void download(View view){
		new Thread(){	//创建子线程下载文件
			public void run() {
				try {
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(new HttpGet(uri));
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						byte[] bytes=EntityUtils.toByteArray(response.getEntity());
						Bitmap bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
						//imgView.setImageBitmap(bitmap);
						
						//通过Handler发送线程给主线程
						Message message=Message.obtain();		//从连接池创建Message对象
						message.obj=bitmap;
						//发送消息
						handler.sendMessage(message);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
