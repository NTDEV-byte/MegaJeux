package com.mega.system;

import java.util.ArrayList;

public class Progression {
	public static final int POINT_GAGNE = 50;
	public static final int POINT_EGALITE = 25;
	public static final int POINT_PERDU = 0;
	
	
	
	private int score;
	//jeu
	private ArrayList<Partie> historique;
	private int nbGagne;
	private int nbEgalite;
	private int nbPerdu;
	
	public Progression() {
		
	}
	
	public int scoreCumule() {
		return (nbGagne * POINT_GAGNE + nbEgalite * POINT_EGALITE);
		
	}
	
	
}
