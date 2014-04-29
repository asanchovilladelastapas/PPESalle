package com.example.disposalles;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class SalleActivity extends ActionBarActivity {
    Button boutonSalles;
    public static String URL = "http://www.anthony-sanchez.com/projets/android/salles.php";
    
    Passerelle passerelle = new Passerelle();
    SalleList listeSalles = new SalleList(this);
    SalleActivity salAct;
    
    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		//Lance l'activity"
		Passerelle.setActivity(this);
	}

	public static void refresh()
    {
		//Recrée une liste actualisée
    	SalleList.createList(Passerelle.getSalles());
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.salles, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}