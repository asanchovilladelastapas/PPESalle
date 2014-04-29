package com.example.disposalles;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.os.Environment;
import android.util.Log;

public class Serialization
{
	//Ecrit les salles dans un fichier
	static String fileName = "DispoSalles/save.txt";
	static List<Salle> s;
	static File file = new File(Environment.getExternalStorageDirectory(), fileName);
	
	public static void write(List<Salle> salles)
	{
		try
		{	
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos); //Select where you wish to save the file...
		    oos.writeObject(salles); // write the class as an 'object'
		    oos.flush(); // flush the stream to insure all of the information was written to 'save.bin'
		    oos.close();// close the stream
		}
		
		catch(Exception ex)
		{
		Log.v("Address Book",ex.getMessage());
		ex.printStackTrace();
		}
    }
	
	@SuppressWarnings("unchecked")
	public static List<Salle> read()
	{
	    ObjectInputStream input;

        try
        {
        	input = new ObjectInputStream(new FileInputStream(file));
            s = (List<Salle>) input.readObject();
            input.close();
        }
        
        catch(IOException i)
        {
        	System.out.println("Impossible d'ouvrir le fichier");
        	i.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
			e.printStackTrace();
		}
		return s;
	}
}
