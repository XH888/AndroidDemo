package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class ImageTask extends AsyncTask<String, Void, Bitmap> {
	private CallBack callback;
	private String url;
	public ImageTask(CallBack callback){
		this.callback=callback;
	}
	@Override
	protected Bitmap doInBackground(String... params) {
		try{
			url=params[0];
			byte[] bytes=Request.get(url);
			if(bytes!=null){
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(result!=null){
			callback.response(url,result);
		}
	}
	public interface CallBack{
		 public void response(String url,Bitmap bitmap);
	}
}
