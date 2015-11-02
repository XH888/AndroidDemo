package com.example.servicedemo;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button btnStart,btnStop;
	private Intent intentService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);

		intentService=new Intent(this, ExtraService.class);
	}

	
	@Override
	public void onClick(View v) {
		//启动服务和停止服务
		switch (v.getId()) {
		case R.id.btnStart:
			startService(intentService);
			break;
		case R.id.btnStop:
			stopService(intentService);
			break;
		default:
			break;
		}
	}

}
