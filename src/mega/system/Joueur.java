package mega.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mega.main.InterfaceJeu;
import mega.system.Partie.Etat;
import mega.utils.Utils;
import tictactoe.TicTacToe;

public class Joueur implements Serializable{

	
	
	public static final String progressionSavePath = "progressions/progression_joueurs#ID";
	public static final String partiesSauvegarderPathTTT = "parties/ttt/partiesSauvergarderJoueur#ID";
	public static final String partiesSauvegarderPathCHESS = "parties/chess/partiesSauvergarderJoueur#ID";
	private int id;
	private int totalPartiesSV = 0;
	private String pseudonyme;
	private String motDePasse;
	private Progression progression;
	private List<InterfaceJeu> partiesSauvegarders;  
	
		public Joueur(String pseudonyme,String motDePasse) { 
				 this.pseudonyme = pseudonyme;
				 this.motDePasse = motDePasse;
				 this.id = MegaJeuModel.TOTAL_JOUEURS;
				 MegaJeuModel.TOTAL_JOUEURS++;
				 progression = new Progression();
				 partiesSauvegarders = new ArrayList<InterfaceJeu>();
			}
		
		public void addNouvellePartie(Partie p) { 
			progression.ajoutePartie(p);
			Utils.serialize(progressionSavePath+id, progression);
			System.out.println("Progression du joueur: ID: "+id+" pseudonyme: "+pseudonyme+" est sauvegard� !");
		}
		
		public void enregisterStatPartieCourante() { 
			if(Utils.FileExists(progressionSavePath+id)) {
				Utils.serialize(progressionSavePath+id, progression);
			}
		}
		
		public void sauvegardePartieJeuEnCours(InterfaceJeu game) { 
			 	int temp = totalPartiesSV;
				clearListParties();
			 		if(partiesSauvegarders.size() == 0) { 
			 			partiesSauvegarders.add(game);
						totalPartiesSV++;
						System.out.println("TotalPartiesSV: "+totalPartiesSV);
			 		}
			 		else
			 			if(!partiesSauvegarders.contains(game)) { 
						partiesSauvegarders.add(game);
						totalPartiesSV++;
						System.out.println("TotalPartiesSV: "+totalPartiesSV);
						//System.out.println("Total après incrémentation " +TOTAL_PARTIES_SAUVEGARDER);
					}
					else {
						temp = temp - 1;
						System.out.println("temp: "+temp);
					//	System.out.println("total - 1 =  "+temp);
						partiesSauvegarders.set(temp , game);
					}
					if(game instanceof TicTacToe) {
						Utils.serialize(partiesSauvegarderPathTTT+id,partiesSauvegarders);
					}
					else {
						Utils.serialize(partiesSauvegarderPathCHESS+id,partiesSauvegarders);
					}
					//System.out.println("taille liste: "+partiesSauvegarders.size());
			}
		
		
		private void clearListParties() { 
			for(int i=0;i<partiesSauvegarders.size();i++) {
				 if(partiesSauvegarders.get(i) != null) { 
					 return;
				 }
			}
			partiesSauvegarders.clear();
		}
		
		public void sauvegardePartieJeuEnCours(InterfaceJeu game,int index) {
				clearListParties();
	 				if(partiesSauvegarders.size() >= index) { 
						partiesSauvegarders.set(index , game);
						if(game instanceof TicTacToe) {
							Utils.serialize(partiesSauvegarderPathTTT+id,partiesSauvegarders);
						  }
						else {
							Utils.serialize(partiesSauvegarderPathCHESS+id,partiesSauvegarders);
						}
					}
					else {
						System.err.println("Index Invalide la taille de la liste est de: "+partiesSauvegarders.size()+ "il doit être entre 0 - "+partiesSauvegarders.size());
					}
	 		}
		
		
		public void supprimePartieSauvegardee(int index) { 
			  if(partiesSauvegarders.size() >=  index) {
				  partiesSauvegarders.set(index, null);
				  totalPartiesSV = (totalPartiesSV <= 0) ? 0 : (totalPartiesSV = totalPartiesSV - 1);
				   System.out.println("total: "+totalPartiesSV);
				  Utils.serialize(partiesSauvegarderPathTTT+id,partiesSauvegarders);
			  	}
			 }
		
