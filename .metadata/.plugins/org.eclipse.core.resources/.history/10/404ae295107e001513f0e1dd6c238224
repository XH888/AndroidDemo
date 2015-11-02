package com.example.excisedemo;

import com.example.control.UsingGallery;
import com.example.control.UsingImageSwicher;
import com.example.control.UsingNotification;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private ArrayAdapter<ListCellData> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter=new ArrayAdapter<ListCellData>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		adapter.add(new ListCellData("Notification",this,new Intent(this,UsingNotification.class)));
		adapter.add(new ListCellData("ImageSwiter",this,new Intent(this,UsingImageSwicher.class)));
		adapter.add(new ListCellData("Gallery",this,new Intent(this,UsingGallery.class)));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListCellData data=adapter.getItem(position);
		data.startActivity();
		super.onListItemClick(l, v, position, id);
	}
}
