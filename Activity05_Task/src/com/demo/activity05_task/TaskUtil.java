package com.demo.activity05_task;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.util.Log;

public class TaskUtil {
	public static void printTask(Context context ){
		//系统服务组件 
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		//取得正在运行的任务；
		List<RunningTaskInfo> runningTasks = am.getRunningTasks(10);
		for(RunningTaskInfo rtask:runningTasks){
			Log.i("info", "id:"+rtask.id+
					",num:"+rtask.numActivities+	//堆栈中的组件个数
					",base:"+rtask.baseActivity.getClassName()+	//栈底组件
					",top:"+rtask.topActivity.getClassName());		//栈顶组件
		}
	}
}
