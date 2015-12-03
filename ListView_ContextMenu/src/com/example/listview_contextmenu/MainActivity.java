package com.example.listview_contextmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lv;
	private List<String> datas;
	private ArrayAdapter<String> adapter;
	private int currentPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datas = new ArrayList<String>();
		lv = (ListView) findViewById(R.id.lvId);
		adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item, datas);
		lv.setAdapter(adapter);
		loadData();
		registerForContextMenu(lv); //向组件注册菜单上下文；
	}

	private void loadData() {
		for (int i = 0; i < 40; i++) {
			datas.add("person - " + i);
		}
	}

	// 点击菜单时候发生的事件
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_add:
				datas.add(currentPosition, "新的Person - "+System.currentTimeMillis());
				break;
			case R.id.action_update:
				datas.set(currentPosition, "修改Person - "+datas.get(currentPosition)+System.currentTimeMillis());
				break;
			case R.id.action_del:
				Toast.makeText(getApplicationContext(), "DEL : "+datas.get(currentPosition), Toast.LENGTH_SHORT).show();
				datas.remove(currentPosition);
				break;
			default:
				break;
		}
		adapter.notifyDataSetChanged();
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.item_edit, menu);	//注册item layout
		AdapterContextMenuInfo amenuInfo=(AdapterContextMenuInfo) menuInfo;	//取得item layout的组件对象
		currentPosition = amenuInfo.position;
		super.onCreateContextMenu(menu, v, menuInfo);
	}
}
