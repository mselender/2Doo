package com.sanitaryresearch.twodoo;

import android.content.Context;
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
   
   public static boolean getEanabled(Context context, String key,  boolean defaultState) {
      return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(key, defaultState);
   }  
}
