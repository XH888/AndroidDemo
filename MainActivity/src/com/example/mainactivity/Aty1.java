package com.example.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Aty1 extends Activity {
	private Button butActy1;
	private TextView txtMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty1);
		butActy1=(Button) findViewById(R.id.butAty1);
		txtMsg=(TextView) findViewById(R.id.txtMsg);
		//txtMsg.setText(getIntent().getStringExtra("txt"));
		Bundle date=getIntent().getExtras();
		txtMsg.setText(date.getString("txt"));
		
		butActy1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent();
				i.putExtra("txtReturn", "这是Acty1返回的值;");
				setResult(0,i);
				finish();
			}
		});
	}
}
