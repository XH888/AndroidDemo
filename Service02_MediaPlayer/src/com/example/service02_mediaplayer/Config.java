package com.example.service02_mediaplayer;

public class Config {
	//进度广播
	public static final String ACTION_PROG_BROADCAST="com.example.service02_mediaplayer.progress";
	
	//拖动广播,指定位置
	public static final String ACTION_SEEK_BROADCAST="com.example.service02_mediaplayer.seek";
	
	public static final String CURRENT_POSITION="current";
	public static final String MAX_LEN="max";
	
	
	//主线程通知service是否切换歌曲的常量
	public static final String EXTRA_CHANGED="changed";
	public static final String EXTRA_PATH="path";
}
