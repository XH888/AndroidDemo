package com.demo.activtiy03;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static Map<String,Object> info=new HashMap<String,Object>();
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView) findViewById(R.id.firstView);
	}
	
	public void showSecondActivty(View view){
		Intent i=new Intent(getApplicationContext(),B_Activty.class);
		//直接通过Intent传值
		i.putExtra("msg", "第一个页面向第二个传值");
		i.putExtra("mame", "dison");
		
		//通过Intent Bundle传值
		Bundle bundle=new Bundle();
		bundle.putString("sex", "男");
		bundle.putInt("age",20);
		
		MyApplication myapp=(MyApplication) getApplication();
		myapp.appInfo.put("sex","男");
		myapp.appInfo.put("age", 10);
		
		i.putExtras(bundle);
		startActivity(i);
	}
	
	
	public void showAndReqB(View view){
		Intent i=new Intent(getApplicationContext(),B_Activty.class);
		startActivityForResult(i, 10);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自动生成的方法存根
		if(requestCode==10&&resultCode==RESULT_OK){
			tv.setText(data.getStringExtra("retmsg"));
		}
		super.onActivityResult(requestCode, resultCode, data);
		
	}
}
