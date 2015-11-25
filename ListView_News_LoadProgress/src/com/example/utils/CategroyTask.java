package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.bean.FeedCategory;

import android.os.AsyncTask;

public class CategroyTask extends AsyncTask<String, Void, List<FeedCategory>> {
	private CallBack callback;
	public CategroyTask(CallBack callback){
		this.callback=callback;
	}
	@Override
	protected List<FeedCategory> doInBackground(String... params) {
		try{
			byte[] bytes=Request.get(params[0] );
			if(bytes!=null){
				String json=new String(bytes,"utf-8");
				JSONArray jsonArray=new JSONObject(json).getJSONObject("paramz").getJSONArray("columns");
				List<FeedCategory> list=new ArrayList<FeedCategory>();
				for(int i=0;i<jsonArray.length();i++){
					FeedCategory fc=new FeedCategory();
					fc.setId( jsonArray.getJSONObject(i).getInt("id") );
					fc.setName( jsonArray.getJSONObject(i).getString("name") );
					list.add(fc);
				}
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(List<FeedCategory> result) {
		if(result!=null){
			callback.response(result);
		}
	}
	
	
	public interface CallBack{
		 public void response(List<FeedCategory> list);
	}
}
