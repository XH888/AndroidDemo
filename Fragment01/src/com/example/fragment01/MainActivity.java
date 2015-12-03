package com.example.fragment01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText nameText;
	private Button clickBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nameText=(EditText) findViewById(R.id.nameId);
		clickBtn=(Button) findViewById(R.id.btnId);
		
		clickBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle(nameText.getText());
			}
		});
	}
}
