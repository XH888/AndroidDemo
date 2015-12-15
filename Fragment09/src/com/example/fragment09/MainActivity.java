package com.example.fragment09;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.fragment.EditDialogFragment;
import com.example.fragment.ExitDialogFragment;

public class MainActivity extends Activity implements OnClickListener{
	private EditDialogFragment df;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void showDialog(View view){
		df=(EditDialogFragment) getFragmentManager().findFragmentByTag("dialog");
		if(df==null){
			Bundle bundle=new Bundle();
			bundle.putString("msg", "正在显示DialogFragment消息");
			bundle.putString("title", "DialogFragment标题");
			bundle.putString("type", "1");
			df=EditDialogFragment.newInstance(bundle);
		}
		//只创建一个对话框Fragment对象
		df.show(getFragmentManager(), "dialog");
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(df.getArguments().getString("type").equals("1")){
			Toast.makeText(this, "点击对话框", 1).show();
		}else{
			finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			/*Bundle bundle=new Bundle();
			bundle.putString("msg", "确定退出吗");
			bundle.putString("title", "DialogFragment标题");
			bundle.putString("type", "2");
			df=EditDialogFragment.newInstance(bundle);
			df.show(getFragmentManager(), "exitdialog"); */
			
			ExitDialogFragment ef=new ExitDialogFragment();
			ef.show(getFragmentManager(), "exitdialog");
		}
		return false;
	}
}
