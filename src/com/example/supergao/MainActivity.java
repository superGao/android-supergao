package com.example.supergao;

import com.superGao.exitApp.ExitApplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 强制退出整个应用相关代码
		ExitApplication.getInstance().addActivity(this);
		// 强制竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// 补间动画之图片旋转
		ImageView iv = (ImageView) findViewById(R.id.image);
		RotateAnimation ra = new RotateAnimation(0, 180);

		ra.setDuration(1500);
		ra.setRepeatCount(1000);
		ra.setRepeatMode(Animation.REVERSE);

		iv.startAnimation(ra);
		// 按钮的动画效果
		Button bb = (Button) findViewById(R.id.bb);
		ObjectAnimator oa5 = ObjectAnimator.ofFloat(bb, "rotationY", 0, 200,
				45, 360);
		oa5.setDuration(3000);
		oa5.setRepeatCount(1000);
		oa5.setRepeatMode(ObjectAnimator.RESTART);
		// 开始播放属性动画
		oa5.start();

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
			ExitApplication.exit();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void click(View v) {
		// 获取用户输入的数据
		EditText e_man = (EditText) findViewById(R.id.man);
		EditText e_woman = (EditText) findViewById(R.id.woman);
		String man = e_man.getText().toString();
		String woman = e_woman.getText().toString();

		// 封装数据
		Bundle bundle = new Bundle();
		bundle.putString("man", man);
		bundle.putString("woman", woman);

		// 创建显式意图
		Intent intent = new Intent(this, ResultActivity.class);
		// 将bundle放入intent中
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
