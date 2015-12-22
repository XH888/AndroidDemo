package com.example.contentresolver01;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lvId;
	private List<String> data;
	private ArrayAdapter<String> adapter;
	
	private Uri uri=Uri.parse("content://com.example.contentprovider02/users");
	private String[] colums={"_id","uname","upass","money"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvId=(ListView) findViewById(R.id.lvId);
		data=new ArrayList<String>();
		adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.item_user,data);
		lvId.setAdapter(adapter);
		loadDate();
	}

	private void loadDate() {
		Cursor cursor=getContentResolver().query(uri, colums, null, null, null);
		while(cursor.moveToNext()){
			long id=cursor.getLong(0);
			String uname=cursor.getString(1);
			String upass=cursor.getString(2);
			int money=cursor.getInt(3);
			data.add("id:"+id+",uname:"+uname +",upass:"+ upass +",money:"+ money);
		}
		cursor.close();
		adapter.notifyDataSetChanged();
	}
}