package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.adapter.CbkAdapter;
import com.example.bean.CbkData;
import com.example.util.AUtils;
import com.example.util.AUtils.CallBack;

import android.app.ListFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class CbkFragment extends ListFragment implements CallBack{

	private String url=null;

	private CbkAdapter adapter;
	private List<CbkData> datas;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		url=getArguments().getString("url");
		datas=new ArrayList<CbkData>();
		adapter=new CbkAdapter(getActivity(), datas);
		
		loadData();
	}
	
	private void loadData() {
		AUtils.getAsy(AUtils.TYPE_TXT, url, this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(adapter);
	}
	
	@Override
	public void respText(String txt) {
		try {
			JSONArray array=new JSONObject(txt).getJSONArray("all_list");
			
			List<CbkData> listdata=JSON.parseArray(array.toString(), CbkData.class);
			
			datas.clear();
			datas.addAll(listdata);
			
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isCancelled(String url) {
		return false;
	}

	

	@Override
	public void respImg(String url, Bitmap bitmap) {
		
	}
}
