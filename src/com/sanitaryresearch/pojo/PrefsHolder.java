package com.sanitaryresearch.pojo;

import java.util.ArrayList;
import java.util.List;

public class PrefsHolder {
	
	  List<String> listTypeKeys; 
	  List<String> listTypeNames; 
	  List<Integer> listTypeIcons; 
	  
	  
	  public PrefsHolder () {
		  listTypeKeys = new ArrayList<String>();
		  listTypeNames = new ArrayList<String>();
		  listTypeIcons = new ArrayList<Integer>();
	  }
	  
	  public PrefsHolder(List<String> listTypeKeys,List<String> listTypeNames, List<Integer> listTypeIcons ) {
		  this.listTypeKeys = listTypeKeys;
		  this.listTypeNames = listTypeNames;
		  this.listTypeIcons = listTypeIcons;
	  }
	  
	  public void setTypeKeys(List<String> listTypeKeys) {
		  this.listTypeKeys = listTypeKeys;
	  }
	  
	  public List<String> getTypeKeys() {
		  return this.listTypeKeys;
	  }
	  
	  public void setTypeNames(List<String> listTypeNames) {
		  this.listTypeNames = listTypeNames;
	  }
	
	  public List<String> getTypeNames() {
		  return this.listTypeNames;
	  }
	  
	  public void setTypeIcons(List<Integer> listTypeIcons) {
		  this.listTypeIcons = listTypeIcons;
	  }
	
	  public List<Integer> getTypeIcons() {
		  return this.listTypeIcons;
	  }

}
