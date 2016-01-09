package com.example.contentresolver02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	// 访问联系人的信息 ID 和 姓名
	private Uri rawUri = Uri.parse("content://com.android.contacts/raw_contacts");
	private String[] rawColums = { "_id", "display_name" };

	// 访问联系人的数据信息 电话号码、邮箱等
	private Uri dataUri = Uri.parse("content://com.android.contacts/data");
	private String[] dataColums = { "data1" };

	private ListView lv;
	private List<Map<String,Object>> list;
	private SimpleAdapter adapter;
	
	private AlertDialog editDialog;
	private EditText nameId,phoneId,emailId;
	
	private boolean isAdd=false;	//判断是增加还是编辑
	
	private int currPosition;	//保存当前的Item的位置
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lv);
		list=new ArrayList<Map<String,Object>>();
		adapter=new SimpleAdapter(getApplicationContext(), list, R.layout.item_contact_person, 
				new String[]{ "name","phone","email"}, 
				new int[]{R.id.nameId,R.id.phoneId,R.id.emailId});
		lv.setAdapter(adapter);
		
		initDialog();
		
		registerForContextMenu(lv);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.main, menu);
		currPosition=((AdapterContextMenuInfo)menuInfo).position;
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_update:	//添加或者更新
			isAdd=false;
			nameId.setText(String.valueOf(list.get(currPosition).get("name")));
			phoneId.setText(String.valueOf(list.get(currPosition).get("phone")));
			emailId.setText(String.valueOf(list.get(currPosition).get("email")));
			editDialog.show();
			break;
		case R.id.action_del:	//删除
			getContentResolver().delete(rawUri, "_id="+list.get(currPosition).get("id"), null);
			getContentResolver().delete(dataUri, "raw_contact_id="+list.get(currPosition).get("id"), null);
			LoadContact(null);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	private void initDialog() {
		list.clear();
		
		View view=getLayoutInflater().inflate(R.layout.dialog_contact_person, null);
		nameId=(EditText) view.findViewById(R.id.nameId);
		phoneId=(EditText) view.findViewById(R.id.phoneId);
		emailId=(EditText) view.findViewById(R.id.emailId);
		
		editDialog=new AlertDialog.Builder(this)
				.setTitle("设置联系人信息")
				.setIcon(android.R.drawable.ic_menu_add)
				.setView(view)
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ContentValues values=new ContentValues();
						String name=nameId.getText().toString();
						String phone=phoneId.getText().toString();
						String email=emailId.getText().toString();
						if(isAdd){
							values.put("display_name",name );
							values.put("display_name_alt", name);
							Uri newUri=getContentResolver().insert(rawUri, values);	//插入到raw_contacts。并且返回新的数据的游标URI地址
							
							//解析新的content_id的值
							long raw_contact_id=ContentUris.parseId(newUri);
							values.clear();
							
							values.put("raw_contact_id",raw_contact_id);
							values.put("data1",name);
							values.put("mimetype","vnd.android.cursor.item/name");
							getContentResolver().insert(dataUri, values);	//插入到raw_contacts。并且返回新的数据的游标URI地址
							
							values.put("data1",phone);
							values.put("mimetype","vnd.android.cursor.item/phone_v2");
							getContentResolver().insert(dataUri, values);
							
							values.put("data1",email);
							values.put("mimetype","vnd.android.cursor.item/email_v2");
							getContentResolver().insert(dataUri, values);
							
						}else{
							//修改联系人信息；
							values.put("display_name",name );
							values.put("display_name_alt", name);
							getContentResolver().update(rawUri, values,
									"_id="+Long.parseLong(list.get(currPosition).get("id").toString()),null);	//插入到raw_contacts。并且返回新的数据的游标URI地址
							values.clear();
							
							//更新联系人姓名
							values.put("data1", name);
							getContentResolver().update(dataUri, values,
									"mimetype_id=7 and raw_contact_id="+Long.parseLong(list.get(currPosition).get("id").toString()),null);	//插入到raw_contacts。并且返回新的数据的游标URI地址
						
						
							//更新联系人电话
							values.put("data1", phone);
							getContentResolver().update(dataUri, values,
									"mimetype_id=5 and raw_contact_id="+Long.parseLong(list.get(currPosition).get("id").toString()),null);	//插入到raw_contacts。并且返回新的数据的游标URI地址

							
							//更新联系人邮件
							values.put("data1", email);
							getContentResolver().update(dataUri, values,
									"mimetype_id=1 and raw_contact_id="+Long.parseLong(list.get(currPosition).get("id").toString()),null);	//插入到raw_contacts。并且返回新的数据的游标URI地址

						}
						LoadContact(null);
					}
				})
				.setNegativeButton("取消", null)
				.setCancelable(false)
				.create();
	}
	
	public void editContact(View view) {
		isAdd=false;
		editDialog.show();
	}
	
	public void newContact(View view) {
		isAdd=true;
		nameId.setText("");
		phoneId.setText("");
		emailId.setText("");
		editDialog.show();
	}
	
	public void LoadContact(View view) {
		list.clear();
		Cursor cursor = getContentResolver().query(rawUri, rawColums, null, null, null);	//查询所有人员信息
		Map<String,Object> map=null;
		while (cursor.moveToNext()) {
			long id = cursor.getLong(0);
			String name = cursor.getString(1);
			map=new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			//根据ID和Type到data表中查找phone
			Cursor phoneCursor = getContentResolver().query(dataUri,
					dataColums, "mimetype_id=5 and raw_contact_id=" + id, null,
					null);
			if (phoneCursor.moveToNext()) {
				String phone = phoneCursor.getString(0);
				map.put("phone", phone);
			}
			//根据ID和Type到data表中查找email
			Cursor emailCursor = getContentResolver().query(dataUri,
					dataColums, "mimetype_id=1 and raw_contact_id=" + id, null,
					null);
			if (emailCursor.moveToNext()) {
				String email = emailCursor.getString(0);
				map.put("email", email);
			}
			list.add(map);
		}
		adapter.notifyDataSetChanged();
	}
}
