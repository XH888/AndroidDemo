package com.example.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelp extends SQLiteOpenHelper {
	public DBHelp(Context context) {
		/**
		 * 第二参数：数据库名称，默认的位置在安装包的database目录下面
		 * 第三参数：CursorFactory 游标工厂。
		 * 第四参数：数据库版本号
		 */
		super(context, "gp06.db", null, 1);	
	}

	/**
	 * 初始化数据库；对于多条数据的增删改查需要执行db.beginTransaction()事物开启；
	 * db.setTransactionSuccessful()：成功提交事物
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table t_fav(_id integer primary key,id,title,info,web_content,fav_data,model_type)");
		db.execSQL("create table t_person(_id integer primary key,name,age,tel)");
	}

	//更新或者升级数据库；
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion>oldVersion){
			db.execSQL("drop table if exists t_fav");
			db.execSQL("drop table if exists t_person");
		}
		onCreate(db);
	}
}
