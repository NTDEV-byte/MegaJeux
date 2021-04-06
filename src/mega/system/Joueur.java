package mega.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mega.jeux.chess.Chess;
import mega.jeux.tictactoe.TicTacToe;
import mega.system.Partie.Etat;
import mega.system.Partie.Jeu;
import mega.utils.Utils;

public class Joueur implements Serializable{

	public static final String progressionSavePath = "progressions/progression_joueurs#ID";
	public static final String partiesSauvegardersPath = "parties/partiesSauvergarderJoueur#ID";
	private int id;
	private String pseudonyme;
	private String motDePasse;
	private Progression progression;
	private List<InterfaceJeu> partiesSauvegarders;  
	
		public Joueur(String pseudonyme,String motDePasse) { 
				 this.pseudonyme = pseudonyme;
				 this.motDePasse = motDePasse;
				 this.id = MegaJeuBD.TOTAL_JOUEURS;
				 MegaJeuBD.TOTAL_JOUEURS++;
				 progression = new Progression();
				 partiesSauvegarders = new ArrayList<InterfaceJeu>();
			}
		
		public void addNouvellePartie(Partie p) { 
			progression.ajoutePartie(p);
			//System.out.println("Partie: "+p.toString());
			Utils.serialize(progressionSavePath+id, progression);
			//System.out.println("Progression du joueur: ID: "+id+" pseudonyme: "+pseudonyme+" est sauvegard� !");
		}
		
		public void enregistreProgression() { 
			if(Utils.FileExists(progressionSavePath+id)) {
				Utils.serialize(progressionSavePath+id, progression);
			}
		}
		
		public void enregistreParties() { 
			if(Utils.FileExists(partiesSauvegardersPath+id)) {
				Utils.serialize(partiesSauvegardersPath+id,partiesSauvegarders);
			}
		}
		
		public void forceSaveAll() { 
			Utils.serialize(progressionSavePath+id, progression);
			Utils.serialize(partiesSauvegardersPath+id,partiesSauvegarders);
		}
		
		
		public void sauvegardePartieJeuEnCours(InterfaceJeu game) { 
			 	int temp = progression.getTotalPartiesSV();
			 	if(contains(game) && temp > 0) { 
			 		temp = temp - 1;
				    System.out.println("Temp: "+temp);
					partiesSauvegarders.set(temp , game);
				}
			 	else { 
						partiesSauvegarders.add(game);
						progression.setTotalPartiesSV(progression.getTotalPartiesSV() + 1);
						//System.out.println("TotalPartiesSV: "+progression.getTotalPartiesSV());
					}
			 		enregistreParties();
			 		enregistreProgression();
				}
		
		
		public void supprimeAutoPartiesTerminee() { 
			List<Chess> chessParties = getPartiesSauvegarderChess();
			List<TicTacToe> ttt = getPartiesSauvegarderTTT();
			
			System.out.println("TTT: "+ttt.toString());
			for(int i=0;i<chessParties.size();i++) {
				 if(chessParties.get(i).getIntegrateur().getEtatPartie() != Etat.DEFAULT) {
					  		Chess partieASupprimer = chessParties.get(i);
					  		partiesSauvegarders.remove(partieASupprimer);
					  		progression.setTotalPartiesSV(progression.getTotalPartiesSV() - 1);
					  		if(partiesSauvegarders.size() == 0) partiesSauvegarders.clear();
					  		enregistreProgression();
							enregistreParties();
				 }
			}
			for(int i=0;i<ttt.size();i++) {
				 if(ttt.get(i).getIntegrateur().getEtatPartie() != Etat.DEFAULT) {
					  		TicTacToe partieASupprimer = ttt.get(i);
					  		partiesSauvegarders.remove(partieASupprimer);
					  		progression.setTotalPartiesSV(progression.getTotalPartiesSV() - 1);
							if(partiesSauvegarders.size() == 0) partiesSauvegarders.clear();
					  		enregistreProgression();
							enregistreParties();
				 }
				 
			}
			//System.out.println("Size Parties: "+partiesSauvegarders.size());
		}
		
		
		private boolean contains(InterfaceJeu j) { 
			boolean present = false;
			if(j instanceof TicTacToe) {
				for(int i=0;i<partiesSauvegarders.size();i++) { 
					if(partiesSauvegarders.get(i) instanceof TicTacToe) {
						TicTacToe saved = (TicTacToe)partiesSauvegarders.get(i);
						TicTacToe x = (TicTacToe)j;
					//	System.out.println("Saved: "+saved.getId()+" InstanceR: "+x.getId());
						if(saved.getId() == x.getId()) { 
							
							present = true;
						}
					}
			   }
			
			}
			else 
				if(j instanceof Chess){
					for(int i=0;i<partiesSauvegarders.size();i++) { 
						if(partiesSauvegarders.get(i) instanceof Chess) {
						Chess saved = (Chess)partiesSauvegarders.get(i);
						Chess x = (Chess)j;
						if(saved.getId() == x.getId()) { 
							present = true;
						}
						}
				   }
			}
			
			return present;
		}
		
