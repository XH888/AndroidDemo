package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class FirstReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String msg = intent.getStringExtra("msg");
		Log.i("info",msg+" @FirstReceiver");
		Bundle bundle=new Bundle();
		bundle.putString("msg", msg);

		//将 Bundle 设置给下个Receiver
		setResultExtras(bundle);
	}
}
