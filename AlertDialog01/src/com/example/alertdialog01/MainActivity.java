package com.example.alertdialog01;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Dialog dialog;
	
	private Dialog dialogColor;
	private ArrayAdapter<String> adapter;
	private TextView textView;
	
	private Dialog dialogFontSize;
	private float[] fontSize={15f,20f,25f,30f};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//颜色适配器
		adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.item_color);
		adapter.add("Red");
		adapter.add("Blue");
		adapter.add("Yellow");
		textView=(TextView) findViewById(R.id.tvId);
		
		createDialog();
		createDialogColor();
		createDialogFontSize();
	}
	
	
	private void createDialogFontSize() {
		//实例化 AlertDialog.Builder 创建 对话框参数
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("选择一种字体")
			.setIcon( android.R.drawable.ic_menu_crop )
			.setCancelable(false)	//禁止点击对话框以外的地方关闭对话框
			//设置单选框按钮的选择项
			//.setMultiChoiceItems(itemsId, checkedItems, listener)这个是多选框，同理
			.setSingleChoiceItems(R.array.fontSize, 2,new DialogInterface.OnClickListener(){	//设置单选框的资源列表和单击事件
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle(getResources().getStringArray(R.array.fontSize)[which]);
					textView.setTextSize(fontSize[which]);
					//关闭当前对话框
					dialogFontSize.dismiss();
				}
			});
		dialogFontSize = builder.create();
	}

	private void createDialogColor() {
		//实例化 AlertDialog.Builder 创建 对话框参数
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("选择一种颜色").setIcon(R.drawable.ic_launcher)
			.setCancelable(false)	//禁止点击对话框以外的地方关闭对话框
			//设置ListView的选择样式
			.setAdapter(adapter, new DialogInterface.OnClickListener() {	//设置列表的adapter和 点击事件
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle( adapter.getItem(which) );
					switch (which) {
					case 0:
						textView.setBackgroundColor(Color.RED);
						break;
					case 1:
						textView.setBackgroundColor(Color.BLUE);
						break;
					case 2:
						textView.setBackgroundColor(Color.YELLOW);
						break;
					default:
						break;
					}
				}
			});
		dialogColor = builder.create();
	}

	private void createDialog() {
		//实例化 AlertDialog.Builder 创建 对话框参数
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("这是标题").setIcon(R.drawable.ic_launcher)
			.setMessage("你确定退出吗?")	//设置头信息
			.setCancelable(false)	//禁止点击对话框以外的地方关闭对话框
			.setPositiveButton("确定", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();	//结束程序
				}
			})
			.setNegativeButton("取消", null);
		dialog = builder.create();
	}
	
	//当系统按键按下的时候触发事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){	//判断是否按下返回键
			dialog.show();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	//启动Dialog
	public void showDialog(View v) {
		dialog.show();
	}
	
	public void showDialogColor(View v) {
		dialogColor.show();
	}
	
	public void showDialogFontSize(View v) {
		dialogFontSize.show();
	}
}
