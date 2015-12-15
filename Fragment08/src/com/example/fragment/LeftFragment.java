package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragment08.ContentActivity;
import com.example.fragment08.R;

public class LeftFragment extends Fragment{
	private List<String> datas=new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private ListView lvView;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		datas.add("day1.txt");
		datas.add("day2.txt");
		datas.add("day3.txt");
		datas.add("day4.txt");
		datas.add("day5.txt");
		datas.add("day6.txt");
		adapter=new ArrayAdapter<String>(getActivity(),R.layout.day_activity,datas);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		lvView = (ListView) inflater.inflate(R.layout.fragment_left, null);
		lvView.setAdapter(adapter);
		return lvView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		lvView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
				Toast.makeText(getActivity(), datas.get(position),1).show();
				Bundle args=new Bundle();
				args.putString("day", datas.get(position));
				if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
					ContentFragment cFragment=new ContentFragment();
					cFragment.setArguments(args);
					getFragmentManager().beginTransaction().replace(R.id.fragmentView, cFragment).addToBackStack(null).commit();
				}else{
					Intent intent=new Intent(getActivity(),ContentActivity.class);
					intent.putExtras(args);
					startActivity(intent);
				}
			}
		});
		super.onActivityCreated(savedInstanceState);
	}
}
