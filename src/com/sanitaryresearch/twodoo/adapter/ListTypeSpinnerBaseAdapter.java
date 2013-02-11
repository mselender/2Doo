package com.sanitaryresearch.twodoo.adapter;

import java.util.ArrayList;

import com.sanitaryresearch.pojo.ListTypeHolder;
import com.sanitaryresearch.twodoo.view.R;
//import com.sanitaryresearch.twodoo.view.R.id;
//import com.sanitaryresearch.twodoo.view.R.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListTypeSpinnerBaseAdapter extends BaseAdapter {
 
    Context context;
    ArrayList<ListTypeHolder> objects;
 
    public ListTypeSpinnerBaseAdapter(Context context, ArrayList<ListTypeHolder> objects) {
        super();
        this.context = context;
        this.objects = objects;
    }
 
    @Override
    public int getCount() {
        return objects.size();
    }
 
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
 
        ListTypeHolder currentItem = objects.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.list_types_spinner_item_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.list_title);
        label.setText(currentItem.getTitle());
        ImageView icon = (ImageView) row.findViewById(R.id.list_image);
        icon.setImageResource(currentItem.getImageId());
 
        return row;
    }
}