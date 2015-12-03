package com.example.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelloFragment extends Fragment {

	/**创建UI控件
	 * 第一个参数：资源加载器
	 * 第二个参数：当前Fragment所在的父布局的对象
	 * 第三个参数：存放的业务数据
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		TextView tv=new TextView(getActivity().getApplicationContext());
		tv.setText("Hello Fragment");
		tv.setTextSize(30);
		tv.setTextColor(Color.RED);
		//转变为标准尺寸的一个函数  10dp 
		int padding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
		tv.setPadding(padding, padding, padding, padding);
		
		return tv;
	}
}
