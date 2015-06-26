package com.example.supergao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LockScreenReceiver extends BroadcastReceiver {

	private static final String TAG = "superGao";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			Log.e(TAG, "ACTION_SCREEN_OFF");
			// 隐式启动软件首页
			Intent intent1 = new Intent("android.intent.action.MAIN");
			// Intent intent1 = new Intent(context, LockScreenActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent1);
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			Log.e(TAG, "ACTION_SCREEN_ON");
		} else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Log.e(TAG, "ACTION_BOOT_COMPLETED");
		}

	}

}
