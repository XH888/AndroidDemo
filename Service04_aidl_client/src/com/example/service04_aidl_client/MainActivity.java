package com.example.service04_aidl_client;

import com.example.aidl.CalculateInterface;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText num1;
	private EditText num2;
	private TextView result;
	
	private CalculateInterface mService;
	
	private ServiceConnection sConnect = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("info", "-- onServiceDisconnected --");
			mService=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("info", "-- onServiceConnected --");
			//从服务取得service接口
			mService = CalculateInterface.Stub.asInterface(service);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =new Intent("com.example.aidl.server.CalculateServer");
        
        //绑定服务
       	bindService(intent, sConnect, BIND_AUTO_CREATE);
        
        num1=(EditText) findViewById(R.id.num1Id);
        num2=(EditText) findViewById(R.id.num2Id);
        result=(TextView) findViewById(R.id.resultId);
    }
    
    public void add(View v){
    	try {
    		//调用接口方法返回值给 result TextView
    		result.setText( String.valueOf(  mService.doCalculate(Double.valueOf(num1.getText().toString()),Double.valueOf(num2.getText().toString())) )) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
