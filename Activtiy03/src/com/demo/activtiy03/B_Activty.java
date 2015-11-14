package com.demo.activtiy03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class B_Activty extends Activity {
	private TextView firstView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		
		firstView=(TextView) findViewById(R.id.firstView);
		
		MyApplication myapp=(MyApplication) getApplication();
		
		
		firstView.setText(myapp.appInfo.get("sex")+"\n"+myapp.appInfo.get("age"));
		
		
		firstView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent data=new Intent();
				data.putExtra("retmsg", "这个是Activity的返回值");
				setResult(RESULT_OK, data);
				finish();//结束activity
			}
		});
	}
}
