package mega.system;

import java.io.Serializable;
import java.util.ArrayList;

import mega.system.Partie.Etat;

public class Progression implements Serializable{
	
	public static final int POINTS_GAGNE = 50;
	public static final int POINTS_EGALITE = 25;
	public static final int POINTS_PERDU = 0;
	
	
	private int score;
	private ArrayList<Partie> historique;
	private int nbG,nbE,nbP;
	
	
	public Progression() {
		historique = new ArrayList<Partie>();	
	}

	public void ajoutePartie(Partie p) { 
		historique.add(p);
	}
	
	public void supprimePartie(Partie p) { 
		historique.remove(p);
	}
	
	public String afficheHistorique() {
		String result = "";
		int index = 1;
		for(Partie p : historique) {
			 result+="Partie: "+index+" "+p.toString()+"\n";
			 index++;
		}
		return result;
	}
	
	
	public int scoreCumule() {
		int tNBG=0,tNBE=0,tNBP=0;
		for(Partie p : historique) { 
			if(p.getEtat() == Etat.GAGNEE){
				tNBG++;
			}
			else if(p.getEtat() == Etat.EGALITE) {
				tNBE++;
			}
			else if(p.getEtat() == Etat.PERDUE) { 
				tNBP++;
			}
		}
		
		nbG = tNBG;
		nbE = tNBE;
		nbP = tNBP;
		score = (nbG * POINTS_GAGNE + nbE * POINTS_EGALITE);
		return (nbG * POINTS_GAGNE + nbE * POINTS_EGALITE);
	}
	
	public ArrayList<Partie> getHistorique() {
		return historique;
	}

	public void setHistorique(ArrayList<Partie> historique) {
		this.historique = historique;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbG() {
		return nbG;
	}

	public void setNbG(int nbG) {
		this.nbG = nbG;
	}

	public int getNbE() {
		return nbE;
	}

	public void setNbE(int nbE) {
		this.nbE = nbE;
	}

	public int getNbP() {
		return nbP;
	}

	public void setNbP(int nbP) {
		this.nbP = nbP;
	}
	
	
}
