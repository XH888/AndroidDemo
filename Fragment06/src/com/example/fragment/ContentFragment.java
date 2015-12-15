package com.example.fragment;

import com.example.fragment06.R;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
	
	private Long msg;
	@Override
	public void onAttach(Activity activity) {
		msg=getArguments().getLong("msg");
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.i("info", " ContentFragment -> onCreateView msg:"+msg);
		View view=inflater.inflate(R.layout.fragment_item,null);
		TextView tv=(TextView) view.findViewById(R.id.tvId);
		int c=(int)(Math.random()*256);
		tv.setBackgroundColor(Color.rgb(c,(int)(Math.random()*256), (int)(Math.random()*256)));
		tv.setText(String.valueOf(msg));
		return view;
	}
	
	@Override
	public void onDestroyView() {
		Log.i("info", "ContentFragment -> onDestroyView msg:"+msg);
		super.onDestroyView();
	}
	
}	
