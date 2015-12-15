package com.example.listfragment;

import java.util.ArrayList;
import java.util.List;

import com.example.listfragment01.R;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PersonFragment extends ListFragment {
	private List<String> datas;
	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datas=new ArrayList<String>();
		adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.item,datas);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		for(int i=0;i<50;i++) datas.add("Person -> "+i);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Toast.makeText(getActivity(),datas.get(position),1).show();
	}
}
