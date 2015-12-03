package com.example.alertdialog02;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Dialog customDialog;
	private TextView msgView;
	private View v;
	
	private EditText nameETid,telEVid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		msgView=(TextView) findViewById(R.id.msgViewId);
		initDialog();
	}

	private void initDialog() {
		v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_dialog, null);
		nameETid= (EditText) v.findViewById(R.id.nameETId);
		telEVid= (EditText) v.findViewById(R.id.telETId);
		AlertDialog.Builder builder=new Builder(this);
		builder.setTitle("设置你的信息")
			.setView(v)
			.setCancelable(false)
			.setPositiveButton("确定",new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					msgView.setText(nameETid.getText()+"\n"+telEVid.getText());
				}
			});
		customDialog=builder.create();
	}
	
	public void setMsg(View view){
		msgView.setText("");
		customDialog.show();
	}
}
