package com.example.meto;

import android.net.Uri;
import android.provider.BaseColumns;

public class Memento {
        public  static final String AUTHORITY="com.example.provider.memento";
        public  static final class Memento1 implements BaseColumns{
        	public static final String _ID="_id";
        	public static final String SUBJECT="subject";
        	public static final String BOBY="boby";
        	public static final String DATE="date";
        	//memento_tb±íÖÐ_id,subject,boby,date×Ö¶Î
        	public static final Uri MEMENTO_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/memento");
        	public static final Uri MEMENTO1_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/memento1");
        	
        }
}
