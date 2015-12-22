package com.example.storage04;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.utils.DBHelp;

public class MainActivity extends Activity {
	private ListView lv;
	private Cursor cursor;
	private DBHelp dbHelper;
	private SimpleCursorAdapter adapter;
	
	private String[] columns=new String[]{"_id","name","age","tel"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lvId);
		dbHelper=new DBHelp(getApplicationContext());
		adapter=new SimpleCursorAdapter(getApplicationContext(), R.layout.item_user, cursor,
				new String[]{"name","age","tel"},
				new int[]{R.id.nameId,R.id.ageId,R.id.telId},
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		loadData();
		lv.setAdapter(adapter);
	}
	
	private void loadData() {
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		
		//查询表
		cursor=sdb.query("t_person", columns, null, null, null, null, null);
		
		//切换数据源；
		adapter.swapCursor(cursor);
	}

	//向数据库添加数据
	public void addPerson(View view){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		ContentValues values=new ContentValues();
		
		values.put("name", "张三");
		values.put("age", (int)(Math.random()*10+20));
		values.put("tel",10086);
		
		long id=sdb.insert("t_person", null, values);
		if(id!=-1){
			Toast.makeText(getApplicationContext(), " --> 数据插入成功 ", 1).show();
		}
		sdb.close();
		
		loadData();
	}
}
