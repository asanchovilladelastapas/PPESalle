package com.example.disposalles;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SalleList 
{
	static SalleActivity sA;
	
	public SalleList(SalleActivity salleActivity)
	{
		//Constructeur
		SalleList.sA = salleActivity;
	}

	public static void createList(final List<Salle> salles)
	{
		//Créé la liste des salles
		
		final ListView mListView = (ListView)sA.findViewById(R.id.listSalles);
		final ArraySallesAdapter adapter = new ArraySallesAdapter(sA.getApplicationContext(), salles);
		mListView.setAdapter(adapter);
		
		//Listener, click sur un item déclenche envoi requete SQL
		mListView.setOnItemClickListener(new OnItemClickListener() 
		{
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	        {       
	        	boolean stat = salles.get(position).getStatut();
	        	if(stat)
	        	{
	        		//Libère
	        		RequeteSQL r = new RequeteSQL();
	    	        r.execute(salles.get(position).getId(), 0);
	        	}
	        	else
	        	{
	        		//Occupe
	        		RequeteSQL r = new RequeteSQL();
	    	        r.execute(salles.get(position).getId(), 1);
	        	}
        	}
	    });
	}	
}