package com.sanitaryresearch.twodoo.database;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class DooDBHelper extends SQLiteOpenHelper {
	
   //private SQLiteDatabase db;
   private static final String DATABASE_NAME = "2Doo.db";
   private static final int DATABASE_VERSION = 1;
   public static final String LISTS_TABLE = "lists";
   public static final String ITEMS_TABLE = "items";
   public static final String[] LISTS_COLUMNS = {"list_id", "name", "type", "created", "target", "completed",};
   public static final String[] ITEMS_COLUMNS = {"list_id", "item_id", "description", "created", "target", "completed",};
   
   
   
   private static final String LISTS_TABLE_CREATE_SQL =
		   "CREATE TABLE lists (list_id REAL PRIMARY KEY, " +
				   "name TEXT, type TEXT, " +
				   "created INTEGER NOT NULL, target INTEGER, completed INTEGER);";
				   
   private static final String ITEMS_TABLE_CREATE_SQL =
		   "CREATE TABLE items (list_id REAL NOT NULL, item_id REAL NOT NULL, " +
				   "description TEXT NOT NULL, " + 
				   "created INTEGER NOT NULL, target INTEGER, completed INTEGER, " +
				   "PRIMARY KEY (list_id, item_id), " +
				   "FOREIGN KEY (list_id) REFERENCES lists(list_id));";

   /** Create a helper object for the 2 Doo database */
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
	   //db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
	   //db.execSQL("DROP TABLE IF EXISTS " + LISTS_TABLE);
	   //onCreate(db);
	   //We'll use the ALTER TABLE statement for most upgrades
   }
   
}
