package com.sanitaryresearch.twodoo.view;

//import com.sanitaryresearch.twodoo.R;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity {
   // Option names and default values
   
	@Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.settings);
     
   }
   /** Get the current value of the music option */
   
   public static boolean getEanabled(Context context, String key, Boolean defaultState) {
	  //Boolean defaultState = true;
	   SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	   Map<String, ?> m = sharedPrefs.getAll();
	   
	   
      return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultState);
   }  
   
   public static void getEnabledPrefs(Context ctx, String[] saTypeKeys, String[] saTypeNames) {
	   
	   //Context ctx = getApplicationContext();
	   Resources res = ctx.getResources();

	   /*(
	   TypedArray taTypeKeys = res.obtainTypedArray(R.);
	   List listTypeKeys = new ArrayList();
	   List listTypeNames = new ArrayList();
	   int n = taTypeKeys.length();
	   for (int i = 0; i < n; ++i) {
		  int id = taTypeKeys.getResourceId(i,0);
		  if (id > 0) {
			  
		  }
	   }
	   */
	   
   }

};
