package com.example.fragment05;

import java.util.LinkedList;

import com.example.fragment.ContentFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private LinkedList<Fragment> fragments=new LinkedList<Fragment>();
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
		
		//removePre(getFragmentManager().beginTransaction());	//删除当前的碎片

		if(fragments.isEmpty()){
			getFragmentManager().beginTransaction().add(R.id.fragmentId, cFragment,"content").commit();
		}else{
			getFragmentManager().beginTransaction().hide(fragments.peek()).add(R.id.fragmentId, cFragment,"content").commit();
		}
		
		fragments.push(cFragment);
	}

	private void removePre(FragmentTransaction transaction) {
		//将Fragment添加到布局文件中	第一个参数是Fragment布局的ID资源文件
		Fragment preFragment=getFragmentManager().findFragmentByTag("content");
		if(preFragment!=null){
			transaction.remove(preFragment);
		}
	}
	
	public void backFragment(View view) {
		if(!fragments.isEmpty()){
			getFragmentManager().beginTransaction().remove(fragments.poll()).commit();
			if(!fragments.isEmpty()){
				getFragmentManager().beginTransaction().show(fragments.peek()).commit();
			}
		}
	}
}
