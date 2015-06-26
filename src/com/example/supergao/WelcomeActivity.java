package com.example.supergao;

/**
 * 定时页面跳转
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mContext = this;
		init();
		
	}

	private void init() {
		ImageView image=(ImageView)findViewById(R.id.iv_welcome);
		image.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//界面跳转到登录界面
				Intent intent = new Intent(mContext, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		},2000);
	}
		
}
