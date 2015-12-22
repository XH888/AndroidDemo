package com.example.contentprovider;

import com.example.util.DBHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

//数据库操作工具类；
public class UserContentProvider extends ContentProvider {
	//声明contentprovider的唯一标识：AUTHORITY（必须小写）
	public static final String AUTHORITY="com.example.contentprovider02";
	
	//标识数据库表
	public static final int CODE_USER=0;
	public static final int CODE_ORDER=1;
	
	//uri资源匹配器
	private static UriMatcher uriMatch;
	static{
		uriMatch=new UriMatcher(UriMatcher.NO_MATCH);
		//content://com.example.contentprovider02/users
		uriMatch.addURI(AUTHORITY, "users", CODE_USER);
		uriMatch.addURI(AUTHORITY, "orders", CODE_ORDER);
	}
	
	private DBHelper dbhelper;
	@Override
	public int delete(Uri uri,String selection,String[] whereArgs) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean onCreate() {
		dbhelper=new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder) {
		//获取数据库连接
		SQLiteDatabase sdb=dbhelper.getReadableDatabase();
		Cursor cursor=null;
		
		switch (uriMatch.match(uri)) {
		case CODE_USER:
			cursor=sdb.query("t_user", projection, selection, selectionArgs, null,null,sortOrder);
			break;

		default:
			break;
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO 自动生成的方法存根
		return 0;
	}
}
