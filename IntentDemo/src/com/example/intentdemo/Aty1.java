package com.example.intentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Aty1 extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView iv= new ImageView(this);
		iv.setImageResource(R.drawable.ic_launcher);
		setContentView(iv);
	}
	
}
