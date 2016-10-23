package com.example.meto;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.UriMatcher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MementProvider extends ContentProvider {
        private   static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
        private   static final int MEMENTO=1;
        private   static final int MEMENTO1=2;
        //������������������ƥ��URI�ķ���ֵ
        MyDatabaseHelper dbHelper;
        SQLiteDatabase db;
        static {
        	matcher.addURI(Memento.AUTHORITY, "memento", MEMENTO);
        	matcher.addURI(Memento.AUTHORITY, "memento1/#", MEMENTO1);
        	//���URIƥ����������ж�URI������
        }
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {

		// TODO Auto-generated method stub
		switch(matcher.match(uri)){
		case MEMENTO:
			return"vnd.android.cursor.dir/memento";
		case MEMENTO1:
			return"vnd.android.cursor.item/memento1";
			default:
				throw new IllegalArgumentException("δ֪Uri:"+uri);
		}
	
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 * ��Ӽ�¼
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {

		// TODO Auto-generated method stub
		long rowId=db.insert("memento.db", Memento.Memento1._ID, values);
		//��Ӽ�¼
		if(rowId>0){
			Uri mementoUri=ContentUris.withAppendedId(uri, rowId);
			getContext().getContentResolver().notifyChange(mementoUri, null);
			return mementoUri;
			}
	
		return null;
	}

	@Override
	public boolean onCreate() {

		// TODO Auto-generated method stub
		dbHelper=new MyDatabaseHelper(getContext(),"memento.db",null,1);
		db=dbHelper.getReadableDatabase();
		//�������ݿ��࣬����ȡ���ݿ�ʵ��
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,

			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		switch(matcher.match(uri)){
		case MEMENTO:
			return db.query("memento.db", projection, selection, selectionArgs, null, null, sortOrder);
		case MEMENTO1:
			long id=ContentUris.parseId(uri);
			String where=Memento.Memento1._ID+"="+id;
			if(selection!=null&&!"".equals(selection)){
				where=where+"and"+selection;
			}
			return db.query("memento.db", projection, selection, selectionArgs, null, null, sortOrder);
			
			default:
				throw new IllegalArgumentException("δ֪URI:"+uri);
		}

	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 * ���¼�¼
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,

			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int num=0;
		switch(matcher.match(uri)){
		case MEMENTO:
			num=db.update("memento.db", values, selection, selectionArgs);
			break;
		case MEMENTO1:
			long id=ContentUris.parseId(uri);
			String where=Memento.Memento1._ID+"="+id;
			if(selection!=null&&!"".equals(selection)){
				where=where+"and"+selection;
			}
			num=db.update("memento.db", values, where, selectionArgs);
			break;
			default:
				throw new IllegalArgumentException("δ֪Uri:"+uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
       return num;
	}

}
