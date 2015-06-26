package com.superGao.insertsms;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.supergao.FirstActivity;
import com.example.supergao.R;
import com.superGao.exitApp.ExitApplication;

public class InsertSms extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mms);

		// 强制退出整个应用相关代码
		ExitApplication.getInstance().addActivity(this);

		// 补间动画之动画组合(移动和旋转结合)
		ImageView iv = (ImageView) findViewById(R.id.sms_iv);
		// 动画平移
		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
		// 动画持续时长
		ta.setDuration(4000);
		// 重复次数
		ta.setRepeatCount(1000);
		// 重复播放模式
		ta.setRepeatMode(Animation.REVERSE);
		// 动画停留在结束时的位置
		ta.setFillAfter(true);

		AnimationSet set = new AnimationSet(false);
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				1.0f);
		ra.setDuration(8000);
		ra.setRepeatCount(1000);
		set.addAnimation(ra);
		set.addAnimation(ta);
		set.setDuration(4000);
		set.setFillAfter(true);
		set.setRepeatCount(88);

		iv.startAnimation(set);
	}

	/**
	 * 添加菜单功能
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 处理菜单的选择功能
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 获取当前的菜单id
		int itemid = item.getItemId();
		switch (itemid) {
		case R.id.item1:
			// 选择了主页，显式跳转到主页
			Intent intent = new Intent(this, FirstActivity.class);
			startActivity(intent);
			break;

		case R.id.item2:
			// 选择了退出，退出整个应用程序
			//android.os.Process.killProcess(android.os.Process.myPid());
			// java.lang.System.exit(0);
			ExitApplication.exit();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void click(View v) {
		// 通过内容提供者插入短信至数据库
		final ContentResolver resolver = getContentResolver();
		final ContentValues values = new ContentValues();
		final String phone;
		final String sms;
		EditText e_phone = (EditText) findViewById(R.id.phone);
		phone = e_phone.getText().toString();

		EditText e_sms = (EditText) findViewById(R.id.sms);
		sms = e_sms.getText().toString();

		Thread t = new Thread() {
			@Override
			public void run() {
				values.put("address", phone);
				values.put("date", System.currentTimeMillis());
				values.put("type", 1);
				values.put("body", sms);
				resolver.insert(Uri.parse("content://sms"), values);

			}
		};
		t.start();
		Toast.makeText(this, "短信插入成功", 1).show();
	}
}
