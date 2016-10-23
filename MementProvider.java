package com.example.meto;

import android.content.ContentProvider;
import android.content.UriMatcher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MementProvider extends ContentProvider {
        private   static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
        private   static final int MEMENTO=1;
        private   static final int MEMENTO1=2;
        //定义两个常量，用于匹配URI的返回值
        MyDatabaseHelper dbHelper;
        SQLiteDatabase db;
        static {
        	matcher.addURI(Memento.AUTHORITY, "memento", MEMENTO);
        	matcher.addURI(Memento.AUTHORITY, "memento1/#", MEMENTO1);
        	//添加URI匹配规则，用于判断URI的类型
        }
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {

		// TODO Auto-generated method stub
		dbHelper=new MyDatabaseHelper(getContext(),"memento.db",null,1);
		db=dbHelper.getReadableDatabase();
		//创建数据库类，并获取数据库实例
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,

			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,

			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
