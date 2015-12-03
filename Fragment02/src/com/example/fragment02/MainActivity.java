package com.example.fragment02;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//取得系统的FragmentManager对象
		fragmentManager = getFragmentManager();	
	}

	public void showInfo(View view){
		//通过FragmentManager返回关联的Fragment对象
		Fragment fragment=fragmentManager.findFragmentById(R.id.fragment2Id);
		
		View v=fragment.getView();
		
		TextView txt2View=(TextView) v.findViewById(R.id.nameId);
		
		setTitle(txt2View.getText());
	}
}
