package com.example.viewpager02;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ViewPager viewPager;
	private List<View> views;
	private PagerAdapter adapter;
	
	private HorizontalScrollView hScrollView;
	private LinearLayout linearLayoutId2;
	private View markingView;
	private LayoutParams paramsMarking;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager=(ViewPager) findViewById(R.id.viewPagerId);
		markingView=findViewById(R.id.markingView);
		linearLayoutId2=(LinearLayout) findViewById(R.id.linearLayoutId2);
		hScrollView=(HorizontalScrollView) findViewById(R.id.hScrollViewId);
		paramsMarking=(LayoutParams) markingView.getLayoutParams();

		//设置ViewPager的事件方法；
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				//取得子控件 控件设置标题
				RelativeLayout reLayout = (RelativeLayout) views.get(position);
				TextView tv=(TextView) reLayout.getChildAt(0);
				setTitle(tv.getText());
				
				//将HScrollView滚动到中间，并且设置其子控件属性
				selectModule(position);
			}

			@Override
			public void onPageScrolled(int position, float offset, int offsetPixels) {
				//计算滚动条距离右边的margin值	(offset（偏移百分比）+position（偏移位置）)* 滚动条控件的宽
				paramsMarking.leftMargin = (int) (paramsMarking.width*(position+offset));
				markingView.setLayoutParams(paramsMarking);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		//创建适配器
		adapter=new MyPagerAdapter();
	
		//初始化资源列表
		initViewAdapter();
		
		//设置适配器
		viewPager.setAdapter(adapter);
		
		//将ViewPager子控件 保存在内存中 的限制；
		viewPager.setOffscreenPageLimit(views.size());

		//添加HScrollView中TextView点击事件
		ItemClick();
	}


	private void initViewAdapter() {
		views=new ArrayList<View>();
		for(int i=1;i<=3;i++){
			//取得资源文件的Id
			int layoutResId=getResources().getIdentifier("pager_view0"+i, "layout", getPackageName());
			
			//将资源文件ID添加到views中；
			views.add(getLayoutInflater().inflate(layoutResId, null));
		}
	}
	
	private void ItemClick() {
		View tv=null;
		for(int i=0;i<linearLayoutId2.getChildCount();i++){
			tv= linearLayoutId2.getChildAt(i);
			tv.setTag(i);
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position=(Integer) v.getTag();
					viewPager.setCurrentItem(position);
				}
			});
		}
	}
	
	private void selectModule(int position) {
		TextView moduleView= null;
		for(int i=0;i<linearLayoutId2.getChildCount();i++){
			moduleView=(TextView) linearLayoutId2.getChildAt(i);
			if(i==position){
				moduleView.setTextColor(Color.BLUE);
			}else{
				moduleView.setTextColor(Color.BLACK);
			}
		}
		moduleView=(TextView) linearLayoutId2.getChildAt(position);
		//获取指定位置的模块控件的位置
		int left = moduleView.getLeft();
		//取得屏幕宽度；
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		
		int offset = left + moduleView.getWidth()/2 - screenWidth/2;
		
		//移动scroll控件位置
		hScrollView.smoothScrollTo(offset, 0);
	}
	
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			Log.i("info", " -- isViewFromObject -- ");
			return arg0==arg1;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			Log.i("info", " -- destroyItem -- ");
			container.removeView(views.get(position));
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Log.i("info", " -- instantiateItem -- ");
			container.addView(views.get(position));
			return views.get(position);
		}
	}
}
