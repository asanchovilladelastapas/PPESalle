package com.example.disposalles;

public class Salle implements java.io.Serializable{
    
	private static final long serialVersionUID = 1L;
	String nom;
    int identifiant;
    boolean statut;

    public Salle(String id, String name, boolean status)
	{
		nom = name;
		identifiant = Integer.parseInt(id);
		statut = status;
	}
    
    public int getId()
    {
    	return identifiant;
    }
    
    public String getNom()
    {
    	return nom;
    }
    
    public boolean getStatut()
    {
    	return statut;
    }
    
    public void setStatut(boolean statut)
    {
    	this.statut = statut;
    }
}