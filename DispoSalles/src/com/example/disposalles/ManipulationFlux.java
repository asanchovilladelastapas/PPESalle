package com.example.disposalles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.widget.TextView;

public class ManipulationFlux 
{
	String nomSalle = "";
    private String result;
	
    //Convertit le flux de données en tableau JSON
	public void Conversion(InputStream retour)
    {
    	try
        {
        	InputStreamReader input = new InputStreamReader(retour,"iso-8859-1");
            BufferedReader reader = new BufferedReader(input);
            StringBuilder sb = new StringBuilder();
            
            String line = null;
            
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
				System.out.println("Hello");
            }
            
            retour.close();
            result=sb.toString();
        }
    	
    	catch(Exception e)
	    {
    		Log.e("log_tag", "Error converting result " + e.toString());
	    }
	    	
	    JSONParse(result);
    }
	
	
	public void JSONParse(String retourJSON)
	{
		try
		{
	        //Parse les données JSON
	        JSONArray jArray = new JSONArray(retourJSON);
	        for(int i=0;i<jArray.length();i++)
	        {
	            // Pour chaque salle, affichant le nom de la salle
	    		nomSalle += jArray.optString(i, "name");
	        	System.out.println(nomSalle);
	        }
		}

		catch(JSONException e)
		{
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
	}
}