package com.sanitaryresearch.twodoo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.sanitaryresearch.pojo.ListHolder;
//import com.sanitaryresearch.twodoo.database.DooDBQueries;
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
 
public class ListsBaseAdapter extends BaseAdapter {
 
    Context context;
    List<ListHolder> lists;
 
    public ListsBaseAdapter(Context context, List<ListHolder> lists) {
        super();
        this.context = context;
        this.lists = lists;
        /*
        DooDBQueries db = new DooDBQueries(context);
        lists = (ArrayList<ListHolder>)db.getAllLists();
        */
    }
 
    @Override
    public int getCount() {
        return lists.size();
    }
 
    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
 
        ListHolder currentItem = lists.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.lists_item, parent, false);
        TextView listName = (TextView) row.findViewById(R.id.list_name);
        listName.setText(currentItem.getName());
        ImageView listIcon = (ImageView) row.findViewById(R.id.list_image);
        listIcon.setImageResource(currentItem.getTypeImageId());
 
        return row;
    }
}