package com.example.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.widget.Toast;


public class Request {
	//从一个URL地址取得对象流；
	public static byte[] get(String url) throws Exception{
		HttpClient client =new DefaultHttpClient();
		HttpResponse response=client.execute(new HttpGet(url));
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			return EntityUtils.toByteArray(response.getEntity());
		}
		return null;
	}
}
