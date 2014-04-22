package com.example.disposalles.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.disposalles.Salles;
import com.example.disposalles.ThreadRequete;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.util.Log;

public class httpTest extends ActivityUnitTestCase<Salles> {

	  public httpTest(Class<Salles> activityClass)
	  {
		super(activityClass);
	  }

	private Salles activity;
    private InputStream is;


	@Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	        ThreadRequete.class);
	    startActivity(intent, null, null);
	    activity = getActivity();
	  }

	public void testHttp()
	{
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
		   	HttpPost httppost = new HttpPost("http://www.anthony-sanchez.com/projets/android/salles.php");
		    HttpResponse response = httpclient.execute(httppost);
		    HttpEntity entity = response.getEntity();     
	    
		    assertTrue("HTTP OK", entity != null);
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void testConversion()
	{
		try
		{
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
       assertTrue("Retourne un resultat", result != null);
       
    }
    catch(Exception e)
    {
        Log.e("log_tag", "Error converting result " + e.toString());
    }
	}
}