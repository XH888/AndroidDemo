package com.example.backbutton;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	private long clickTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	public void onBackPressed() {
		long currentTime=System.currentTimeMillis();
		if(currentTime-clickTime<1000){
			finish();
		}else{
			Toast.makeText(this, "在按一次退出", Toast.LENGTH_SHORT).show();
			clickTime=currentTime;
		}
	}
}