		public void chargePartiesJeuSauvegarder() { 
			if(Utils.FileExists(partiesSauvegardersPath+id)) { 
				Object obj = (Utils.deserialize(partiesSauvegardersPath+id));
				if(obj instanceof List) {
					partiesSauvegarders = (List<InterfaceJeu>)(obj);
				}
			}
			else {
					//System.err.println("Aucune Partie Sauvergardé trouvé !");
				}
			}
		
			public void chargeProgressionJoueur() {
				if(Utils.FileExists(progressionSavePath+id)) {
					Object obj =  Utils.deserialize(progressionSavePath+id);
					progression = (Progression) obj;
				}
				else {
					//System.err.println("Id: "+id+" Progression du joueur: "+pseudonyme+" est in�xistante !");
				}
				chargePartiesJeuSauvegarder();
			}
			
			public void supprimePartie(Jeu jeu_selectionner,int index) { 
				  if(jeu_selectionner == Jeu.TICTACTOE) { 
					  	List<TicTacToe> partiesTTT = getPartiesSauvegarderTTT();
					  	if(index < partiesTTT.size() && (!partiesTTT.isEmpty())) {
					  		TicTacToe partie = partiesTTT.get(index);
					  		if(partiesSauvegarders.contains(partie)) {
					  			partiesSauvegarders.remove(partie);
					  			progression.setTotalPartiesSV(progression.getTotalPartiesSV() - 1);
					  			if(progression.getTotalPartiesSV() < 0) progression.setTotalPartiesSV(0);
					  			Utils.serialize(partiesSauvegardersPath+id,partiesSauvegarders);
					  		}
					  		
					  	}
				  	}
				  else 
				   if(jeu_selectionner == Jeu.CHESS){
						List<Chess> partiesChess = getPartiesSauvegarderChess();
					  	if (index < partiesChess.size() && (!partiesChess.isEmpty())) {
					  		Chess partie = partiesChess.get(index);
					  		if(partiesSauvegarders.contains(partie)) {
					  			partiesSauvegarders.remove(partie);
					  			progression.setTotalPartiesSV(progression.getTotalPartiesSV() - 1);
					  			if(progression.getTotalPartiesSV() < 0) progression.setTotalPartiesSV(0);
					  			Utils.serialize(partiesSauvegardersPath+id,partiesSauvegarders);
					  		}
					  	}
				  }
			}
			
			public List<TicTacToe> getPartiesSauvegarderTTT(){ 
				List<TicTacToe> tttParties = new ArrayList<TicTacToe>();
				for(int i=0;i<partiesSauvegarders.size();i++) { 
					   if(partiesSauvegarders.get(i) instanceof TicTacToe) { 
						   		tttParties.add((TicTacToe) partiesSauvegarders.get(i));
					   }
				}
				return tttParties;
			}
			
			public List<Chess> getPartiesSauvegarderChess(){ 
				List<Chess> chessParties = new ArrayList<Chess>();
				for(int i=0;i<partiesSauvegarders.size();i++) { 
					   if(partiesSauvegarders.get(i) instanceof Chess) { 
						   chessParties.add((Chess) partiesSauvegarders.get(i));
					   }
				}
				return chessParties;
			}
			
			public boolean canSaveChess() { 
				return getPartiesSauvegarderChess().size() <= 3;
			}
			
			public boolean canSaveTicTacToe() { 
				return getPartiesSauvegarderTTT().size() <= 3;
			}
			
			public int calculeScore() {
				 return progression.scoreCumule();
			}
			
			
			public Partie getPartieEncours() { 
				if(progression.getHistorique().size() > 0) { 
					return progression.getHistorique().get(progression.getHistorique().size() - 1);
				}
				else {
					//System.err.println("Aucune Partie n'a �t� trouv� !");
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
				return partiesSauvegarders.size() == 6;
			}
			
			public boolean isEmpty() { 
				return partiesSauvegarders.size() == 0;
			}
			
			public List<InterfaceJeu> getPartiesSauvegarders() {
				return partiesSauvegarders;
			}


			public void setPartiesSauvegarders(List<InterfaceJeu> partiesSauvegarders) {
				this.partiesSauvegarders = partiesSauvegarders;
			}

			public String toString() { 
				return "ID: "+id+" pseudo: "+this.pseudonyme+" mot de passe: "+this.motDePasse+ " Score: "+calculeScore();
			}
}
