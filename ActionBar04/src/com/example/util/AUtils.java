package com.example.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

public class AUtils {
	public final static int TYPE_TXT = 1;
	public final static int TYPE_IMG = 2;
	private static Handler mHandler=new Handler();
	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	public interface CallBack {
		public boolean isCancelled(String url);

		public void respText(String txt);

		public void respImg(String url, Bitmap bitmap);
	}

	public abstract static class AbsCallBack implements CallBack{

		@Override
		public boolean isCancelled(String url) {
			return false;
		}

		@Override
		public void respText(String txt) {
			
		}

		@Override
		public void respImg(String url, Bitmap bitmap) {
			
		}
	}
	
	public static void getAsy(final int type,final String url,final CallBack callback) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(new HttpGet(url));
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						final byte[] b = EntityUtils.toByteArray(response.getEntity());
					
					//HttpURLConnection读取JSON有乱码问题
					/*HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
					if (con.getResponseCode() == 200) {
						InputStream is = new BufferedInputStream(con.getInputStream());
						byte[] bytes = new byte[is.available()];
						int len = -1;
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						while ((len = is.read()) != -1) {
							baos.write(bytes, 0, len);
							if (callback.isCancelled(url)) {
								Log.i("info", " -- 取消下载  -- ");
								return;
							}
						}
						final byte[] b = baos.toByteArray();
						*/
						
						if (type == TYPE_IMG) {
							ImgUtil.saveImage(url, b); // 保存到扩展存储；
						}
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								try {
									if (type == TYPE_TXT) {
										String s=new String(b, "utf-8");
										callback.respText(s);
									} else if (type == TYPE_IMG) {
										callback.respImg(url, BitmapFactory.decodeByteArray(b,0,b.length));
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
