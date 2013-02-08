package com.sanitaryresearch.twodoo.view;

//import com.sanitaryresearch.twodoo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
//import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
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
   
   public static boolean getEnabled(Context context, String key, Boolean defaultState) {
	   return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key,defaultState);

   }  
   
   public PrefsHolder getEnabledPrefs(Context context) {
	   
	   Resources res = context.getResources();
	   List<String> enabledListTypeKeys = new ArrayList<String>();
	   List<String> enabledListTypeNames = new ArrayList<String>();
	   List<Integer> enabledListTypeIcons = new ArrayList<Integer>();
	   String[] allListTypeKeys = res.getStringArray(R.array.list_types);
	   String[] allListTypeTitles = res.getStringArray(R.array.list_titles);
	   //String[] allListTypeIcons = (res.getStringArray(R.array.list_icons));
	   TypedArray allListTypeIcons = res.obtainTypedArray(R.array.list_icons);
	   
	   SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	   //Map <String, ?> m = sharedPrefs.getAll(); 
	   
	   int n = allListTypeKeys.length;
	   for (int i = 0; i < n; i++) {
		  String listTypeKey = allListTypeKeys[i];  
		  if (sharedPrefs.getBoolean(listTypeKey, false)) {
			  enabledListTypeKeys.add(listTypeKey);
			  enabledListTypeNames.add(allListTypeTitles[i]);
			  enabledListTypeIcons.add(allListTypeIcons.getResourceId(i, -1));
		  } 
	   } 
	   PrefsHolder prefsHolder = new PrefsHolder(enabledListTypeKeys, enabledListTypeNames, enabledListTypeIcons);
	   return prefsHolder;
   }

}
