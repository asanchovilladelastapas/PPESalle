package com.example.disposalles;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

public class Salles extends ActionBarActivity{
    Button boutonSalles;
    private String URL = "http://www.anthony-sanchez.com/projets/android/salles.php";
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salles);
		
		//Lors du clic recupère les salles
		boutonSalles = (Button) findViewById(R.id.boutonSalles);
		
		this.boutonSalles.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Execute les requetes sur un nouveau thread
				ThreadRequete t = new ThreadRequete();
				t.execute(URL);
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

}