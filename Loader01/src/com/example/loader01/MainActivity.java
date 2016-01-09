package com.example.loader01;

import android.os.Bundle;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor>{
	private SearchView searchView;
	private ListView lv;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lvId);
		searchView=(SearchView) findViewById(R.id.searchViewId);	//searchView搜索框

		adapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.item_contact_phone,null,
				new String[]{"display_name","data1"},new int[]{R.id.nameId,R.id.phoneId},
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		lv.setAdapter(adapter);

		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				// 按搜索键提交事件
				return false;
			}
			@Override
			public boolean onQueryTextChange(String newText) {
				Bundle args=new Bundle();
				args.putString("key", newText);
				getLoaderManager().restartLoader(0, args, MainActivity.this);	//重新载入Loader
				return false;
			}
		});
		getLoaderManager().initLoader(0, null, this);	//初始化Loader
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// 创建调用返回Loader加载器
		return new ContactLoader(getApplicationContext(),args);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);	//调用完成自动载入adapter数据
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
}
