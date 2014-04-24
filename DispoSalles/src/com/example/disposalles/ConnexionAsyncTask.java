package com.example.disposalles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

public class ConnexionAsyncTask extends AsyncTask <String, String, Void>
{
	String ligne;
    public static String reponse="";

	protected Void doInBackground(String... params)
	{
		try
		{
			URL url = new URL(Salles.URL);
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
		return null;
	}
}
