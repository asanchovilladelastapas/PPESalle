package com.example.disposalles;

import java.util.List;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArraySallesAdapter extends ArrayAdapter<Salle> {
	private List<Salle> s;
	LayoutInflater mInflater;
	View rowView;
	
	public ArraySallesAdapter(Context context, List<Salle> salle) 
	{
		  super(context, R.layout.row_list, salle);
		  this.s = salle;
		  mInflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View rowView = convertView;

		if(rowView == null)
		{
	        //création de l’objet View à partir de la ressource Layout
			rowView = mInflater.inflate(R.layout.row_list, null);
		}
	
	        //Récupération de la salle identifiée par sa position dans la liste
			Salle sal = s.get(position);
	
	        //Valorisation du TexteView de la Vue avec le nom de la salle
			TextView nameView = (TextView) rowView.findViewById(R.id.name);
			nameView.setText(sal.getNom());
			
			//Couleur de background en fonction statut
			if (sal.getStatut())
			{
				nameView.setBackgroundColor(Color.parseColor("#B40431"));
			}
			else
			{
				nameView.setBackgroundColor(Color.parseColor("#088A4B"));
			}
			return rowView;
	}
}
