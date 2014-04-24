package com.example.disposalles;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class RequeteSQL extends AsyncTask <Integer, String, String>{

	Salles s = new Salles();
	//ProgressDialog progressDialog = new ProgressDialog(s.getApplicationContext());
	//protected void onPreExecute()
	//{
		//progressDialog=ProgressDialog.show(s.getApplicationContext(),"Please Wait..","Sending data to database",false);
	//}

	  protected String doInBackground(Integer... params) {

	      try
	      {	    	  
	          HttpClient httpclient = new DefaultHttpClient();
	          HttpPost httppost = new HttpPost(Salles.URL);
	          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	          postParameters.add(new BasicNameValuePair("id", params[0].toString()));
	          postParameters.add(new BasicNameValuePair("statut", params[1].toString()));

	          httppost.setEntity(new UrlEncodedFormEntity(postParameters));                   
	          HttpResponse response = httpclient.execute(httppost);
	          Log.i("postData", response.getStatusLine().toString());
	       }
	      
	      catch(Exception e)
		  {
			Log.e("Erreur SQL", e.toString());
		  }

	    return "";
	}

	  //protected void onPostExecute(String result)
	//{
	  //      progressDialog.dismiss(); 
	    //    Toast.makeText(s.getApplicationContext(), "Finished", Toast.LENGTH_LONG).show(); 
	//}
}
