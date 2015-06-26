package com.example.supergao;

import com.superGao.exitApp.ExitApplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ResultActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		// 强制退出整个应用相关代码
		ExitApplication.getInstance().addActivity(this);

		// 强制竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// 获得启动该Activity的意图对象
		Intent itent = getIntent();
		// 获取接收到的数据
		Bundle bundle = itent.getExtras();
		String man = bundle.getString("man");
		String woman = bundle.getString("woman");

		String text = man + woman;
		byte[] b = text.getBytes();
		int total = -100;
		for (byte c : b) {
			total += c;
		}
		// 取绝对值
		total = Math.abs(total) % 101;

		TextView tv = (TextView) findViewById(R.id.tv);

		if (total < 30) {
			tv.setText(man
					+ "和"
					+ woman
					+ "爱情指数为："
					+ total
					+ "点。      有缘无分，趁早分了吧。。。>_<"
					+ "   开玩笑呢。。。不过，你们的感情有待加强哦，未来如此美好，没有必要对彼此抱有猜疑的心态哦，毕竟爱情需要磨合嘛");
		}

		if (total >= 30 && total < 60) {
			tv.setText(man + "和" + woman + "爱情指数为：" + total
					+ "点。      还行，凑合过吧。。。^_^"
					+ "   其实生活不能凑合就行了，爱情更是如此，多给对方一点惊喜，多一份关怀，你们的爱情会更加多彩哦。。");
		}

		if (total >= 60 && total < 80) {
			tv.setText(man + "和" + woman + "爱情指数为：" + total
					+ "点。     superGao祝福你们哦。。。^o^"
					+ "   爱情维持到这个地步不错哦，祝你们白头到老，子孙成群。。。哈哈（前提是不怕被罚款）");
		}
		if (total >= 80) {
			tv.setText(man + "和" + woman + "爱情指数为：" + total
					+ "点。       百年修得同船渡，千年修得共枕眠，你俩可以去宾馆看电视了。。"
					+ "不过。。。必要的安全措施不要忘了哦（要生孩儿的除外。。）");
		}

		if ("高永健".equals(man)) {
			tv.setText(man + "和" + woman
					+ "爱情指数为100++！！！        爱情指数爆表，superGao十八块钱请你们拿证去。。");
		}

		ImageView iv = (ImageView) findViewById(R.id.image);
		// 界面图片的翻转效果
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
				0.3f);

		ra.setDuration(8000);
		ra.setRepeatCount(200);
		ra.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(ra);

		// 设置按钮的动画效果
		Button bb = (Button) findViewById(R.id.bb);
		ObjectAnimator oa5 = ObjectAnimator.ofFloat(bb, "rotationY", 0, 200,
				45, 360);
		oa5.setDuration(4000);
		oa5.setRepeatCount(1000);
		oa5.setRepeatMode(ObjectAnimator.REVERSE);
		// 开始播放属性动画
		oa5.start();
	}

	// 处理再测一次事件
	public void click(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * 处理直接打电话的事件
	 */
	public void call(View v) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:15802982964"));
		// 启动新的Activity，也就是Activity的跳转
		startActivity(intent);
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
