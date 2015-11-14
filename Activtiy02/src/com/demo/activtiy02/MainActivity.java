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
		//ֱ��ͨ��Intent��ֵ
		i.putExtra("msg", "��һ��ҳ����ڶ�����ֵ");
		i.putExtra("mame", "dison");
		
		//ͨ��Intent Bundle��ֵ
		Bundle bundle=new Bundle();
		bundle.putString("sex", "��");
		bundle.putInt("age",20);
		
		//ͨ��ȫ�ֱ�����ֵ��������
		//info.put("info", "��̬��Ա��ֵ");
		
		i.putExtras(bundle);
		startActivity(i);
	}
}
