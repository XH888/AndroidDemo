package com.example.broadcastreceiver07;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void send(View v){
    	Intent intent = new Intent("com.example.receiver.BROADCASTRECEIVER");
        intent.putExtra("msg", "hello receiver");
        sendOrderedBroadcast(intent,"com.example.receiver.PERMISSION");
    }
}
