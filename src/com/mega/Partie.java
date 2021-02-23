package com.mega;

import java.util.ArrayList;
import java.util.Date;

public class Partie {
		
	public enum Etat {GAGNEE, PERDUE, NULLE, LANCEE};

		private Joueur joueur1;
		private Joueur joueur2;
		//jeu
		private Date date;
		Etat etat;
		boolean estGagneParJoueur1;
		
		public Partie(Joueur j1, Joueur j2){
			this.joueur1 = j1;
			this.joueur2 = j2;
			this.date = new Date();
			this.etat = Etat.LANCEE;
		}
				
}
