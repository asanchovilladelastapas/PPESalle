package com.example.disposalles;

import java.util.List;

public class Passerelle
{
	private static SalleActivity activity = null;
	
	public static void setActivity(SalleActivity activity)
	{
		//Démarre l'activity
		if (Passerelle.activity == null)
		{
			System.out.println("setActivity");
			Passerelle.activity = activity;
			ConnexionAsyncTask c = new ConnexionAsyncTask();	
			c.execute();
		}
	}
	
	public static List<Salle> getSalles()
	{
		//Lit les salles dans le fichier serializé
		return Serialization.read();
	}
	
	public static void write(List<Salle> salles)
	{
		//Ecrit la liste des salles dans le fichier serializé
		Serialization.write(salles);
	}
}
