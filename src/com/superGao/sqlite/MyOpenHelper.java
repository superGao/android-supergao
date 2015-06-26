package com.superGao.sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	//arg1:数据库文件的名字 
	//arg2:游标工厂，游标其实就是结果集
	//arg3:数据库版本，最低是1，用于升级
	public MyOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	//数据库创建时，此方法调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		//执行sql语句
		db.execSQL("create table user(_id integer primary key autoincrement, name varchar(30), password varchar(30))");
	}

	//数据库升级时，此方法调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("数据库升级");
	}

}
