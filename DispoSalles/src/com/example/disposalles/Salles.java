package com.example.disposalles;

import java.lang.reflect.Array;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Salles extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener{
    Button boutonSalles;
    public static String URL = "http://www.anthony-sanchez.com/projets/android/salles.php";
    String nom = "";
    String identifiant = "";
    String statut = "";
    OnCheckedChangeListener toggleListener;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salles);

	    TableLayout tl = (TableLayout) findViewById(R.id.relay);

	    TableRow buttonRow = new TableRow(this);
	    
		//Lors du clic recupère les salles
		boutonSalles = new Button(this);
		boutonSalles.setText("Récupérer les salles");
		boutonSalles.setVisibility(View.VISIBLE);
		
		
		buttonRow.addView(boutonSalles);
		buttonRow.setGravity(Gravity.CENTER_HORIZONTAL);
		
		TableLayout buttonTableLayout = new TableLayout(this);
  	    buttonTableLayout.addView(buttonRow);
		
		tl.addView(buttonTableLayout);
		
		//Execute les requetes sur un nouveau thread
		ConnexionAsyncTask c = new ConnexionAsyncTask();
		c.execute();
		
		this.boutonSalles.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Affichage resultat pour vérif
				System.out.println(ConnexionAsyncTask.reponse);		
				JSONParse(ConnexionAsyncTask.reponse);
				boutonSalles.setVisibility(View.GONE);
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

	public void JSONParse(String laRep)
	{
		try
		{
			JSONArray jArray = new JSONArray(laRep);
			//layout pour stocker les textView dynamiques
		    TableLayout ll = (TableLayout) findViewById(R.id.relay);

			for (int i = 0; i < jArray.length(); i++) 
			{	

			    TableRow contentRow = new TableRow(this);
			    
			    // margin
			    contentRow.setPadding(0,0,0,0);

				//Création d'un TextView pour chaque salle
				 TextView textView1 = new TextView(this);
	
				 JSONObject json_obj = jArray.getJSONObject(i);
				 nom = json_obj.getString("nom");
				 identifiant = json_obj.getString("id");
				 statut = json_obj.getString("statut");
				 
				 textView1.setGravity(Gravity.LEFT);
				 textView1.setId(i+1);
				 textView1.setText(nom);
	             textView1.setTextSize(17); //taille du texte
	             textView1.setPadding(10, 3, 80, 3);
	             int idText = textView1.getId();
	
	             contentRow.addView(textView1); // Attache le TextView au layout parent
	             
	             
	          // the title tablelayout
	             //Création d'un togglebutton pour chaque salle
	             ToggleButton toggle = new ToggleButton(this);
	             toggle.setMinimumWidth(0);
	             toggle.setWidth(150);
	             toggle.setHeight(90);
	             
	             if(statut.equals("0"))
	             {
	            	 toggle.setText("Libre");
	             }
	             else
	             {
	            	 toggle.setChecked(true);
	            	 toggle.setText("Occupée");
	             }
	             toggle.setTextOn("Occupée");
	             toggle.setTextOff("Libre");
	             toggle.setId(i+1);
	             toggle.setOnCheckedChangeListener(this);   
	             
	             //Left, top, right, bottom
	
	     	    contentRow.setPadding(10,10,10,10);
	
	     	    contentRow.addView(toggle);
	     	    contentRow.setGravity(Gravity.RIGHT);
	     	    
	     	   TableLayout contentTableLayout = new TableLayout(this);
	     	   contentTableLayout.addView(contentRow);
	
	     	    ll.addView(contentTableLayout);
	     }
	}
		catch (JSONException e)
		{
            Log.e("Erreur JSON", e.toString());
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
	    Toast.makeText(this, "Boutton n°" + buttonView.getId() + " " + isChecked, Toast.LENGTH_SHORT).show();
		
	    if (isChecked)
		{
	        RequeteSQL r = new RequeteSQL();
	        r.execute(buttonView.getId(), 1);
        }
		else
		{
			RequeteSQL r = new RequeteSQL();
	        r.execute(buttonView.getId(), 0);
        }			
	}
}