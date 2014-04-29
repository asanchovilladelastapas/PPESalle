package com.example.disposalles;

import java.util.List;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SalleAdapter extends BaseAdapter
{

	List<String> s;
	Salle sal;
	LayoutInflater mInflater;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return parent;
//        View view = convertView;
//        if (view == null) {
//            view = mInflater.inflate(R.layout.listitem, parent, false);
//        }
//
//        if (position % 2 == 0) {
//            view.setBackgroundResource(R.drawable.artists_list_backgroundcolor);
//        } else {
//            view.setBackgroundResource(R.drawable.artists_list_background_alternate);
//        }
//
//        ((TextView) view.findViewById(R.id.heading)).setText(data.get(position));
//
//        return view;
    }

	@Override
	public int getCount() 
	{
		return s.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return s.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

}
