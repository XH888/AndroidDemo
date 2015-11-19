package com.example.listview01;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.app.Activity;

public class MainActivity extends Activity {
	private AutoCompleteTextView actview;
	private ListView listView;	//下拉框显示的list列表
	private List<String> datas;	//存放原始列表数据
	private List<String> words;	//存放查找数据
	private ArrayAdapter<String> adapter;	//adapter适配器
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//先实例化各个初始值；
		actview=(AutoCompleteTextView) findViewById(R.id.actvId);
		listView= (ListView) findViewById(R.id.lvId);
		
		datas=new ArrayList<String>();
		
		adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.item_view,datas);
		
		listView.setAdapter(adapter);
		
		loadWords();	//启动时候，载入初始数据
		actview.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Log.i("sys", "afterTextChanged"+s);
				datas.clear();
				if(s.length()>0){
					for(String word:words){
						if(word.indexOf(s.toString())!=-1){
							datas.add(word);
						}
					}
				}else{
					datas.addAll(words);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

	private void loadWords() {
		words=new ArrayList<String>();
		for(int i=0;i<50;i++){
			char[] c=new char[10];
			for(int j=0;j<c.length;j++){
				c[j]=(char) ('a'+(Math.random()*25));
			}
			words.add(new String(c));
		}
		datas.addAll(words);
		//adapter.notifyDataSetChanged();
	}
}
