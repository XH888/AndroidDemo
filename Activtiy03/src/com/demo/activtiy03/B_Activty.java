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
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		
		firstView=(TextView) findViewById(R.id.firstView);
		
		MyApplication myapp=(MyApplication) getApplication();
		
		
		firstView.setText(myapp.appInfo.get("sex")+"\n"+myapp.appInfo.get("age"));
		
		
		firstView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent data=new Intent();
				data.putExtra("retmsg", "�����Activity�ķ���ֵ");
				setResult(RESULT_OK, data);
				finish();//����activity
			}
		});
	}
}
