package com.example.menu01;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvId=(TextView) findViewById(R.id.tvId);
	}
	
	@Override
	//创建一个Option Menu
	public boolean onCreateOptionsMenu(Menu menu){
		setIconEnable(menu, true);
		getMenuInflater().inflate(R.menu.main, menu);	//创建系统菜单
		return true;
	}

	@Override
	//选择Option Menu菜单触发的事件；
	public boolean onOptionsItemSelected(MenuItem item) {
		changeFontSize(item);
		return super.onOptionsItemSelected(item);
	}
	
	public void showPopupMenu(View v){
		//第一个参数是context对容器，第二个参数是调用着的锚点
		PopupMenu popm = new PopupMenu(getApplicationContext(), v);
		
		//通过资源menu ID取得 列表的对象；
		getMenuInflater().inflate(R.menu.main,popm.getMenu());
		
		setIconEnable(popm.getMenu(),true);
		
		popm.show();
		
		popm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				changeFontSize(item);
				return false;
			}
		});
	}
	
	//Menu Item的触发事件
	private void changeFontSize(MenuItem item){
		float fontSize=tvId.getTextSize();
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(Settings.ACTION_SETTINGS));
			break;
		case R.id.action_add:
			fontSize+=5;
			tvId.setTextSize(fontSize);
			break;
		case R.id.action_del:
			fontSize-=5;
			tvId.setTextSize(fontSize);
			break;
		default:
			break;
		}
	}
	
	//通过反射调用 显示菜单Item图标文件
	public void setIconEnable(Menu menu,boolean enable){
		try {
			Class clazz=Class.forName("com.android.internal.view.menu.MenuBuilder");
			Method m=clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
			m.setAccessible(true);
			m.invoke(menu, enable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
