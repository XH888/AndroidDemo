package com.example.listview07;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adapter.PersonAdapter;
import com.example.bean.Person;

public class MainActivity extends Activity {
	private List<Person> datas;
	private ListView lv;
	private PersonAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datas=new ArrayList<Person>();
		lv=(ListView) findViewById(R.id.lvId);
		adapter=new PersonAdapter(getApplicationContext(), datas);
		lv.setAdapter(adapter);
		
		loadData();
	}

	private void loadData() {
		
		Person p1=new Person("张三","title","男",11,"1111");datas.add(p1);
		Person p2=new Person("张三","data","男",11,"1111");datas.add(p2);
		Person p3=new Person("张三","title","男",11,"1111");datas.add(p3);
		Person p4=new Person("张三","data","男",11,"1111");datas.add(p4);
		Person p5=new Person("张三","data","男",11,"1111");datas.add(p5);
		Person p6=new Person("张三","title","男",11,"1111");datas.add(p6);
		Person p7=new Person("张三","data","男",11,"1111");datas.add(p7);
		Person p8=new Person("张三","title","男",11,"1111");datas.add(p8);
		Person p9=new Person("张三","title","男",11,"1111");datas.add(p9);
		
		
		
	}
}
