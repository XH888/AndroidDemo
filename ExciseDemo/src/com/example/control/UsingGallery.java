package com.example.control;


import com.example.excisedemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Gallery;

public class UsingGallery extends Activity {
	private Gallery callery;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_use_gallery);
		callery=(Gallery) findViewById(R.id.gallery);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
		adapter.add("item1");
		adapter.add("item2");
		adapter.add("item3");
		callery.setAdapter(adapter);
	}
}
