package com.example.disposalles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Salles extends ActionBarActivity implements Runnable{

	//URL du script PHP
    private String URL = "http://www.anthony-sanchez.com/projets/android/salles.php";
    
    private InputStream is = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salles);
		
		//TextView nomSalle1 = new TextView(this);
		//TextView nomSalle2= new TextView(this);
    	//TextView nomSalle3 = new TextView(this);
    	//TextView nomSalle4 = new TextView(this);
    	//TextView nomSalle5 = new TextView(this);
    	//TextView nomSalle6 = new TextView(this);
    	//TextView nomSalle7 = new TextView(this);
    	//TextView nomSalle8 = new TextView(this);
    	//TextView nomSalle9 = new TextView(this);
    	//TextView nomSalle10 = new TextView(this);

		//nomSalle1 = (TextView) findViewById(R.id.nomSalle);
		//nomSalle.setText("Majorelle");
		
	//Lancement de la requete http sur un nouveau thread
	(new Thread(new Salles())).start();	
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.salles, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    
	    if (on) {
	        // Enable vibrate
	    } else {
	        // Disable vibrate
	    }
	}

	//Thread pour éxecution requete httppost
	@Override	
	public void run() {
		try
		{
			//Requete http
		   	HttpClient httpclient = new DefaultHttpClient();
		   	HttpPost httppost = new HttpPost(URL);
		    HttpResponse response = httpclient.execute(httppost);
		    HttpEntity entity = response.getEntity();     
		    
		    //Récupère le flux retourné par le script PHP
		    is = entity.getContent();

		    //Crée un String avec le resultat récupéré
		    InputStreamReader input = new InputStreamReader(is,"iso-8859-1");
	        BufferedReader reader = new BufferedReader(input);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        
	        while ((line = reader.readLine()) != null)
	        {
	            sb.append(line + "\n");
	        }
	        
	        is.close();
	        
	        String result=sb.toString();
	        
	        //Parse les données JSON
	        JSONArray jArray = new JSONArray(result);

	        for(int i=0;i<jArray.length();i++)
	        {
	            // Pour chaque salle, doit créer un Textview affichant son nom
	        	TextView nomSalle = new TextView(this);

	    		nomSalle = (TextView) findViewById(R.id.nomSalle+i);
	    		nomSalle.setText(jArray.optString(i, "name"));
	        }
		}

			catch(Exception e)
		    {
		        Log.e("log_tag", e.toString());
		    }
		}
	}