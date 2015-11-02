package com.example.intentdemo;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnAty1,btnImg,btnTel,btnWeb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnAty1=(Button) findViewById(R.id.btnAty1);
		btnImg=(Button) findViewById(R.id.btnImg);
		btnTel=(Button) findViewById(R.id.btnTel);
		btnWeb=(Button) findViewById(R.id.btnWeb);
		
		
		btnAty1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent("com.example.intentdemo.intent.action.Aty1");
				//i.setComponent(new ComponentName(Aty1.class.getPackage().toString(), Aty1.class.getName()));
				startActivity(i);
			}
		});
		btnImg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				File f=new File("/sdcard/1.jpg");
				Intent i=new Intent(Intent.ACTION_VIEW);
				i.setDataAndType(Uri.fromFile(f), "image/*");
				startActivity(i);
			}
		});
		btnTel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:10086"));
				startActivity(i);
			}
		});
		
		btnWeb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
				startActivity(i);
			}
		});
	}
}
