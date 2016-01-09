package com.example.handler04;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.adapter.FeedAdapter;
import com.example.bean.Feed;
import com.example.utils.AUtil;
import com.example.utils.URLs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {

	private ListView lv;
	private List<Feed> datas;
	private FeedAdapter adapter;
	
	private Handler mHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			//取得子线程Handler处理后的数据
			if(msg.what==AUtil.TYPE_TXT){
				parseJson(String.valueOf(msg.obj));
			}else{
				String url=msg.getData().getString("url");
				ImageView imageView=(ImageView) lv.findViewWithTag(url);
				if(imageView!=null){
					imageView.setImageBitmap((Bitmap) msg.obj);
				}
			}
		}
	};
	
	AUtil autils;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lvId);
		autils=new AUtil(mHandler);
		datas=new ArrayList<Feed>();
		adapter=new FeedAdapter(getApplicationContext(), datas,autils);
		
		lv.setAdapter(adapter);
		
		autils.getAsy(AUtil.TYPE_TXT, URLs.BASE_URI+URLs.URI);	//初始化下载Json数据
	}
	
	//解析json数据源
	private void parseJson(String json) {
		try {
			JSONArray array= new JSONArray(json);
			
			Gson gson=new Gson();
			TypeToken<List<Feed>> typeToken=new TypeToken<List<Feed>>(){};
			
			List<Feed> list=gson.fromJson(array.toString(), typeToken.getType());
			
			datas.clear();
			datas.addAll(list);
			adapter.notifyDataSetChanged();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
