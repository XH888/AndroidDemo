package com.example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
		super(context, "users.db", null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table t_user(_id integer primary key,uname,upass,money)");
		db.execSQL("create table t_order(_id integer primary key,product_name,price,user_id)");
		
		db.execSQL("insert into t_user(uname,upass,money) values('zhangsan','123','1234') ");
		db.execSQL("insert into t_user(uname,upass,money) values('zhangsan2','1232','12342') ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion > oldVersion){
			db.execSQL("drop table t_user if exists");
			db.execSQL("drop table t_order if exists");
			onCreate(db);
		}
	}
}
