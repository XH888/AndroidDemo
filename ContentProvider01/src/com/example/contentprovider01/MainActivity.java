package com.example.contentprovider01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	Uri uri = Calls.CONTENT_URI;	//通话记录
	String[] culums = new String[] { 
			CallLog.Calls._ID, 
			CallLog.Calls.NUMBER,
			CallLog.Calls.DATE, 
			CallLog.Calls.TYPE };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void loadCall(View view) {
		ContentResolver resolver = getContentResolver();

		Cursor cursor = resolver.query(uri, culums, null, null, null);

		while (cursor.moveToNext()) {
			Log.i("info", "showMsg:-ID:" 
					+ cursor.getLong(0) + ",NUMBER："
					+ cursor.getString(1) + ",DATE:" 
					+ cursor.getLong(2)
					+ ",TYPE:" + cursor.getInt(3)
					+ "Date:"+System.currentTimeMillis());
		}
	}
}
