package com.example.loader01;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

public class ContactLoader extends AsyncTaskLoader<Cursor> {
	private Bundle args;
	
	public ContactLoader(Context context,Bundle args) {
		super(context);
		this.args=args;
	}
	
	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		onForceLoad();	//在开始时候前置执行载入Loader
	}
	
	@Override
	//查询联系人的姓名和电话
	public Cursor loadInBackground() {
		String where=null;
		String whereArgs[]=null;
		if(args!=null){
			where="display_name like ? or data1 like ? ";
			whereArgs=new String[]{"%"+args.getString("key")+"%","%"+args.getString("key")+"%"};
		}
		return getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				new String[]{"_id","display_name","data1"}, 
				where, whereArgs, null);
	}
}
