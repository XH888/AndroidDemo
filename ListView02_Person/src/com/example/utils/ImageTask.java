package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class ImageTask extends AsyncTask<String, Void, Bitmap> {
	private CallBack callback;
	private String url;
	
	public ImageTask(CallBack callback) {
		this.callback=callback;
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		url=params[0];
		try {
			byte[] bytes=Request.get(url,callback);
			if(bytes!=null){
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if(result!=null){
			callback.response(url, result);
		}
	}
	
	public interface CallBack{
		//取消下载URL的任务
		public boolean isCancel(String url);
		
		//将数据返回给调用者
		public void response(String url,Bitmap bitmap);
	}
}
