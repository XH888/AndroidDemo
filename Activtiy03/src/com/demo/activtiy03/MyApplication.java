package com.demo.activtiy03;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;

public class MyApplication extends Application {
	//通过application设置全局的变量；当系统启动时候创建。 所有的其中的 Activity 
	public Map<String, Object> appInfo=new HashMap<String, Object>();
}
