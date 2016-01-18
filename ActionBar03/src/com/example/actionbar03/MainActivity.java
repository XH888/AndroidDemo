package com.example.actionbar03;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements TabListener{

	private ActionBar actionBar;
	
	private ArrayAdapter<String> adapter;
	
	private Map<Integer,Fragment> map=new HashMap<Integer,Fragment>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
	}
	
	private void initActionBar() {
		actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//创建Tab 并且添加将Tab 添加到ActionBar中
		ActionBar.Tab tab1=actionBar.newTab();
		tab1.setText("Tab1")
			.setIcon(android.R.drawable.ic_menu_add)
			.setTabListener(this);
		actionBar.addTab(tab1,true);
		
		actionBar.addTab(actionBar.newTab().setText("Tab2").setIcon(android.R.drawable.ic_menu_delete).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Tab3").setIcon(android.R.drawable.ic_menu_edit).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Tab4").setIcon(android.R.drawable.ic_menu_save).setTabListener(this));
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//Tab被被选中的事件；
		Fragment f=map.get(tab.getPosition());
		if(f==null){
			f=new ContentFragment();
			map.put(tab.getPosition(), f);
			Bundle args=new Bundle();
			args.putString("info", tab.getText().toString());
			f.setArguments(args);
			ft.add(R.id.mainLayoutId,f);
		}else{
			ft.show(f);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//Tab取消被选中的事件；
		Fragment f=map.get(tab.getPosition());
		if(f!=null)
			ft.hide(f);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Tab重新被选中的事件；
	}
}
