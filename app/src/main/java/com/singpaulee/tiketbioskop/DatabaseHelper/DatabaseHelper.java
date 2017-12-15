package com.singpaulee.tiketbioskop.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 15/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "bioskop.db";
	public static final int DATABASE_VERSION = 4;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String q = "CREATE TABLE user(id integer primary key AUTOINCREMENT,firstname text not null, lastname text not null, nickname text not null, email text not null, password text not null, telp text not null);";
		sqLiteDatabase.execSQL(q);

		String q2 = "CREATE TABLE customer(id integer primary key AUTOINCREMENT, title_movie text not null, name text not null, quantity integer not null, phone text not null);";
		sqLiteDatabase.execSQL(q2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
