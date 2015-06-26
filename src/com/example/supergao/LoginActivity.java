package com.example.supergao;

import com.superGao.sqlite.MyOpenHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 实现登录功能的Activity
 * @author gao
 *
 */
public class LoginActivity extends Activity {

	private Context mContext;
	private Button mLogin;
	private Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mContext = this;
		findView();
		init();

	}

	private void findView() {
		mLogin = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
	}

	private void init() {
		mLogin.setOnClickListener(loginOnClickListener);
		register.setOnClickListener(registerOnClickListener);
	}

	/**
	 * 登录的点击事件
	 */
	private OnClickListener loginOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 获取用户的登录信息
			EditText eusername = (EditText) findViewById(R.id.account);
			String username = eusername.getText().toString();

			EditText epassword = (EditText) findViewById(R.id.password);
			String password = epassword.getText().toString();

			// 根据用户输入的登录信息在数据库中查询是否存在
			MyOpenHelper op = new MyOpenHelper(mContext, "user.db", null, 1);
			SQLiteDatabase db = op.getWritableDatabase();

			Cursor cursor = db.rawQuery(
					"select * from user where name=? and password=?",
					new String[] { username, password });
			//从游标中取出数据，方法与结果集类似
			String loginname=null;
			
			while(cursor.moveToNext()){
				//先通过列名获取列索引，再通过列索引获取内容
				loginname = cursor.getString(cursor.getColumnIndex("name"));
			}
			if (loginname != null) {
				Intent intent = new Intent(mContext, FirstActivity.class);
				startActivity(intent);
				finish();
			} else {
				TextView ts = (TextView) findViewById(R.id.ts);
				ts.setText("用户名或者密码错误");
			}
		}
	};
	/**
	 * 注册的点击事件
	 */
	private OnClickListener registerOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(mContext, RegisterInfoActivity.class);
			startActivity(intent);

		}
	};

	// 屏蔽点击返回键的相应事件
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}
}
