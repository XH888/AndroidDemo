package com.example.listview_viewholder;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adapter.PersonAdapter;
import com.example.bean.Person;

public class MainActivity extends Activity {
	private List<Person> datas;
	private PersonAdapter adapter;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		datas=new ArrayList<Person>();
		lv=(ListView) findViewById(R.id.lvid);
		adapter=new PersonAdapter(datas, getApplicationContext());
		lv.setAdapter(adapter);
		loadData();
	}

	private void loadData() {
		Person p=null;
		for (int i = 0; i < 100; i++) {
			p=new Person("person - "+i, (int)(Math.random()*11+20));
			datas.add(p);
		}
		//adapter.notifyDataSetChanged();
	}
}
