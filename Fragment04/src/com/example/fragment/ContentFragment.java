package com.example.fragment;

import com.example.fragment04.R;

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
	private static final String TAG="ContentFragment";
	
	@Override
	public void onAttach(Activity activity) {
		Log.i("info",TAG + " -- > onAttach");  
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i("info",TAG + " -- > onCreate");  
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.i("info",TAG + " -- > onCreateView");  
		return inflater.inflate(R.layout.fragment_content, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i("info",TAG + " -- > onActivityCreated");  
		super.onActivityCreated(savedInstanceState);
	}
	
	
	@Override
	public void onStart() {
		Log.i("info",TAG + " -- > onStart");  
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.i("info",TAG + " -- > onResume");  
		super.onResume();
	}
	
	@Override
	public void onPause() {
		Log.i("info",TAG + " -- > onPause");  
		super.onPause();
	}
	
	@Override
	public void onStop() {
		Log.i("info",TAG + " -- > onStop");  
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {
		Log.i("info",TAG + " -- > onDestroyView");  
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.i("info",TAG + " -- > onDestroy");  
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
		Log.i("info",TAG + " -- > onDetach");  
		super.onDetach();
	}
}
