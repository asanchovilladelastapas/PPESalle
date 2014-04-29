package com.example.disposalles;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class RequeteSQL extends AsyncTask <Integer, String, String>{
	
	protected String doInBackground(Integer... params) {
		try
		{
			//Envoi d'une requête SQL a l'URL indiquée
			
			HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(ConnexionAsyncTask.SERVER_URL);
	        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	        postParameters.add(new BasicNameValuePair("id", params[0].toString()));
	        postParameters.add(new BasicNameValuePair("statut", params[1].toString()));

	        httppost.setEntity(new UrlEncodedFormEntity(postParameters));                   
	        httpclient.execute(httppost);
		}
	      
	      catch(Exception e)
		  {
			Log.e("Erreur SQL", e.toString());
		  }
	    return "";
	}

	@Override
	protected void onPostExecute(String result)
	{
		//Une fois requete executée, requete http pour actualiser statut des salles.
		ConnexionAsyncTask c = new ConnexionAsyncTask();
		c.execute();
	}
}