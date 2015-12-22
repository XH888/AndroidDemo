package com.example.storage03;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadData();
	}

	private void loadData() {
		String dbPath=Environment.getExternalStorageDirectory()+"";
		SQLiteDatabase db=SQLiteDatabase.openDatabase(dbPath, null,SQLiteDatabase.OPEN_READWRITE);
		if(db!=null){
			Cursor cursor=db.rawQuery("select * from user",null);
		}
	}
}
