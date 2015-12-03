package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment07.R;

public class LeftFragment extends Fragment {

	private List<String> datas=new ArrayList<String>();
	private ListView lv;
	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		datas.add("day1.txt");
		datas.add("day2.txt");
		datas.add("day3.txt");
		datas.add("day4.txt");
		datas.add("day5.txt");

		adapter=new ArrayAdapter<String>(getActivity(), R.layout.fragment_day,datas);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_left,null);
		TextView tv=(TextView) view.findViewById(R.id.lvId);
		lv=(ListView) getActivity().findViewById(R.id.lvId);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				String name=datas.get(position);
				Toast.makeText(getActivity(), "----》"+name,1).show();
			}
		});
	}
}
