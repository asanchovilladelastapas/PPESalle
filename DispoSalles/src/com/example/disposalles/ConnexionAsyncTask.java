package com.example.disposalles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ConnexionAsyncTask extends AsyncTask <String, String, Void>
{
    public static String SERVER_URL = "http://www.anthony-sanchez.com/projets/android/salles.php";

	String ligne;
	String nom = "";
    String identifiant = "";
    boolean statut;
    public static String reponse="";
    SalleActivity activity;
    
	protected Void doInBackground(String... params)
	{
		try
		{			
			//Requet http récupère infos salles au format JSON
				URL url = new URL(SERVER_URL);
				URLConnection  conn = (HttpURLConnection) url.openConnection();
				
				conn.setDoOutput(true);
		
				//Recuperation des informations
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((ligne = reader.readLine()) != null) 
				{
					reponse = ligne.trim()+"\n";
				}
			}
			catch(Exception e)
		    {
				Log.e("log_tag", e.toString());
		    }
			//Appel fonction JSON pour parcourir le	tableau d'informations
			JSONParse(reponse);
		return null;
	}
	
	public List<Salle> JSONParse(String laRep)
	{
		List<Salle> salles = new ArrayList<Salle>();
		try
		{
			//Parcourt le tableau et ajoute une salle dans la liste a chaque itération.
			JSONArray jArray = new JSONArray(laRep);

			for (int i = 0; i < jArray.length(); i++) 
			{	
				JSONObject json_obj = jArray.getJSONObject(i);
				 
				nom = json_obj.getString("nom");
				identifiant = json_obj.getString("id");
				
				if(json_obj.getString("statut").equals("1"))
				{
					statut = true;
				}
				else
				{
					statut = false;
				}
				 salles.add(new Salle(identifiant, nom, statut));
			}
		}
		
		catch (JSONException e)
		{
            Log.e("Erreur JSON", e.toString());
		}
		System.out.println("Execution requete http");
		Passerelle.write(salles);
		return salles;
	}

	@Override
	protected void onPostExecute(Void salles) 
	{
		//Rafraichit la liste a la fin de l'execution
		SalleActivity.refresh();
	}
}