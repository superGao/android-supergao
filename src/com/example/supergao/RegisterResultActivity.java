package com.example.supergao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterResultActivity extends Activity {
	private Context mContext;
	private Button complete;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_result);
		
		mContext=this;
		findView();
		init();
		
		//获取用户注册的结果
		Intent ii=getIntent();
		Bundle bundle=ii.getExtras();
		String name=bundle.getString("username");
		//获取显示结果的组件
		TextView tv=(TextView)findViewById(R.id.tv);
		tv.setText(name);
		
	}
	
	private void findView(){
		complete=(Button) findViewById(R.id.register_success);
	}

	private void init(){
		complete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
}
