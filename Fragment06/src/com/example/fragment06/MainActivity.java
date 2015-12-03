package com.example.fragment06;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.fragment.ContentFragment;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	//动态显示Fragment
	public void addFragment(View view) {
		//实例化Fragment
		ContentFragment cFragment=new ContentFragment();
		
		Bundle args=new Bundle();
		args.putLong("msg", System.currentTimeMillis());
		
		cFragment.setArguments(args);
		
		//获取Fragment对象
		FragmentTransaction ft=getFragmentManager().beginTransaction();
		ft.replace(R.id.fragmentId, cFragment);
		ft.addToBackStack(null);		//将Fragment放到默认的堆栈中，name标识栈的标识符；
		ft.commit();
	}

	public void backFragment(View view) {
		onBackPressed();
	}
}
