package com.example.autocompletetextview01;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
	private AutoCompleteTextView actView;
	private List<String> datas;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actView=(AutoCompleteTextView) findViewById(R.id.actViewId);
		
		datas=new ArrayList<String>();
		
		for(int i=0;i<50;i++){
			char[] c=new char[10];
			for(int j=0;j<c.length;j++){
				c[j]=(char) ('a'+(Math.random()*25));
			}
			datas.add(new String(c));
		}
		
		adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.item_view,datas);
		
		actView.setAdapter(adapter);
		
		//点击item发生的事件
		actView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				CharSequence txt=((TextView)v).getText();
				setTitle(txt);
				Toast.makeText(getApplicationContext(),"text->"+txt,Toast.LENGTH_LONG).show();
			}
		});
		
		//监听文本框改变事件
		actView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO 自动生成的方法存根
				Log.i("info", "-- onTextChanged --" + s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO 自动生成的方法存根
				Log.i("info", "-- beforeTextChanged --" + s.toString());
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO 自动生成的方法存根
				Log.i("info", "-- afterTextChanged --" + s.toString());
			}
		});
	}
}
