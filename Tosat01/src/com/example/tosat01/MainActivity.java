package com.example.tosat01;

import android.app.Activity;
import android.app.ApplicationErrorReport.CrashInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void showTosat01(View view){
		Toast toast=new Toast(getApplicationContext());
		
		TextView tv=new TextView(getApplicationContext());
		tv.setText("这个是实例化构造的Toast方法");
		tv.setTextColor(Color.RED);
		tv.setPadding(20, 20, 20, 20);
		tv.setBackgroundColor(Color.argb(128,0,0,0));		//设置 透明度和颜色背景
		
		tv.setAlpha(0.5f);	//设置透明度
		//将toast设置到textView
		toast.setView(tv);
		toast.setDuration(Toast.LENGTH_LONG);	//设置显示的时间
		
		//设置显示的位置;后面2个参数是表示偏移量。	120px/(320/160)	--> 60dp
		toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 320);
		
		toast.show();
	}
	
	public void showTosat02(View view){
		Toast toast=Toast.makeText(getApplicationContext(), "Toast 静态构造创建...", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL , 0, 0);
	
		toast.show();
	}
}
