package com.demo.activtiy02;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {
	private static Map<String,Object> info=new HashMap<String,Object>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		
		//通过全局变量传值；不建议
		//info.put("info", "静态成员传值");
		
		i.putExtras(bundle);
		startActivity(i);
	}
}
