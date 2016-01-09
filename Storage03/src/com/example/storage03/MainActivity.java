package com.example.storage03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.util.FileUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageView img;
	private byte[] bytes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img=(ImageView) findViewById(R.id.img);
	}
	
	
	public void readImg(View view){
		
	}
	
	public void writeImg(View view){
		String url="http://newtab.firefoxchina.cn/img/sitenav/logo.png";
		try {
			new MyTask().execute(url);
			FileUtils.saveImage(url, bytes);
			Toast.makeText(getApplicationContext(), " write img success ", 1).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	class MyTask extends AsyncTask<String, Void, byte[]>{
		@Override
		protected byte[] doInBackground(String... params) {
			// TODO	子线程执行任务的方法
			try{
				String uri=params[0];
				HttpClient client=new DefaultHttpClient();
				HttpGet get=new HttpGet(uri);
				HttpResponse response=client.execute(get);
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					return EntityUtils.toByteArray(response.getEntity());
				}
			}catch(Exception e ){
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(byte[] result) {
			// TODO 子线程返回主线程的方法
			bytes=result;
		}
	}
}
