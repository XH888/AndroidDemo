package com.example.spinner_simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity {
	private Spinner sp;
	private List<Map<String,Object>> datas;
	private Adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp=(Spinner) findViewById(R.id.spid);
		//载入数据
		loadData();
		adapter= new SimpleAdapter(getApplicationContext(),datas,R.layout.item_person, 
				new String[]{"name","age","sex"},		//对应的传入的对象数组
				new int[]{R.id.nameId,R.id.ageId,R.id.sexId});	//传给对象View的对应ID
		
		sp.setAdapter((SpinnerAdapter) adapter);		//设置spinner的列表对象
	}
	
	
	private void loadData() {
		datas =new ArrayList<Map<String,Object>>();
		for(int i=0;i<4;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", "张三");
			map.put("age", i);
			map.put("sex", i%2==0?"男":"女");
			datas.add(map);
		}
	}
}
