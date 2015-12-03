package com.example.fragment;

import com.example.fragment03.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_left, null);
		TextView tv=(TextView) v.findViewById(R.id.txId);
		tv.setTextColor(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
		
		Bundle args=getArguments();
		
		tv.setText(String.valueOf(args.getLong("msg")));		//取得传过来的Bundle值
		return v;
	}
}
