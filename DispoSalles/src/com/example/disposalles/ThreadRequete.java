package com.example.disposalles;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class ThreadRequete extends AsyncTask <String, String, Void>
{

    private InputStream is;
	ManipulationFlux manipulation  = new ManipulationFlux();

	protected Void doInBackground(String... params)
	{
		try
		{
			//Requete http
		   	HttpClient httpclient = new DefaultHttpClient();
		   	HttpPost httppost = new HttpPost(params[0]);
		    HttpResponse response = httpclient.execute(httppost);
		    HttpEntity entity = response.getEntity();     
 
		    //Récupère le flux retourné par le script PHP
		    is = entity.getContent();

		    //Appelle la fonction de conversion en passant le flux en paramètre
		    manipulation.Conversion(is);
		 }
		
		catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
		return null;
	}
}
