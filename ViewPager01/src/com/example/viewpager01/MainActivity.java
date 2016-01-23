package com.example.viewpager01;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.app.Activity;

public class MainActivity extends Activity {
	private ViewPager pager = null;
	private PagerAdapter adapter;
	private List<ImageView> views;
	private LinearLayout navLayout;
	private LinearLayout.LayoutParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 实例化控件
		pager = (ViewPager) findViewById(R.id.viewpager);
		navLayout = (LinearLayout) findViewById(R.id.navLayoutId);

		// 添加view对象
		views = new ArrayList<ImageView>();
		for (int i = 0; i < 3; i++) {
			ImageView img = new ImageView(getApplicationContext());
			img.setTag(i);
			img.setImageResource(R.drawable.logo_vip);
			img.setScaleType(ScaleType.CENTER);
			img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(),"点击图片TagId = " + v.getTag(), 0).show();
					//pager.setCurrentItem(Integer.valueOf(v.getTag().toString()));
				}
			});
			views.add(img);

			// 导航图片
			ImageView navimg = new ImageView(getApplicationContext());
			if (i == 0) {
				navimg.setImageResource(R.drawable.icon_choose_dot_s);
			} else {
				navimg.setImageResource(R.drawable.icon_choose_dot_n_1);
			}
			params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.leftMargin = 10;
			params.rightMargin = 10;
			navimg.setLayoutParams(params);
			navLayout.addView(navimg);
		}
		// 适配adapter
		adapter = new MyPagerAdapter();
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// 选择指定的页面事件
				selectNavImg(position);
			}

			/**
			 * 位置，偏移量，偏移像素大小
			 */
			@Override
			public void onPageScrolled(int position, float offset, int offsetpix) {
				// 滚动状态中的处理方法
				Log.i("info", "-- onPageScrolled -- "+offset+" -- "+offsetpix);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// 滚动状态变化的事件
				switch (state) {
				case ViewPager.SCROLL_STATE_DRAGGING: // 开始滚动
					Log.i("info", "SCROLL_STATE_DRAGGING -- 开始滚动");
					break;
				case ViewPager.SCROLL_STATE_IDLE: // 停止滚动
					Log.i("info", "SCROLL_STATE_IDLE -- 停止滚动");
					break;

				case ViewPager.SCROLL_STATE_SETTLING: // 滚动正在准备显示下个页面
					Log.i("info", "SCROLL_STATE_SETTLING -- 滚动正在准备显示下个页面");
					break;
				default:
					break;
				}
			}
		});

	}

	// 取得当前设置导航的状态
	public void selectNavImg(int position) {
		ImageView imgView = null;
		for (int i = 0; i < navLayout.getChildCount(); i++) {
			// 获取指定位置的子控件
			imgView = (ImageView) navLayout.getChildAt(i);
			if (i == position) {
				imgView.setImageResource(R.drawable.icon_choose_dot_s);
			} else {
				imgView.setImageResource(R.drawable.icon_choose_dot_n_1);
			}
		}

	}

	class MyPagerAdapter extends PagerAdapter {
		// 获取当前窗体的界面数
		@Override
		public int getCount() {
			return views.size();
		}

		// 判断对象是否是由界面生成
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 当前适配器选择那个对象放在ViewPager上面
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position)); // 添加到容器中
			return views.get(position);
		}

		// 销毁当前那个View
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}
	}
}
