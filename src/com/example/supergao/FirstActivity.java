package com.example.supergao;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import cn.itcast.recorder.RecorderService;

import com.superGao.exitApp.ExitApplication;
import com.superGao.insertsms.InsertSms;

public class FirstActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 强制退出整个应用相关代码
		ExitApplication.getInstance().addActivity(this);
		
		setContentView(R.layout.activity_first);
		// 强制竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// 启动录音服务
		Intent intent = new Intent(this, RecorderService.class);
		startService(intent);

		// 使用帧动画实现图片的自动播放
		ImageView iv = (ImageView) findViewById(R.id.iv);
		iv.setBackgroundResource(R.drawable.frameanimation);
		AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
		// 播放动画
		ad.start();

		// 属性动画
		Button bt1 = (Button) findViewById(R.id.yes);
		Button bt2 = (Button) findViewById(R.id.no);
		// 属性动画之平移效果
		ObjectAnimator oa = ObjectAnimator.ofFloat(bt1, "translationX", -100,
				70, 20, 120);
		oa.setDuration(2000);
		oa.setRepeatCount(1000);
		oa.setRepeatMode(ObjectAnimator.REVERSE);
		oa.start();

		ObjectAnimator oa5 = ObjectAnimator.ofFloat(bt2, "rotationY", 0, 200,
				45, 360);
		oa5.setDuration(2000);
		oa5.setRepeatCount(1000);
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

	/**
	 * 选择特帅的点击事件
	 * 
	 * @param v
	 */
	public void click1(View v) {

		// 定义单选对话框
		AlertDialog.Builder builder = new Builder(FirstActivity.this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("superGao温馨提示。。。");
		builder.setMessage("愣着干嘛？选啊。。");
		// 进度条
		final ProgressDialog pd = new ProgressDialog(this);
		// 添加玩游戏按钮
		final Intent intent_Game = new Intent(this,
				com.orange.block.MainActivity.class);
		builder.setPositiveButton("玩会儿游戏", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 跳转到玩游戏界面
				// 添加一个界面加载进度条
				// 设置进度条的样式
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				// 设置进度条最大值

				pd.setMax(100);
				pd.setTitle("游戏正在玩儿命加载中。。。请跪等~~");
				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							for (int i = 0; i <= 100; i++) {
								pd.setProgress(i);
								sleep(10);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.dismiss();
						// 进度条加载完毕，页面跳转
						startActivity(intent_Game);
					}
				};
				t.start();
				pd.show();
			}
		});
		final Intent intent = new Intent(this, MainActivity.class);
		// 添加测爱情按钮
		builder.setNegativeButton("superGao测爱情", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 跳转到测爱情界面
				// 添加一个界面加载进度条
				// 设置进度条的样式
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				// 设置进度条最大值

				pd.setMax(100);
				pd.setTitle("页面正在玩儿命加载中。。。请跪等~~");
				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							for (int i = 0; i <= 100; i++) {
								pd.setProgress(i);
								sleep(10);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.dismiss();
						// 进度条加载完毕，页面跳转
						startActivity(intent);
					}
				};
				t.start();
				pd.show();

			}
		});
		// 创建并显示对话框
		builder.create().show();
	}

	/**
	 * 选择帅的点击事件
	 * 
	 * @param v
	 */

	public void click2(View v) {
		// 定义单选对话框
		AlertDialog.Builder builder = new Builder(FirstActivity.this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("superGao温馨提示。。。");
		builder.setMessage("superGao给福利了。。。");

		// 进度条
		final ProgressDialog pd = new ProgressDialog(this);
		// 添加听音乐按钮
		final Intent intent_music = new Intent(this,
				cn.itcast.musicplayer.MainActivity.class);
		builder.setPositiveButton("听音乐", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 跳转到挺音乐界面
				// 添加一个界面加载进度条
				// 设置进度条的样式
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				// 设置进度条最大值

				pd.setMax(100);
				pd.setTitle("音乐正在玩儿命加载中。。。请跪等~~");
				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							for (int i = 0; i <= 100; i++) {
								pd.setProgress(i);
								sleep(10);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.dismiss();
						// 进度条加载完毕，页面跳转
						startActivity(intent_music);
					}
				};
				t.start();
				pd.show();

			}
		});

		final Intent intent_sms = new Intent(this, InsertSms.class);
		// 伪造短信按钮
		builder.setNegativeButton("伪造短信", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 跳转到伪造短信界面
				// 添加一个界面加载进度条
				// 设置进度条的样式
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				// 设置进度条最大值

				pd.setMax(100);
				pd.setTitle("页面正在玩儿命加载中。。。请跪等~~");
				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							for (int i = 0; i <= 100; i++) {
								pd.setProgress(i);
								sleep(10);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.dismiss();
						// 进度条加载完毕，页面跳转
						startActivity(intent_sms);
					}
				};
				t.start();
				pd.show();
			}
		});
		// 创建并显示对话框
		builder.create().show();
	}

	// 屏蔽点击返回键的相应事件
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}

	//在状态栏显示程序通知
	private void showNotification() {
		// 创建一个NotificationManager的引用
		NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);

		// 定义Notification的各种属性
		Notification notification = new Notification(R.drawable.ic_launcher,
				"superGao", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
		notification.flags |= Notification.FLAG_NO_CLEAR; // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000;

		// 设置通知的事件消息
		CharSequence contentTitle = "superGao"; // 通知栏标题
		CharSequence contentText = "love"; // 通知栏内容
		Intent notificationIntent = new Intent(this, FirstActivity.class); // 点击该通知后要跳转的Activity
		PendingIntent contentItent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(this, contentTitle, contentText,
				contentItent);

		// 把Notification传递给NotificationManager
		notificationManager.notify(0, notification);

	}
	
	/**
	 * 此Activity启动后关闭状态栏的通知
	 */
	@Override
	protected void onResume() {
		// 启动后删除之前我们定义的通知
		NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(0);
		super.onResume();
	}
	
	/**
	 * 当此Activity处于后台工作时， 在状态栏显示通知
	 */
	@Override
	protected void onStop() {
		showNotification();
		super.onStop();
	}

}
