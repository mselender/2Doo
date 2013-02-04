package com.sanitaryresearch.twodoo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.preference.PreferenceManager;


public class TwoDoo extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two_doo);
		
		//Context ctx = getApplicationContext();
		//Resources res = ctx.getResources();
		
		this.setTitle(R.string.app_long_name);
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
				
		//TextView textViewDescription = (TextView)findViewById(R.id.description);
		//textViewDescription.setTextSize(getResources().getDimension(R.dimen.textsize));
		//textViewDescription.setTextSize(res.getDimension(R.dimen.textsize));
		
		View newListButton = findViewById(R.id.new_list_button);
	    newListButton.setOnClickListener(this);
	    View aboutButton = findViewById(R.id.about_button);
	    aboutButton.setOnClickListener(this);
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
	      case R.id.new_list_button:
	    	  Intent newListMenu = new Intent(this, NewListMenu.class);
	         startActivity(newListMenu);
	         break;
	      case R.id.about_button:
	         Intent about = new Intent(this, About.class);
	         startActivity(about);
	         break;
	      }
	   }

}
