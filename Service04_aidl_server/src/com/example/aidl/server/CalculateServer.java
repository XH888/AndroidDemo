package com.example.aidl.server;

import com.example.aidl.CalculateInterface;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CalculateServer extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("info", "-- onBind --");
		return mBinder;
	}
	
	private final CalculateInterface.Stub mBinder = new CalculateInterface.Stub() {
		
		@Override
		public double doCalculate(double a, double b) throws RemoteException {
			return a+b;
		}
	};
}
