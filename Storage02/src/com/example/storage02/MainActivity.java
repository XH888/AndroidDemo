package com.example.storage02;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText nameId,contentId;
	private AlertDialog dialog;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contentId=(EditText) findViewById(R.id.contentId);
		nameId=(EditText) findViewById(R.id.nameId);
		
		adapter=new ArrayAdapter<String>(this, R.layout.item);
		dialog=new AlertDialog.Builder(this)
				.setTitle("请选择文件")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setAdapter(adapter, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						nameId.setText(adapter.getItem(which));
						openFile(null);
					}
				})
				.create();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.selectId){
			adapter.clear();
			adapter.addAll(fileList());
			dialog.show();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void openFile(View view) {
		String fileName=nameId.getText().toString().trim();	//文件名
		try {
			FileInputStream fis= openFileInput(fileName);
			byte[] b=new byte[fis.available()];
			fis.read(b);
			fis.close();
			contentId.setText(new String(b));
			Toast.makeText(getApplicationContext(), "open success", 0).show();
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFile(View view) {
		String fileName=nameId.getText().toString().trim();	//文件名
		String content=contentId.getText().toString().trim();//文件内容
		try {
			FileOutputStream fos=openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
			Toast.makeText(getApplicationContext(), "save success", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
