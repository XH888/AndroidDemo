package com.example.storage01;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tv;
	private int changeBackgroundColor;
	private int changeFontColor;
	private float addfontSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tvId);
		registerForContextMenu(tv);
		read(null);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.changeBackgroundColor:
				changeBackgroundColor=Color.rgb((int)(Math.random()*256),(int)(Math.random()*256), (int)(Math.random()*256));
				tv.setBackgroundColor(changeBackgroundColor);
				break;
			case R.id.changeFontColor:
				changeFontColor=Color.rgb((int)(Math.random()*256),(int)(Math.random()*256), (int)(Math.random()*256));
				tv.setTextColor(changeFontColor);
				break;
			case R.id.addfontSize:
				addfontSize+=tv.getTextSize()+10;
				tv.setTextSize(addfontSize);
				break;
			default:
				break;
		} 
		return super.onContextItemSelected(item);
	}

	public void read(View view) {
		SharedPreferences sf = getSharedPreferences("set", Context.MODE_PRIVATE);
		tv.setTextColor(sf.getInt("textColor", tv.getCurrentTextColor()));
		tv.setBackgroundColor(sf.getInt("backGroundColor", ((ColorDrawable)tv.getBackground()).getColor()));
		tv.setTextSize(sf.getFloat("textSize" , tv.getTextSize()));
		tv.setAllCaps(sf.getBoolean("allCaps", true));
		Toast.makeText(getApplicationContext(), "read ok", 1).show();
	}

	public void write(View view) {
		SharedPreferences sf = getSharedPreferences("set", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = sf.edit();
		edit.putInt("textColor", changeFontColor);
		edit.putInt("backGroundColor",changeBackgroundColor);
		edit.putFloat("textSize",addfontSize);
		edit.putBoolean("allCaps", true);
		edit.commit();
		Toast.makeText(getApplicationContext(), "write ok", 1).show();
	}
	
	@Override
	protected void onDestroy(){
		write(null);
		super.onDestroy();
	}
}
