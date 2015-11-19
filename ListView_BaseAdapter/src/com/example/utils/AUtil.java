package com.example.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class AUtil<T> extends AsyncTask<String, Void, Object> {
	private static int TYPE_IMG=0;
	private static int TYPE_TXT=1;
	private int type;
	private String url;
	
	public CallBack<T> callback;
	public AUtil(int type,CallBack<T> callback){
		this.type=type;
		this.callback=callback;
	}
	@Override
	protected T doInBackground(String... params) {
		url=params[0];
		try{
			HttpClient client=new DefaultHttpClient();
			HttpResponse response=client.execute(new HttpGet(url));
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				byte[] bytes=EntityUtils.toByteArray(response.getEntity());
				if(type==TYPE_IMG){
					BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
				}else if(type==TYPE_TXT){
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
	}
	
	public interface CallBack<T>{
		public void response(String url,T result);
	}
}
