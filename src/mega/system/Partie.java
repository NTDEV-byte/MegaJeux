package mega.system;

import java.io.Serializable;
import java.util.Date;

import mega.utils.Utils;

public class Partie implements Serializable{
	
	public enum Jeu {CHESS,TICTACTOE};
	public enum Etat {GAGNEE, PERDUE, EGALITE, DEFAULT};

		private Joueur joueur1;
		private Joueur joueur2;
		private Date date;
		private Etat etat;
		private Jeu jeu;
		private boolean vainceurJ1;
		
		public Partie(Joueur j1, Joueur j2,Jeu jeu){
			this.joueur1 = j1;
			this.joueur2 = j2;
			this.jeu = jeu;
			this.date = new Date();
			this.etat = Etat.DEFAULT;//Etat.values()[Utils.RANDOM.nextInt(Etat.values().length - 1)];
		}
		
		public Etat getEtat() { 
			return etat;
		}
			
		public void setEtat(Etat e) { 
			etat = e;
		}
		public String toString() { 
			return "Joueur 1 : "+joueur1.getPseudonyme()+" vs Joueur 2 : "+joueur2.getPseudonyme()+//
					" Date: "+Utils.dateToString(null, date) + " Jeu : "+jeu.toString()+" Etat: "+etat.toString()+//
					" Vainceur: "+vainceurJ1;
		}
}
