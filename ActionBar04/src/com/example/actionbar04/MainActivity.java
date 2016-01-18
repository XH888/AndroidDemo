package com.example.actionbar04;

import com.example.fragment.CbkFragment;
import com.example.util.URLs;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;

public class MainActivity extends Activity implements TabListener{
	private ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1=actionBar.newTab();
		tab1.setText("投诉大厅").setTabListener(this).setTag(String.format(URLs.URL_LIST));
		actionBar.addTab(tab1, true);
		
		actionBar.addTab(actionBar.newTab().setText("受理进程").setTabListener(this).setTag(String.format(URLs.URL_LIST1,1)));
		actionBar.addTab(actionBar.newTab().setText("结案答复").setTabListener(this).setTag(String.format(URLs.URL_LIST1,2)));
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		String url=tab.getTag().toString();
		CbkFragment cbkfragment=new CbkFragment();
		Bundle args=new Bundle();
		
		args.putString("url", url);
		cbkfragment.setArguments(args);
		
		ft.replace(R.id.mainLayoutId, cbkfragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
}
