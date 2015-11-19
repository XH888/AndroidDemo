package com.example.intent02;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onCall(View v) {
		Intent intent=new Intent(Intent.ACTION_CALL );
		intent.setData(Uri.parse("tel:10086"));
		startActivity(intent);
	}

	public void onWeb(View v) {
		Intent intent=new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com"));
		startActivity(intent);
	}

	public void onShare(View v) {
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("text/*");
		intent.putExtra(Intent.EXTRA_TEXT,"要发送的信息。。。");
		startActivity(intent);
	}
	public void onSMS(View v) {
		Intent intent=new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("smsto:10086"));
		intent.putExtra( "sms_body","要发送短信的内容。。");	//短信内容的附加常量是 ‘sms_body’
		startActivity(intent);
	}
	
	
}
