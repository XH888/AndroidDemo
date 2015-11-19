package com.example.asynctask01;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.DownloadManager;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView=(ImageView) findViewById(R.id.imgId);
	}

	/*
	 * AsyncTask<Params, Progress, Result>
	 * params:子线程中执行方法的参数类型；
	 * progress:子线程中执行任务的进度类型；
	 * result:子线程执行任务的结果类型；
	 */
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
			if(result!=null){
				imageView.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
			}
		}
	}
	
	public void onShow(View v){
		new MyTask().execute("http://newtab.firefoxchina.cn/img/worldindex/logo.png");
	}

}
