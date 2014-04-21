package com.example.disposalles;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class Requete extends Thread 
{
	InputStream is;
    String URL = "http://10.0.2.2/salles.php";

	public void run() 
    {
    	try
    	{
	    	HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(URL);
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        
	        is = entity.getContent();
    	}

    	catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
  
}
}