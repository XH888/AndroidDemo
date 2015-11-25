package com.example.listview08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.AbsAdapter;
import com.example.bean.Person;

public class MainActivity extends Activity {
	private List<Person> datas;
	private ListView lv;
	private AbsAdapter<Person> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lvId);
		datas=new ArrayList<Person>();
		adapter=new AbsAdapter<Person>(getApplicationContext(),R.layout.item_person,datas) {
			@Override
			public void showData(ViewHolder<Person> viewHolder, Person data) {
				
				TextView nameView=(TextView) viewHolder.getViews(R.id.nameId);
				TextView ageView=(TextView) viewHolder.getViews(R.id.ageId);
				TextView sexView=(TextView) viewHolder.getViews(R.id.sexId);
				
				nameView.setText(data.getName());
				ageView.setText(String.valueOf(data.getAge()));
				sexView.setText(data.getSex());
				
			}
		};
		lv.setAdapter(adapter);
		
		loadData();
	}

	private void loadData() {
		for(int i=0;i<10;i++){
			Person p=new Person("zhangsan - "+i,Math.random()>0.5?"男":"女",(int)(Math.random()*10+20));
			datas.add(p);
		}
	}
}
