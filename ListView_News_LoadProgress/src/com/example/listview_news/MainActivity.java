package com.example.listview_news;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.FeedAdapter;
import com.example.bean.Feed;
import com.example.bean.FeedCategory;
import com.example.utils.CategroyTask;
import com.example.utils.FeedTask;
import com.example.utils.Urls;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private Spinner cateSpinner;
	private List<FeedCategory> fcdatas; 
	private List<Feed> fdatas; 
 	private ArrayAdapter<FeedCategory> fcadapter;
 	private FeedAdapter fadapter;
	private ListView lv;
	private ProgressDialog pd;

	private Button moreBtn;
	private LinearLayout proLayout;
	
	
	private int currPage=1;	//当前页面
	private int cateId;
	private boolean isLoading=false; //标识是否正在加载数据	false：标识不需要加载
	private boolean flag=false; //标识ListView是否到底部
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pd=new ProgressDialog(this);	//进度条显示 context需要传this对象
		pd.setMessage("Loading...");
		
		moreBtn=(Button) findViewById(R.id.btnId);
		proLayout=(LinearLayout) findViewById(R.id.progLayoutId);
		
		initAndLoadFeedCategoryDatas();
		
		initLoadFeedDatas();
	}
	
	//获取Feed数据
	private void initLoadFeedDatas() {
		lv=(ListView) findViewById(R.id.lvid);
		fdatas=new ArrayList<Feed>();
		fadapter=new FeedAdapter(getApplicationContext(), fdatas);
		lv.setAdapter(fadapter);
		
		//设置滚动事件
		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(flag&&scrollState==OnScrollListener.SCROLL_STATE_IDLE&&!isLoading){
					//loadFeedData();
					moreBtn.setVisibility(View.VISIBLE);
				}else {
					moreBtn.setVisibility(View.GONE);
				}
			}
			
			/*
			 * view:代表当前的listView
			 * firstVisibleItem：可见的当前第一个Item
			 * visibleItemCount：可见的 Item 的数量
			 * totalItemCount：listView的Item的总数；
			 */
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem+visibleItemCount==totalItemCount){
					flag=true;
				}else{
					flag=false;
				}
			}
		});
	}

	public void loadMore(View view) {
		view.setVisibility(View.GONE);
		loadFeedData();
		
	}
	
	//获取分类数据
	private void initAndLoadFeedCategoryDatas() {
		cateSpinner=(Spinner) findViewById(R.id.cateSpinnerId);
		fcdatas=new ArrayList<FeedCategory>();
		fcadapter=new ArrayAdapter<FeedCategory>(getApplicationContext(), R.layout.item_cate, fcdatas);
		cateSpinner.setAdapter(fcadapter);
		
		//分类下拉框改变时候，更新feed数据；
		cateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,int position, long id) {
				fdatas.clear();
				moreBtn.setVisibility(View.GONE);
				currPage=1;	//重置分类时候，需要重新载入数据
				cateId = fcdatas.get(position).getId();	//动态改变全局的分类
				loadFeedData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
		
		//通过回调进行设置从网络取得的值
		new CategroyTask(new CategroyTask.CallBack(){
			@Override
			public void response(List<FeedCategory> list) {
				fcdatas.addAll(list);
				fcadapter.notifyDataSetChanged();
			}
		}).execute(Urls.CATEGORY_URL);
	}

	private void loadFeedData() {
		isLoading=true;
		//pd.show();		//显示滚动条
		proLayout.setVisibility(View.VISIBLE);
		String url=String.format(Urls.LIST_URL, cateId,currPage);
		new FeedTask(new FeedTask.CallBack() {
			@Override
			public void response(List<Feed> list) {
				Log.i("info", "load ok,currPage:"+currPage+",cateId:"+cateId);
				fdatas.addAll(list);
				fadapter.notifyDataSetChanged();
				
				isLoading=false;
				currPage++;
				//pd.hide();	//隐藏滚动条
				proLayout.setVisibility(View.GONE);
			}
		}).execute(url);
	}
}
