package com.sanitaryresearch.twodoo.view;

//import com.sanitaryresearch.twodoo.R;

import java.util.ArrayList;
import java.util.List;

//import com.sanitaryresearch.pojo.ListTypeHolder;
import com.sanitaryresearch.pojo.PrefsHolder;
//import com.sanitaryresearch.twodoo.adapter.ListTypeSpinnerBaseAdapter;
//import java.util.Map;
//import java.util.prefs.Preferences;

//import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
//import android.content.res.TypedArray;
import android.os.Bundle;
//import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
//import android.widget.Spinner;

public class Prefs extends PreferenceActivity 
	implements OnSharedPreferenceChangeListener {
   // Option names and default values
	
	private static Boolean preferencesChanged;
   
	@Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.settings);
     setPreferencesChanged(false);
   }
  
   public PrefsHolder getEnabledPrefs(Context context) {
	   
	   setPreferencesChanged(false);
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
   
   @Override
   protected void onResume() {
       super.onResume();
       // Set up a listener we enter prefs menu
      
       getPreferenceScreen().getSharedPreferences()
               .registerOnSharedPreferenceChangeListener(this);
       //preferencesChanged = false;
      
   }

   @Override
   protected void onPause() {
       super.onPause();
       // Unregister the listener when we leave prefs menu
       
       getPreferenceScreen().getSharedPreferences()
               .unregisterOnSharedPreferenceChangeListener(this);
      
   }
	
   @Override
   public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
      // TODO Auto-generated method stub
     setPreferencesChanged(true);
   }

   public Boolean getPreferencesChanged() {
	  return preferencesChanged;
   }
	
   public void setPreferencesChanged(Boolean preferencesChanged) {
	  Prefs.preferencesChanged = preferencesChanged;
   }
}
