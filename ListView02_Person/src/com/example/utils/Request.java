package com.example.utils;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class Request {
	public static byte[] get(String url) throws Exception{
		HttpClient client=new DefaultHttpClient();
		HttpResponse response=client.execute(new HttpGet(url));
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			return EntityUtils.toByteArray(response.getEntity());
		}
		return null;
	}
	
	public static byte[] get(String url,ImageTask.CallBack callback) throws Exception{
		HttpClient client=new DefaultHttpClient();
		HttpResponse response=client.execute(new HttpGet(url));
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			//return EntityUtils.toByteArray(response.getEntity());
			HttpEntity entity=response.getEntity();
			InputStream stream=entity.getContent();
			byte[] buffer=new byte[1024];
			int len=-1;
			ByteArrayBuffer buf=new ByteArrayBuffer(0);		
			while((len=stream.read(buffer))!=-1){		//将数据读到stream中，并且不是读取到最后的时候；
				buf.append(buffer,0,len);	//将数据放buffer到缓存中
				if(callback.isCancel(url)){
					Log.i("info", url+"--已经取消下载");
					return  null;
				}
			}
			return buf.toByteArray();
		}
		return null;
	}
}
