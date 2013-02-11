package com.sanitaryresearch.twodoo.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
//import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ListView;
import android.widget.Toast;

import com.sanitaryresearch.pojo.ListHolder;
import com.sanitaryresearch.pojo.ListTypeHolder;
import com.sanitaryresearch.pojo.PrefsHolder;
import com.sanitaryresearch.twodoo.adapter.ListTypeSpinnerBaseAdapter;
import com.sanitaryresearch.twodoo.adapter.ListsBaseAdapter;
import com.sanitaryresearch.twodoo.database.DooDBQueries;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
//import android.graphics.drawable.Drawable;
//import android.view.KeyEvent;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.TextView.OnEditorActionListener;

//import com.sanitaryresearch.twodoo.R;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Context;
//import android.content.res.Resources;


public class TwoDoo extends Activity 
	 implements OnClickListener, OnLongClickListener,
		OnItemClickListener, OnItemLongClickListener { 
	
	/*Context */
	Context context;
	Resources resources;
	/*Components */
	View aboutButton;
	View newListButton;
	EditText editText;
	Spinner spinner;
	ListView listView;
	/* preferences */
	SharedPreferences preferences;
	Prefs prefs;
	int selectedItem = -1;
	protected Object mActionMode;
	/* Adapters */
	/* Listeners */
	/* lists */
	
	//Context context;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.activity_two_doo);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.string.twodoo_icon);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		this.setTitle(R.string.app_long_name);
		
		context = getApplicationContext();
		//resources = ctx.getResources();
		
		/* initialize preferences */
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
		prefs = new Prefs();
		
		/* get view components */
		AccessComponents();
		
		/* listeners */
	    AccessListeners();
		
		/* get preferences */
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		/*Adapters*/
	    AccessAdapters();
	  
	    /* set up spinner to have only enabled list types */
	    RefreshSpinner(context);
	    
	    /*Retrieve list of lists */
	    RefreshLists(context);
	    
	}
	
	@Override
	   protected void onResume() {
	      super.onResume();
	     
	      /* until we have a listener working for preference changes sett up, we'll refresh spinner here */
	      //prefs = new Prefs();
	      if (prefs.getPreferencesChanged()) {
	    	  	context = getApplicationContext();
	      	RefreshSpinner(context);
	      	prefs.setPreferencesChanged(false);
	      }
	   }

	   @Override
	   protected void onPause() {
	      super.onPause();
	      
	   }


	protected Object getItemAtPosition(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      super.onCreateOptionsMenu(menu);
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.activity_two_doo, menu);
	      return true;
	   }
	
	 @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	      switch (item.getItemId()) {
	      case R.id.settings:
	         startActivity(new Intent(this, Prefs.class));
	         Context context = getApplicationContext();
	         RefreshSpinner(context);
	         return true;
	      }
	      return false;
	   }
	 
	 private void AccessComponents() {
		 	 		 
		 editText = (EditText) findViewById(R.id.new_list_input_text);
		 editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
		 spinner = (Spinner) findViewById(R.id.new_list_type_selector);
		 newListButton = findViewById(R.id.new_list_button);
		 //newListButton.setOnClickListener(this);
		 listView = (ListView)findViewById(R.id.lists_view);
		 aboutButton = findViewById(R.id.about_button);
		 //aboutButton.setOnClickListener(this);
		 
	 }
	 /* we want the main view to catch the change when it resumes - 
	    will a listener in main activity work in delayed fashion? */
	 private void AccessListeners() {
		
		 newListButton.setOnClickListener(this);
		 aboutButton.setOnClickListener(this);
		 //listView.setOnClickListener(this);
		 //listView.setOnLongClickListener(this);
		 
		 /* not needed */
		 /*
		OnSharedPreferenceChangeListener sharedPrefsListener = new SharedPreferences.OnSharedPreferenceChangeListener(){
		   public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String key) {
		     // Implementation
			   context = getApplicationContext();
			   RefreshSpinner(context);
		   };
		};
		
		preferences.registerOnSharedPreferenceChangeListener(sharedPrefsListener);
		*/
		/* not needed */
		/*
		editText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_SEND) {
					//sendMessage();
					handled = true;
				}
				return handled;
			}
		});
		*/
	
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					selectedItem = position;
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				/*
				if (mActionMode != null) {
					return false;
				}
				selectedItem = position;
				mActionMode = TwoDoo.this.startActivity(Intent intent);
				*/
				Intent listLongClickMenu = new Intent(context, ListLongClickMenu.class);
		        startActivity(listLongClickMenu);
		        view.setSelected(true);
				return true;	 //so it compile for now
			}
		});
	 }
	 
	 private void AccessAdapters() {
		 
		
	 }
	
	 public void onClick(View v) {
	      switch (v.getId()) {
	      	case R.id.about_button:
		         Intent about = new Intent(this, About.class);
		         startActivity(about);
		         break;
	      	case R.id.new_list_button:
	      		
		      	 context = getApplicationContext();
		      	 //int duration = Toast.LENGTH_SHORT;
		      	
			     editText = (EditText) findViewById(R.id.new_list_input_text);
			     String listName = editText.getText().toString();
			    	 //Toast toast = Toast.makeText(context, newListName, duration);
			    	 //toast.show();
			    	 int selectedPosition = spinner.getSelectedItemPosition();
			    	 ListTypeHolder listTypeHolder = (ListTypeHolder)spinner.getAdapter().getItem(selectedPosition);
			    	 String listType = listTypeHolder.getKey();
			    	 //listType = enabledListTypeKeys.get(selectedPosition);
			    	 //toast = Toast.makeText(context, listTypeName, duration);   	 
			    	 //toast.show();
			    	 
			    	 DooDBQueries db = new DooDBQueries(context);
			    	 boolean succeeded = db.insertNewList(listName, listType);
			    	 if (succeeded) {
			    		 RefreshLists(context);
			    		 editText.setText("");
			    	 }
			    	 
			    	 
			    	 //Intent newListMenu = new Intent(this, NewListMenu.class);
			    	 /*
			    	 Intent newListMenu = new Intent(this, NewListMenu.class);
			         startActivity(newListMenu);
			         break;
			         */
			}
	}
	 
	
	private void RefreshSpinner(Context context) {
		
	    /* set up spinner to have only enabled list types */
		List<String> enabledListTypeKeys;
		List<String> enabledListTypeNames;
		List<Integer> enabledListTypeIcons;
	    //prefs = new Prefs();
	    PrefsHolder prefsHolder = prefs.getEnabledPrefs(context);
	    enabledListTypeKeys = prefsHolder.getTypeKeys();
		enabledListTypeNames = prefsHolder.getTypeNames();
		enabledListTypeIcons = prefsHolder.getTypeIcons();
	    spinner = (Spinner) findViewById(R.id.new_list_type_selector); 
	   
	    ArrayList<ListTypeHolder> objects = new ArrayList<ListTypeHolder>();
        for (int k = 0; k < enabledListTypeKeys.size(); k++) {
        	ListTypeHolder obj = new ListTypeHolder();
            obj.setAll(enabledListTypeIcons.get(k), enabledListTypeNames.get(k), enabledListTypeKeys.get(k));
            objects.add(obj);
        }
    
        /*
	    ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, enabledListTypeNames);
	    */
	    /*
	    ListTypeSpinnerArrayAdapter adapter = new ListTypeSpinnerArrayAdapter(context, android.R.layout.simple_spinner_item, enabledListTypeNames, enabledListTypeIcons);  
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    */
        
        spinner.setAdapter(new ListTypeSpinnerBaseAdapter(this, objects));	
        spinner.setSelection(0);
		
	}
	
	public void RefreshLists(Context context) {
		
		 	DooDBQueries db = new DooDBQueries(context);
	        List<ListHolder> lists = (List<ListHolder>)db.getAllLists();
	        ListView listView = (ListView)findViewById(R.id.lists_view); 
	        listView.setAdapter(new ListsBaseAdapter(this, lists));
	 
	}
	/*
	private void ResizeNewListButton() {
		
  		ViewGroup.LayoutParams spinnerParams = spinner.getLayoutParams();
  		ViewGroup.LayoutParams buttonParams = newListButton.getLayoutParams();
  	    buttonParams.height = spinnerParams.height;
  	    newListButton.setLayoutParams(buttonParams); 
  	    newListButton.refreshDrawableState();
	}
	*/
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long aid) {
		// TODO Auto-generated method stub
		
	}
	
}





/*
Use this after your super.onCreate(savedInstanceState); call:

requestWindowFeature(Window.FEATURE_LEFT_ICON);
Then, set your contentView(R.layout.youLayout); and then use this:

getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, android.R.drawable.ic_dialog_alert);
 */
		

/*
listTypeSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
	@Override
	public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		//String list_key = parentView.getItemAtPosition(position).toString();
		int icon_key = enabledListTypeIcons.get(position);
		newListButton.setCompoundDrawablesWithIntrinsicBounds(icon_key, 0, 0, 0); 
    }
	 @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }
});
*/
