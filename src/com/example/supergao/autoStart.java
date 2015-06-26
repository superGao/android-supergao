package com.example.supergao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class autoStart extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//启动主界面
		Intent it = new Intent(context, WelcomeActivity.class);
		//创建一个新的任务栈去保存该Activity
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(it);		
	}

}
