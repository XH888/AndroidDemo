package com.example.viewpager01;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;

public class MainActivity extends Activity{
	private ViewPager pager=null;
	private PagerAdapter adapter;
	private List<ImageView> views;
	private LinearLayout.LayoutParams params;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pager=(ViewPager) findViewById(R.id.viewpager);
		adapter=new MyPagerAdapter(views);
	
		pager.setAdapter(adapter);
		
		ImageView img= new ImageView(getApplicationContext());
		img.setImageResource(R.drawable.ic_launcher);
		
		views.add(img);
		views.add(img);
		views.add(img);
		views.add(img);

		
	}
}
