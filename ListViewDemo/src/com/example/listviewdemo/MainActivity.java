package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lv;
	private ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lv);
		List<DataItem> list=new ArrayList<DataItem>();
		list.add(new DataItem("zhangsan1","ÄÐ",16));
		list.add(new DataItem("zhangsan2","Å®",14));
		list.add(new DataItem("zhangsan3","ÄÐ",14));
		list.add(new DataItem("zhangsan4","Å®",11));
		list.add(new DataItem("zhangsan5","ÄÐ",15));
		adapter=new ArrayAdapter<DataItem>(this, android.R.layout.simple_list_item_1,list);
		//ListAdapter adapter=new ArrayAdapter<String>(this,R.layout.listview_item,list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				DataItem data= (DataItem) adapter.getItem(position);
				Toast.makeText(MainActivity.this,String.format("name:%s,sex:%s,age:%d", data.getName(),data.getSex(),data.getAge()),Toast.LENGTH_SHORT).show();
			}
		});
	}

}
