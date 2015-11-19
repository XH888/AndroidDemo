package com.example.intent01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout=new LinearLayout(getApplicationContext());
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		Button btn=new Button(getApplicationContext());
		btn.setText(" 隐式跳转到C ");
		layout.addView(btn);
		
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setAction("com.example.intent01.CActivity");
				startActivity(intent);
			}
		});
		setContentView(layout);	//设置布局对象
	}
}
