package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.viewpager03.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class ContentFragment extends ListFragment{
	private String title;
	private List<String> datas;
	private ArrayAdapter<String> adapter;
	
	public static ContentFragment newInstance(String title){
		ContentFragment cf=new ContentFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		cf.setArguments(args);
		return cf;
	}
	
	//创建ListFragment
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("info", "-- onCreate --");
		title = getArguments().getString("title");
		datas=new ArrayList<String>();
		for(int i=0;i<20;i++){
			datas.add(title + " -- " + i);
		}
		adapter=new ArrayAdapter<String>(getActivity(),R.layout.item,datas);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(adapter);
	}


	//ListItem单击事件；方法继承ContentFragment
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getActivity(),"-->" + datas.get(position), 0).show();
	}
	
	//ListFragment中的子控件创建
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("info", "-- onCreateView --");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	//ListFragment中的子控件销毁
	@Override
	public void onDestroyView() {
		Log.i("info", "-- onDestroyView --");
		super.onDestroyView();
	}
	
	//ListFragment销毁
	@Override
	public void onDestroy() {
		Log.i("info", "-- onDestroy --");
		super.onDestroy();
	}
}
