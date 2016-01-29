package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ThirdReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String msg = getResultExtras(true).getString("msg");
		Log.i("info",msg+" @ThirdReceiver");
		Bundle bundle=new Bundle();
		bundle.putString("msg", msg);
	}

}
