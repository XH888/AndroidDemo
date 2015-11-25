package com.example.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

import android.os.AsyncTask;

public class JsonArrayTask<T> extends AsyncTask<String, Void, List<T>> {
	private Class<T> cls;
	private CallBack<T> callback;
	
	public JsonArrayTask(Class<T> cls, CallBack<T> callback) {
		this.cls = cls;
		this.callback = callback;
	}

	@Override
	protected List<T> doInBackground(String... params) {
		try {
			byte[] bytes=Request.get(params[0]);
			if(bytes!=null){
				//解析json
				String json=callback.parsePart(new String(bytes,"utf-8"));
				return JSON.parseArray(json, cls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(List<T> result) {
		if(result!=null)
			callback.response(result);
	}
	
	public interface CallBack<T>{
		public String parsePart(String json);	
		public void response(List<T> result);
	}
}
