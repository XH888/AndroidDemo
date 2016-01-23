package com.example.viewpager03;

import java.util.ArrayList;
import java.util.List;

import com.example.fragment.ContentFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity{
	
	private ViewPager viewPager;
	private List<Fragment> fragments;
	private FragmentPagerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager=(ViewPager) findViewById(R.id.viewPagerId);
		
		//初始化数据
		initFragment();
	}

	private void initFragment() {
		fragments=new ArrayList<Fragment>();
		fragments.add(ContentFragment.newInstance("A"));
		fragments.add(ContentFragment.newInstance("B"));
		fragments.add(ContentFragment.newInstance("C"));
		fragments.add(ContentFragment.newInstance("D"));
		fragments.add(ContentFragment.newInstance("E"));
		
		adapter=new MyPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(adapter);
	}

	class MyPagerAdapter extends FragmentPagerAdapter{

		//FragmentPagerAdapter必须要实现的一个构造方法；
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
	}
}
