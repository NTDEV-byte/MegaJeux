package mega.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mega.system.Partie.Etat;
import mega.system.Partie.Jeu;

public class Progression implements Serializable{
	
	public static final int POINTS_GAGNE = 15;
	public static final int POINTS_EGALITE = 5;
	public static final int POINTS_PERDU = 0;
	
	
	private int score;
	private ArrayList<Partie> historique;
	private int nbG,nbE,nbP;
	private int totalPartiesSV;
	
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
	
	public List<Partie> troisDerniereParties(){ 
		if(historique.size() >= 3) {
			List<Partie> parties = new ArrayList<Partie>();
			for(int i=0;i<3;i++) { 
				parties.add(historique.get(historique.size() -1 - i));
			}
			return parties;
		}
		return null;
	}
	
	public List<Partie> troisDernierePartiesTTT(){ 
			List<Partie> parties = new ArrayList<Partie>();
			for(int i=0;i<historique.size();i++) { 
				if(historique.get(i).getJeu() == Jeu.TICTACTOE) { 
					if(historique.get(i).getEtat() != Etat.DEFAULT) {
						parties.add(historique.get(historique.size() -1 - i));
					}
				}
			}
			return parties;
	}
	
	public List<Partie> troisDernierePartiesChess(){ 
			List<Partie> parties = new ArrayList<Partie>();
			for(int i=0;i<historique.size();i++) { 
				if(historique.get(i).getJeu() == Jeu.CHESS) { 
					if(historique.get(i).getEtat() != Etat.DEFAULT) {
						parties.add(historique.get(historique.size() -1 - i));
					}
				}
		}		
			return parties;
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

	public int getTotalPartiesSV() {
		return totalPartiesSV;
	}

	public void setTotalPartiesSV(int totalPartiesSV) {
		this.totalPartiesSV = totalPartiesSV;
	}
	
	
}
