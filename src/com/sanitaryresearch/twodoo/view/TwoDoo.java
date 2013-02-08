package com.sanitaryresearch.twodoo.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

//import com.sanitaryresearch.twodoo.R;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Context;
//import android.content.res.Resources;


public class TwoDoo extends Activity implements OnClickListener{
	
	List<String> enabledListTypeKeys;
	List<String> enabledListTypeNames;
	List<Integer> enabledListTypeIcons;
	//Context context;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.activity_two_doo);
		//getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.twodoo_launcher);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.string.twodoo_icon);
		this.setTitle(R.string.app_long_name);
		
		Context context = getApplicationContext();
	
		//Resources res = ctx.getResources();
		
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
	
		//addPreferencesFromResource(R.xml.settings);
				
		//TextView textViewDescription = (TextView)findViewById(R.id.description);
		//textViewDescription.setTextSize(getResources().getDimension(R.dimen.textsize));
		//textViewDescription.setTextSize(res.getDimension(R.dimen.textsize));
		
		
	    View aboutButton = findViewById(R.id.about_button);
	    aboutButton.setOnClickListener(this);
	    View newListButton = findViewById(R.id.new_list_button);
	    newListButton.setOnClickListener(this);
	  
	    /* set up spinner to have only enabled list types */
	    RefreshSpinner(context);
	   
	    /* set up text edit */
		EditText editText = (EditText) findViewById(R.id.new_list_input_text);
		editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
		
		
		
		
		/* listeners */
		/* not working yet */
		/*
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		OnSharedPreferenceChangeListener sharedPrefsListener = new SharedPreferences.OnSharedPreferenceChangeListener(){
		   public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		     // Implementation
			   Context context = getApplicationContext();
			   RefreshSpinner(context);
		   };
		 };
		
		prefs.registerOnSharedPreferenceChangeListener(sharedPrefsListener);
		*/
		
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
	    /*
	    Spinner listTypeSelector = (Spinner)findViewById(R.id.new_list_type_selector);
	    listTypeSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
	    	@Override
	    	public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	    		//String list_key = parentView.getItemAtPosition(position).toString();
	    		int icon_key = enabledListTypeIcons.get(position);
	    		Button newListButton = (Button) findViewById(R.id.new_list_button);
	    		newListButton.setCompoundDrawablesWithIntrinsicBounds(icon_key, 0, 0, 0); 
	        }
	    	 @Override
    	    public void onNothingSelected(AdapterView<?> parentView) {
    	        // your code here
    	    }
	    });
	    */
	}
	
	@Override
	   protected void onResume() {
	      super.onResume();
	     
	      /* until we have a listener working for preference changes sett up, we'll refresh spinner here */
	      Context context = getApplicationContext();
	      RefreshSpinner(context);
	    
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
	
	public void onClick(View v) {
	      switch (v.getId()) {
	      case R.id.about_button:
		         Intent about = new Intent(this, About.class);
		         startActivity(about);
		         break;
	      	case R.id.new_list_button:
	      		
		      	 Context context = getApplicationContext();
		      	 int duration = Toast.LENGTH_SHORT;
		      	
			     EditText editText = (EditText) findViewById(R.id.new_list_input_text);
			     String newListName = editText.getText().toString();
		    	 //Toast toast = Toast.makeText(context, newListName, duration);
		    	 //toast.show();
		    	 
		    	 Spinner spinner = (Spinner) findViewById(R.id.new_list_type_selector);
		    	 String listTypeName = spinner.getSelectedItem().toString();
		    	 Integer pos = spinner.getSelectedItemPosition();
		    	 //toast = Toast.makeText(context, listTypeName, duration);   	 
		    	 //toast.show();
		    	 
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
		
	    Prefs prefs = new Prefs();
	    PrefsHolder prefsHolder = prefs.getEnabledPrefs(context);
	    enabledListTypeKeys = prefsHolder.getTypeKeys();
		enabledListTypeNames = prefsHolder.getTypeNames();
		enabledListTypeIcons = prefsHolder.getTypeIcons();
	    Spinner spinner = (Spinner) findViewById(R.id.new_list_type_selector); 
	    /*
	    ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, enabledListTypeNames);
	    *./
	    /*
	    ListTypeSpinnerAdapter adapter = new ListTypeSpinnerAdapter(context, android.R.layout.simple_spinner_item, enabledListTypeNames, enabledListTypeIcons);  
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    */
	   
	    ArrayList<ListTypeHolder> objects = new ArrayList<ListTypeHolder>();
        for (int k = 0; k < enabledListTypeKeys.size(); k++) {
        	ListTypeHolder obj = new ListTypeHolder();
            obj.setAll(R.drawable.twodoo_launcher, enabledListTypeNames.get(k));
            objects.add(obj);
        }
 
        spinner.setAdapter(new ListTypeSpinnerAdapterA(this, objects));
		
		
	}
	


}



/*
Use this after your super.onCreate(savedInstanceState); call:

requestWindowFeature(Window.FEATURE_LEFT_ICON);
Then, set your contentView(R.layout.youLayout); and then use this:

getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, android.R.drawable.ic_dialog_alert);
 */
		
