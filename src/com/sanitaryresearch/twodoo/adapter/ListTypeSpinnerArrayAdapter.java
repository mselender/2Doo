package com.sanitaryresearch.twodoo.adapter;

import java.util.List;

import com.sanitaryresearch.twodoo.view.R;
//import com.sanitaryresearch.twodoo.view.R.id;
//import com.sanitaryresearch.twodoo.view.R.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListTypeSpinnerArrayAdapter extends ArrayAdapter<String>{
	
	List<String> listTypeNames;
	List<Integer> listTypeIcons;
	Context context;
		 
	public ListTypeSpinnerArrayAdapter(Context context, int textViewResourceId) {
          super(context, textViewResourceId);
          this.context = context;
    }
	
	public ListTypeSpinnerArrayAdapter(Context context, int textViewResourceId, List<String> listTypeNames, List<Integer> listTypeIcons) {
		super(context, textViewResourceId);
        this.context = context;
        this.listTypeNames = listTypeNames;
        this.listTypeIcons = listTypeIcons;
       
	}
 
	 public void initialize (List<String> listTypeNames, List<Integer> listTypeIcons) {
	    	this.listTypeNames = listTypeNames;
	    	this.listTypeIcons = listTypeIcons;
	}
	 
    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
 
    public View getCustomView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		//LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.list_types_spinner_item_layout, parent, false);
        TextView label = (TextView) item.findViewById(R.id.list_title);
        label.setText((CharSequence)listTypeNames.get(position));
 
        ImageView icon = (ImageView) item.findViewById(R.id.list_image);
        icon.setImageResource(listTypeIcons.get(position));   
        //ViewGroup.setImageResource(listTypeIcons.get(position));  
      
        return item;
     }

}