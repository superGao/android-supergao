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

public class RegisterInfoActivity extends Activity {
	private Context mContext;
	private Button btn_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_userinfo);
		mContext = this;
		findView();
		init();
		
		
	}

	private void findView() {
		btn_complete = (Button) findViewById(R.id.register_complete);
	}

	private void init() {
		btn_complete.setOnClickListener(completeOnClickListener);
	}
	
	private OnClickListener completeOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			//获取用户输入的注册信息
			EditText ename=(EditText)findViewById(R.id.name);
			String name=ename.getText().toString();
			EditText epassword=(EditText)findViewById(R.id.password);
			String password=epassword.getText().toString();
			
			//创建数据库
			MyOpenHelper oh=new MyOpenHelper(mContext, "user.db", null, 1);
			//如果数据库不存在，先创建后打开，如果数据库存在，直接打开
			SQLiteDatabase db= oh.getWritableDatabase();
			
			//向数据库中的user表添加用户数据
			db.execSQL("insert into user (name,password) values(?,?)",new Object[]{name,password});
			
			
			Intent intent = new Intent(mContext, RegisterResultActivity.class);
			
			//查询数据库中的用户名
			Cursor cursor = db.rawQuery("select name from user where password = ?", new String[]{password});
			//从游标中取出数据，方法与结果集类似
			String username=null;
			
			while(cursor.moveToNext()){
				//先通过列名获取列索引，再通过列索引获取内容
				 username = cursor.getString(cursor.getColumnIndex("name"));
			}
			//传递用户名到注册结果页面
			Bundle bundle = new Bundle();
			if(username!=null){
				bundle.putString("username", username);
			}else{
				bundle.putString("username", "注册失败");
			}
	    	
	    	intent.putExtras(bundle);
			startActivity(intent);
		}
	};

}
