package com.example.listfragment01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.listfragment.PersonFragment;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void showList(View view){
		getFragmentManager().beginTransaction().replace(R.id.listFragId, new PersonFragment()).commit();
	}
}
