package com.example.fragment03;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.fragment.LeftFragment;

public class MainActivity extends Activity {
	private FragmentManager fmanager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fmanager=getFragmentManager();	//获取FragmentManager对象
	}
	
	public void showFragment(View view) {
		FragmentTransaction ftransaction=fmanager.beginTransaction();
		
		LeftFragment lf=new LeftFragment();
		Bundle bundle=new Bundle();
		bundle.putLong("msg",System.currentTimeMillis());
		lf.setArguments(bundle);	//将Bundle传入Fragment
		
		ftransaction.replace(R.id.containerId,lf);
		
		ftransaction.commit();
	}
}
