package com.example.fragmentdemo;

import com.example.fragmentdemo.constant.Constant;
import com.example.fragmentdemo.ui.BottomControlPanel;
import com.example.fragmentdemo.ui.BottomControlPanel.BottomPanelCallback;
import com.example.fragmentdemo.ui.HeadControlPanel;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends Activity implements BottomPanelCallback{
	private BottomControlPanel bottomPanel;
	private HeadControlPanel headPanel;
	private FragmentManager fragementManager;
	private FragmentTransaction fragementTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void initUi(){		//初始化所有的组件;
		bottomPanel=(BottomControlPanel) findViewById(R.id.bottom_layout);
		if(bottomPanel!=null){
			bottomPanel.initBottomPanel();
			bottomPanel.setBottomCallback(this);
		}
		headPanel=(HeadControlPanel) findViewById(R.id.head_layout);
		if(headPanel!=null){
			headPanel.initHeadPanel();
		}
		fragementManager=getFragmentManager();
		setDefaultFirstFragment(Constant.FRAGMENT_FLAG_CONTACTS);
	}
	
	@Override
	public void onBottomPanelClick(int itemID) {
		String tag="";
		if((itemID& Constant.BTN_FLAG_MESSAGE)!=0){
			tag=Constant.FRAGMENT_FLAG_MESSAGE;
		}else if ((itemID&Constant.BTN_FLAG_NEW)!=0){
			tag=Constant.FRAGMENT_FLAG_NEW;
		}else if ((itemID&Constant.BTN_FLAG_SETTING)!=0){
			tag=Constant.FRAGMENT_FLAG_SETTING;
		}else if ((itemID&Constant.BTN_FLAG_CONTACTS)!=0){
			tag=Constant.FRAGMENT_FLAG_CONTACTS;
		}
		
	}
	
	public void setDefaultFirstFragment(String tag){
		setTabSelection(tag);
		bottomPanel.defaultBtnChecked();
	}
	
	private void commitTransactions(String tag){
		if(fragementTransaction!=null&&!fragementTransaction.isEmpty()){
			fragementTransaction.commit();
			fragementTransaction=null;
		}
	}
	
	public void setTabSelection(String tag){
		fragementTransaction=fragementManager.beginTransaction();
		fragementTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
	}
}
