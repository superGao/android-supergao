package cn.itcast.musicplayer;

import com.example.supergao.FirstActivity;
import com.example.supergao.R;
import com.superGao.exitApp.ExitApplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	MusicControllerInterface mi;
	private static SeekBar sb;

	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bundle data = msg.getData();
			int duration = data.getInt("duration");
			int currentPosition = data.getInt("currentPosition");

			// 把进度设置给进度条
			sb.setMax(duration);
			sb.setProgress(currentPosition);
		}
	};
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);

		// 强制退出整个应用相关代码
		ExitApplication.getInstance().addActivity(this);

		sb = (SeekBar) findViewById(R.id.sb);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			// 手指抬起
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// 手指抬起时，获取进度条的进度
				mi.seekTo(seekBar.getProgress());
			}

			// 手指按下
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			// 手指滑动
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});

		Intent intent = new Intent(this, MusicService.class);

		// 为了让进程变成服务进程
		startService(intent);
		// 为了拿到中间人对象
		bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
		/**
		 * 补间动画 补间动画之动画组合(移动和旋转和透明结合)
		 * 
		 * @author gao
		 * 
		 */
		iv = (ImageView) findViewById(R.id.hair);
		// 图片的透明效果
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1);
		// 设置动画播放时长
		aa.setDuration(1000);
		// 重复次数
		aa.setRepeatCount(1000);
		// 重复播放模式
		aa.setRepeatMode(Animation.REVERSE);

		// 动画平移
		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
		// 动画持续时长
		ta.setDuration(4000);
		// 重复次数
		ta.setRepeatCount(200);
		// 重复播放模式
		ta.setRepeatMode(Animation.REVERSE);
		// 动画停留在结束时的位置
		ta.setFillAfter(true);

		AnimationSet set = new AnimationSet(false);
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
				0.3f);
		ra.setDuration(8000);
		ra.setRepeatCount(200);
		set.addAnimation(ra);
		set.addAnimation(ta);
		set.addAnimation(aa);
		set.setDuration(4000);
		set.setFillAfter(true);
		set.setRepeatCount(88);

		iv.startAnimation(set);
	}

	class MyConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mi = (MusicControllerInterface) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}

	public void play(View v) {
		mi.play();
	}

	public void pause(View v) {
		mi.pause();
	}

	public void continuePlay(View v) {
		mi.continuePlay();
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

}
