package com.example.listview_scroll;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView lvId;
	private ListAdapter adapter;
	
	private static String[] datas= {"a","b","c","d","e","a","b","c","d","e","a","b","c","d","e","a","b","c","d","e"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvId=(ListView) findViewById(R.id.lvId);
		
		adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.item_textview,datas);
		lvId.setAdapter(adapter);
		
		lvId.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState){
				switch (scrollState){
					case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
						Log.i("info", "- SCROLL_STATE_TOUCH_SCROLL -");
						break;
					case OnScrollListener.SCROLL_STATE_FLING:	
						Log.i("info", "- SCROLL_STATE_FLING -");
						break;
					case OnScrollListener.SCROLL_STATE_IDLE:
						Log.i("info", "- SCROLL_STATE_IDLE -");
						break;
					default:
						break;
				}
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				Log.i("info", "view -"+view.getId()+" - firstVisibleItem:"+firstVisibleItem+
						" - visibleItemCount:"+visibleItemCount+
						" - totalItemCount:"+totalItemCount);
				
			}
		});
	}
	
}
