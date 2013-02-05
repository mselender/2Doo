package com.sanitaryresearch.twodoo.database;

import static android.provider.BaseColumns._ID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class DooDBHelper extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "2Doo.db";
   private static final int DATABASE_VERSION = 1;
   private static final String LISTS_TABLE = "lists";
   private static final String ITEMS_TABLE = "items";
   private static final String LISTS_TABLE_CREATE_SQL =
		   "CREATE TABLE lists (id REAL PRIMARY KEY, " +
				   "name TEXT, type TEXT);";
				   
   private static final String ITEMS_TABLE_CREATE_SQL =
		   "CREATE TABLE items (list_id REAL NOT NULL, id REAL NOT NULL, " +
				   "description TEXT NOT NULL, " + 
				   "created DATE NOT NULL, completed DATEL, " +
				   "PRIMARY KEY (list_id, id), " +
				   "FOREIGN KEY (list_id) REFERENCES lists(id));";

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
 
   public double calcNewSortId(Integer listId, Integer itemId) {
	   double result = 0.0;
	   SQLiteDatabase db = null;
	   
	   String selectSql = "SELECT MAX(id) FROM items " + 
			   "WHERE list_id = ? AND id < ?;";
	   
	   try{
		   db = getReadableDatabase();
		   Cursor cursor = db.rawQuery(selectSql, new String[] {listId.toString(), itemId.toString()});
	       if (cursor != null) {
	    	   cursor.moveToFirst();
	    	
	       }
	       else {
	    	   result = 1.0;
	       }
		   
	   } catch (SQLiteException e) {
		   
	   } finally {
		  
	   }
	
	   db.close();
	   return result;
   }
   
}
