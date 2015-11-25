package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListView extends Activity {
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_activity);
		lv=(ListView) findViewById(R.id.lv);
		ListAdapter adapter=new BaseAdapter() {
			//private String[] data=new String[]{"11","22","33","444","55","66","77","88","99","00","12","636"};
			private CustomData[] data=new CustomData[]{
					new CustomData(R.drawable.pic_1,"zhangsan1","zhangsan desc1"),
					new CustomData(R.drawable.pic_2,"zhangsan2","zhangsan desc2"),
					new CustomData(R.drawable.pic_3,"zhangsan3","zhangsan desc3"),
			};
			
			
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				System.out.println("--------------------- -------------");
				TextView tv=null;
				if(convertView==null){
					tv=new TextView(CustomListView.this);
				}else{
					tv=(TextView) convertView;
				}
				tv.setText(position+">>"+data[position]);
				tv.setTextSize(50);
				
				return tv;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return data[position];
			}
			
			@Override
			public int getCount() {
				return data.length;
			}
		};
		lv.setAdapter(adapter);
	}
}
