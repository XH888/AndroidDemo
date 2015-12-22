package com.example.actionbar01;

import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tvId;
	private float fontSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvId=(TextView) findViewById(R.id.tvId);
		fontSize=tvId.getTextSize();
		
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		setIconEnable(menu, true);
		return true;
	}

	/*@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		//分发事件
		return onTouchEvent(ev);	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//3种类型的触屏事件	actionBar的显示和隐藏
		if(event.getAction()==MotionEvent.ACTION_UP){
			if(getActionBar().isShowing()){
				getActionBar().hide();
			}else{
				getActionBar().show();
			}
		}else if(event.getAction()==MotionEvent.ACTION_DOWN){
			return true;
		}
		return true;
	}*/
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_add:
				fontSize+=10;
				tvId.setTextSize(fontSize );
				break;
			case R.id.action_call:
	
				break;
			case R.id.action_camera:
	
				break;
			case R.id.action_delete:
				break;
			case android.R.id.home:
				Toast.makeText(getApplicationContext(), "--->actionBar home", 1).show();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
		
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
