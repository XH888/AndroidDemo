package com.example.actionbar03;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ContentFragment extends ListFragment {

	private String info;
	
	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		info=getArguments().getString("info");
		
		adapter=new ArrayAdapter<String>(getActivity(), R.layout.item_content);
		
		for(int i=0;i<20;i++)
			adapter.add(info+" --> "+i);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(adapter);
	}
}