		public int supprimePartieSauvegardeeEtTerminee() { 
			for(int i=0;i<partiesSauvegarders.size();i++) {
				  if(partiesSauvegarders.get(i) instanceof TicTacToe) { 
					   TicTacToe partie = (TicTacToe)partiesSauvegarders.get(i);
					   if(partie.getIntegrateur().getEtatPartie() != Etat.DEFAULT) {
						   partiesSauvegarders.remove(i);
						   totalPartiesSV = (totalPartiesSV <= 0) ? 0 : (totalPartiesSV = totalPartiesSV - 1);
						   System.out.println("total: "+totalPartiesSV);
						   Utils.serialize(partiesSauvegarderPathTTT+id,partiesSauvegarders);
						   return i;
					   }
				  }
			}
			   Utils.serialize(partiesSauvegarderPathTTT+id,partiesSauvegarders);
			return -1;
		}
		
		public void chargePartiesJeuSauvegarder() { 
			if(Utils.FileExists(partiesSauvegarderPathTTT+id)) { 
				Object obj = (Utils.deserialize(partiesSauvegarderPathTTT+id));
				if(obj instanceof List) {
					partiesSauvegarders = (List<InterfaceJeu>)(obj);
				}
				for(int i=0;i<partiesSauvegarders.size();i++) {
					 TicTacToe x = (TicTacToe) partiesSauvegarders.get(i);
					 if(x != null) { 
						 System.out.println("PS"+i+" J1: "+x.getIntegrateur().getPseudoj1()+" J2: "+x.getIntegrateur().getPseudoj2());
					 }
				}
				
			}
			else {
					System.err.println("Aucune Partie Sauvergardé trouvé !");
				}
			}
		
			public void chargeProgressionJoueur() {
				if(Utils.FileExists(progressionSavePath+id)) {
					Object obj =  Utils.deserialize(progressionSavePath+id);
					progression = (Progression) obj;
				}
				else {
					System.err.println("Id: "+id+" Progression du joueur: "+pseudonyme+" est in�xistante !");
				}
				
				chargePartiesJeuSauvegarder();
			}
			
			public boolean canSave() { 
				return partiesSauvegarders.size() <= 3;
			}
			
			public int calculeScore() {
				 return progression.scoreCumule();
			}
			
			
			
			
			public Partie getPartieEncours() { 
				if(progression.getHistorique().size() > 0) { 
					return progression.getHistorique().get(progression.getHistorique().size() - 1);
				}
				else {
					System.err.println("Aucune Partie n'a �t� trouv� !");
					return null;
				}
			}
			
			public Partie getPartie(int index) { 
				return progression.getHistorique().get(index);
			}
			
			public String getPseudonyme() {
				return pseudonyme;
			}

			public void setPseudonyme(String pseudonyme) {
				this.pseudonyme = pseudonyme;
			}

			public String Progression() {
				return  progression.toString();
			}

			public String getMotDePasse() {
				return motDePasse;
			}

			public void setMotDePasse(String motDePasse) {
				this.motDePasse = motDePasse;
			}
	
			public String toString() { 
				return "ID: "+id+" pseudo: "+this.pseudonyme+" mot de passe: "+this.motDePasse+ " Score: "+calculeScore();
			}
			public Progression getProgression() {
				return progression;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public boolean fullSlotsSave() { 
				return partiesSauvegarders.size() == 3;
			}
			
			
			public List<InterfaceJeu> getPartiesSauvegarders() {
				return partiesSauvegarders;
			}


			public void setPartiesSauvegarders(List<InterfaceJeu> partiesSauvegarders) {
				this.partiesSauvegarders = partiesSauvegarders;
			}

			
}
