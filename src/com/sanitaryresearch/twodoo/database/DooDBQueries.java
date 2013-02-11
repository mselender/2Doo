package com.sanitaryresearch.twodoo.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sanitaryresearch.pojo.ListHolder;
import com.sanitaryresearch.pojo.ItemHolder;
import com.sanitaryresearch.twodoo.view.R;

public class DooDBQueries {
	
	private DooDBHelper twoDoo;
	private final double idIncrement = 100.0;
	
	private List<String> listTypes;
	private TypedArray listTypeIcons; 
	private Context context;
	
	//res.obtainTypedArray(R.array.list_icons);
	//res.getStringArray(R.array.list_types);
	/*
	public DooDBQueries() {
		super();	
		listTypes = new ArrayList<String>();
	}
	*/
	public DooDBQueries(Context context) {
		super();
		this.context = context;
		Resources res = context.getResources();
		listTypes = Arrays.asList(res.getStringArray(R.array.list_types));
		listTypeIcons = res.obtainTypedArray(R.array.list_icons);
		twoDoo = new DooDBHelper(context);
	}
	
	public boolean insertNewList(String listName, String listType) {
		long now = System.currentTimeMillis();
		return insertNewList(listName, listType, 0, 0, now);
	}	   
	
	public boolean insertNewList(String listName, String listType, 
			long createdDate, long targetDate, long completedDate) {
		   
		double listId = getNewListId();
		boolean succeeded = false;
		SQLiteDatabase db = twoDoo.getWritableDatabase();
		if (db != null) {
			ContentValues values = new ContentValues();
			values.put("list_id", listId);
			values.put("name", listName);
			values.put("created", createdDate);
			values.put("target", targetDate);
			values.put("completed", completedDate);
			values.put("type", listType);   
			try {
				db.insertOrThrow(DooDBHelper.LISTS_TABLE, null, values);
				succeeded = true;
			}
			catch (Exception ex) {
				
			}
			finally {
				db.close();
			} 
		}
		return succeeded;
	  }
	
	public boolean insertNewItem(double listId, String description) {
		long now = System.currentTimeMillis();
		return insertNewItem(listId, description, 0, 0, now);	
	}
	
	public boolean insertNewItem(double listId, String description,
			long createdDate, long targetDate, long completedDate) {
		   
		boolean succeeded = false;
		double itemId = getNewItemId(listId);
		
		SQLiteDatabase db = twoDoo.getWritableDatabase();
		if (db != null) {
			ContentValues values = new ContentValues();
			values.put("list_id", listId);
			values.put("item_id", itemId);
			values.put("description", description);
			values.put("created", createdDate);
			values.put("target", targetDate);
			values.put("completed", completedDate);
			try {
				db.insertOrThrow(DooDBHelper.LISTS_TABLE, null, values);
				succeeded = true;
			}
			catch (Exception ex) {
				
			}
			finally {
				db.close();
			} 
		}
		return succeeded;
	  }

	  public double getNewListId() {
		  
		double newListId = idIncrement;
		String selectSql = "SELECT COALESCE(MAX(list_id), 0) FROM " + DooDBHelper.LISTS_TABLE + ";";
		
		SQLiteDatabase db = twoDoo.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.rawQuery(selectSql, null);
		    if (cursor != null) {
		    	   cursor.moveToFirst();
		    	   //newListId = Double.parseDouble(cursor.getString(0)) + idIncrement;
		    	   newListId = cursor.getFloat(0) + idIncrement;
		    }
		 db.close();
		}
		return newListId;
	  }
	    
	  public double getNewItemId(double listId) {
		double newItemId = idIncrement;
		String selectSql = "SELECT COALESCE(MAX(item_id), 0) FROM " + DooDBHelper.ITEMS_TABLE + " WHERE list_id = ?;";
		
		SQLiteDatabase db = twoDoo.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.rawQuery(selectSql, new String[] {(Double.valueOf(listId)).toString()});
		    if (cursor != null) {
		    	   cursor.moveToFirst();
		    	   newItemId = Double.parseDouble(cursor.getString(0)) + idIncrement;
		    }
		 db.close();
		}
		return newItemId;
	  }
	  
	  public List<ListHolder> getAllLists() {
		  
		  List<ListHolder> lists = new ArrayList<ListHolder>();
		  Cursor cursor = null;
		  SQLiteDatabase db = twoDoo.getReadableDatabase();
		  
		  if (db != null) {
			  cursor = db.query(DooDBHelper.LISTS_TABLE, DooDBHelper.LISTS_COLUMNS, null, null, null, null, "list_id ASC");
			  if (cursor != null) {
				  cursor.moveToFirst();
				  while (!cursor.isAfterLast()) {
				      ListHolder list = cursorToListHolder(cursor);
				      lists.add(list);
				      cursor.moveToNext();
				  }
			  }
			  db.close();
		  }
		  return lists;
	  }
	  
	  public List<ItemHolder> getAllItemsForList(double listId) {
		  
		  List<ItemHolder> items = new ArrayList<ItemHolder>();
		  Cursor cursor = null;
		  SQLiteDatabase db = twoDoo.getReadableDatabase();
		  
		  if (db != null) {
			  cursor = db.query(DooDBHelper.ITEMS_TABLE, DooDBHelper.LISTS_COLUMNS, "list_id = ?",  new String[] { String.valueOf(listId) }, null, null, "item_id ASC");
			  if (cursor != null) {
				  cursor.moveToFirst();
				  while (!cursor.isAfterLast()) {
				      ItemHolder item = cursorToItemHolder(cursor);
				      items.add(item);
				      cursor.moveToNext();
				  }
			  }
			  db.close();
		  }
		  return items;
	  }
		  
	  private ListHolder cursorToListHolder(Cursor cursor) {
		   ListHolder listHolder = new ListHolder();
		   listHolder.setListId(cursor.getDouble(0));
		   listHolder.setName(cursor.getString(1));
		   listHolder.setType(cursor.getString(2));
		   listHolder.setCreated(new Date(cursor.getInt(3)));
		   listHolder.setCreated(new Date(cursor.getInt(4)));
		   listHolder.setCreated(new Date(cursor.getInt(5)));
		   listHolder.setTypeImageId(listTypeIcons.getResourceId(listTypes.indexOf(listHolder.getType()), 0));
		   return listHolder;
	  }
	  
	  private ItemHolder cursorToItemHolder(Cursor cursor) {
		   ItemHolder itemHolder = new ItemHolder();
		   itemHolder.setListId(cursor.getDouble(0));
		   itemHolder.setItemId(cursor.getDouble(1));
		   itemHolder.setDescription(cursor.getString(2));
		   itemHolder.setCreated(new Date(cursor.getInt(3)));
		   itemHolder.setCreated(new Date(cursor.getInt(4)));
		   itemHolder.setCreated(new Date(cursor.getInt(5)));
		   return itemHolder;
	  }
	  
}
