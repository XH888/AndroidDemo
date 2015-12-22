package com.example.actionbar02;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor>{
	private ListView lvId;
	private SimpleCursorAdapter adapter;
	private Uri rawcontactUrl=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	private String[] colums={"_id","display_name","data1"};
 	
	private SearchView searchItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvId=(ListView) findViewById(R.id.lvId);
		adapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.item_contact_phone,null,new String[]{"display_name","data1"},new int[]{R.id.nameId,R.id.phoneId});
		
		lvId.setAdapter(adapter);
		
		getLoaderManager().initLoader(3, null, this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自动生成的方法存根
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item= menu.findItem(R.id.action_search);
		searchItem = (SearchView) item.getActionView();
		
		searchItem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO 自动生成的方法存根
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				Bundle bundle=new Bundle();
				bundle.putString("key", newText);
				getLoaderManager().restartLoader(3,bundle,MainActivity.this);
				return false;
			}
		});
		return true;
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String where=null;
		String whereArgs[]=null;
		if(args!=null){
			where ="display_name like ? or data1 like ? ";
			String key="%"+args.getString("key")+"%";
			whereArgs=new String[]{key,key};
		}
		
		return new CursorLoader(getApplicationContext(),rawcontactUrl,colums,where,whereArgs,null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
}
