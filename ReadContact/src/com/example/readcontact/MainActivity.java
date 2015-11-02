package com.example.readcontact;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;

public class MainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//System.out.println(getContentResolver().getType(ContactsContract.Contacts.CONTENT_URI));
		Cursor c= getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (c.moveToNext()) {
			System.out.println( c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME)) );
		}
	}
}