package com.example.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fragment08.R;

public class ContentFragment extends Fragment{
	private String textContent;
	private TextView txView;
	private String fileName;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		//读取文件
		fileName=getArguments().getString("day");
		InputStream input;
		try {
			input = getResources().getAssets().open(fileName);
			BufferedReader bf=new BufferedReader(new InputStreamReader(input));
			StringBuilder sb=new StringBuilder();
			String s;
			while ((s=bf.readLine())!=null) {
				sb.append(s+"\n");
			}
			textContent=sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view= inflater.inflate(R.layout.fragment_content,null);
		txView = (TextView) view.findViewById(R.id.contentTxView); 
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		txView.setText(textContent);
	}
}
