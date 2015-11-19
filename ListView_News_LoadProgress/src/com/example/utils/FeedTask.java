package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.bean.Feed;
import android.os.AsyncTask;
import android.util.Log;

public class FeedTask extends AsyncTask<String, Void, List<Feed>> {
	private CallBack callback;
	public FeedTask(CallBack callback){
		this.callback=callback;
	}
	@Override
	protected List<Feed> doInBackground(String... params) {
		try{
			byte[] bytes=Request.get(params[0]);
			if(bytes!=null){
				String json=new String(bytes,"utf-8");
				JSONArray jsonArray=new JSONObject(json).getJSONObject("paramz").getJSONArray("feeds");
				List<Feed> list=new ArrayList<Feed>();
				for(int i=0;i<jsonArray.length();i++){
					Feed f=new Feed();
					f.setId( jsonArray.getJSONObject(i).getInt("id") );
					f.setOid( jsonArray.getJSONObject(i).getInt("oid") );
					f.setSubject( jsonArray.getJSONObject(i).getJSONObject("data").getString("subject") );
					f.setSummary( jsonArray.getJSONObject(i).getJSONObject("data").getString("summary") );
					f.setCover( jsonArray.getJSONObject(i).getJSONObject("data").getString("cover") );
					
					list.add(f);
				}
				return list;
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(List<Feed> result) {
		if(result!=null){
			callback.response(result);
		}
	}
	
	public interface CallBack{
		public void response(List<Feed> list);
	}
}
