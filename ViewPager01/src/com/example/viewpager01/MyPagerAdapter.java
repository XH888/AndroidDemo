package com.example.viewpager01;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPagerAdapter extends PagerAdapter {

	private List<ImageView> datas;
	public MyPagerAdapter(List<ImageView> datas){
		this.datas=datas;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(datas.get(position));
		return datas.get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(datas.get(position));
	}
}
