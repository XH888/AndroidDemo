package com.example.fragment08;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.fragment.ContentFragment;

public class ContentActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		ContentFragment cFragment=new ContentFragment();
		cFragment.setArguments(getIntent().getExtras());
		
		getFragmentManager().beginTransaction().add(R.id.contentViewId, cFragment).commit();
		
	}
}
