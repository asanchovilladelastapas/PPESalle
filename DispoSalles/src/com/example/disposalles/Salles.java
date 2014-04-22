package com.example.disposalles;

import org.json.JSONArray;
import org.json.JSONException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Salles extends ActionBarActivity{
    Button boutonSalles;
    private String URL = "http://www.anthony-sanchez.com/projets/android/salles.php";
    public static String reponse = "";
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salles);
		
		//Lors du clic recup�re les salles (appuer deux fois)
		boutonSalles = (Button) findViewById(R.id.boutonSalles);
		
		this.boutonSalles.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Execute les requetes sur un nouveau thread
				ThreadRequete t = new ThreadRequete();
				t.execute(URL);
				//Affichage resultat pour v�rif
				System.out.println(reponse);		
				JSONParse(reponse);
			}
		});
				
	//Lancement de la requete http sur un nouveau thread
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
	        // Enable
	    } else {
	        // Disable
	    }
	}

	public void JSONParse(String laRep)
	{
		try
		{
			JSONArray jArray = new JSONArray(laRep);
			
			for (int i = 0; i < jArray.length(); i++) 
			{
				
			//TextView nomSalle = new TextView(this);
				
			//V�rifie qu'on rentre bien dans la boucle
			System.out.println("iteration"+i);
			
			//nomSalle = (TextView) findViewById(R.id.nomSalle+i);
			//nomSalle.setText(jArray.optString(i, "name"));
			}
		}
		catch (JSONException e)
		{
            Log.e("log_tag", e.toString());
		}
	}

}