package com.example.service01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private Intent myServiceIntent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myServiceIntent=new Intent(getApplicationContext(),MyService.class);
    }
 
    public void startService(View v){
    	myServiceIntent.putExtra("msg","msg - "+System.currentTimeMillis());
    	startService(myServiceIntent);
    }
    
    public void stopService(View v){
    	stopService(myServiceIntent);
    }
}
