package com.sanitaryresearch.twodoo.view;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

//import com.sanitaryresearch.twodoo.R;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Context;
//import android.content.res.Resources;


public class TwoDoo extends Activity implements OnClickListener{
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.activity_two_doo);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.twodoo_launcher);
		this.setTitle(R.string.app_long_name);
		
		//Context ctx = getApplicationContext();
		//Resources res = ctx.getResources();
		
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
				
		//TextView textViewDescription = (TextView)findViewById(R.id.description);
		//textViewDescription.setTextSize(getResources().getDimension(R.dimen.textsize));
		//textViewDescription.setTextSize(res.getDimension(R.dimen.textsize));
		
		
	    View aboutButton = findViewById(R.id.about_button);
	    aboutButton.setOnClickListener(this);
	    View newListButton = findViewById(R.id.new_list_button);
	    newListButton.setOnClickListener(this);
	    
		
	    Spinner spinner = (Spinner) findViewById(R.id.new_list_type_selector);
	    /*
	    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, NoCore_Array);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    */
	    /*
		EditText editText = (EditText) findViewById(R.id.new_list_input_text);
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
	         return true;
	      // More items go here (if any) ...
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

		    	 EditText editText = (EditText) findViewById(R.id.new_list_input_text);
		    	 CharSequence listName = editText.getText();
		    	 Spinner spinner = (Spinner) findViewById(R.id.new_list_type_selector);
		    	 String listType = spinner.getSelectedItem().toString();
		    	 Integer pos = spinner.getSelectedItemPosition();
		    	 //Intent newListMenu = new Intent(this, NewListMenu.class);
		    	 Intent newListMenu = new Intent(this, NewListMenu.class);
		         startActivity(newListMenu);
		         break;
			}
	   }
	


}

/*
Use this after your super.onCreate(savedInstanceState); call:

requestWindowFeature(Window.FEATURE_LEFT_ICON);
Then, set your contentView(R.layout.youLayout); and then use this:

getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, android.R.drawable.ic_dialog_alert);
 */
		
