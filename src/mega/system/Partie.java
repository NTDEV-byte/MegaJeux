package mega.system;

import java.io.Serializable;
import java.util.Date;

import mega.utils.Utils;

public class Partie implements Serializable{
	
	public enum Jeu {CHESS,TICTACTOE};
	public enum Etat {GAGNEE, PERDUE, EGALITE, DEFAULT,TERMINEE};

		private String joueur1;
		private String joueur2;
		private Date date;
		private Etat etat;
		private Jeu jeu;

		
		public Partie(String joueur1, String joueur2,Jeu jeu){
			this.joueur1 = joueur1;
			this.joueur2 = joueur2;
			this.jeu = jeu;
			this.date = new Date();
			this.etat = Etat.DEFAULT;//Etat.values()[Utils.RANDOM.nextInt(Etat.values().length - 1)];
		}
		
	
		
		public String statPartie() {
			return Utils.dateToString(null, date) + " Versus " +joueur2+ " " + etat.toString()+" score "+getScorePartie(); 
		}
		
		
		private int getScorePartie() { 
			if(etat == Etat.GAGNEE) return 15;
			if(etat == Etat.EGALITE) return 5;
			return 0;
		}

		
		public String toString() { 
			return "Joueur 1 : "+joueur1+" vs Joueur 2 : "+joueur2+//
					" Date: "+Utils.dateToString(null, date) + " Jeu : "+jeu.toString()+" Etat: "+etat.toString();//
		}
		
		public Etat getEtat() { 
			return etat;
		}
			
		public void setEtat(Etat e) { 
			etat = e;
		}
		
		

		public Jeu getJeu() {
			return jeu;
		}

		public void setJeu(Jeu jeu) {
			this.jeu = jeu;
		}
}
