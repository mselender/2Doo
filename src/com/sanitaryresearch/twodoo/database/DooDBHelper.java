package com.sanitaryresearch.twodoo.database;

import static android.provider.BaseColumns._ID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DooDBHelper extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "2Doo.db";
   private static final int DATABASE_VERSION = 1;
   private static final String LISTS_TABLE = "lists";
   private static final String ITEMS_TABLE = "items";
   private static final String LISTS_TABLE_CREATE_SQL =
		   "CREATE TABLE lists (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				   "sort_key REAL, name TEXT, type TEXT);";
				   
   private static final String ITEMS_TABLE_CREATE_SQL =
		   "CREATE TABLE lists (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				   "list_key INTEGER, sort_key REAL, description TEXT, type TEXT);";

   /** Create a helper object for the Events database */
   public DooDBHelper(Context ctx) { 
      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) { 
      db.execSQL(LISTS_TABLE_CREATE_SQL);
      db.execSQL(ITEMS_TABLE_CREATE_SQL);
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
	   	db.execSQL("DROP TABLE IF EXISTS " + LISTS_TABLE);
      onCreate(db);
   }
}
