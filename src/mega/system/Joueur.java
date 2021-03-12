package mega.system;

import java.io.Serializable;

import mega.utils.Utils;

public class Joueur implements Serializable{

	public static String progressionSaveName = "progressions/progression_joueurs#ID";
	
	private int id;
	private String pseudonyme;
	private String motDePasse;
	private Progression progression;
	
			public Joueur(String pseudonyme,String motDePasse) { 
				 this.pseudonyme = pseudonyme;
				 this.motDePasse = motDePasse;
				 this.id = MegaJeuModel.TOTAL_JOUEURS;
				 MegaJeuModel.TOTAL_JOUEURS++;
				 if(!Utils.FileExists(progressionSaveName+id)) { 
					  progression = new Progression();
				 }
				 else {
					 Object obj =  Utils.deserialize(progressionSaveName+id);
					 progression = (Progression) obj;
				 }
			}

			public void chargeProgression() {
				if(Utils.FileExists(progressionSaveName+id)) {
					Object obj =  Utils.deserialize(progressionSaveName+id);
					progression = (Progression) obj;
				}
				else {
					System.err.println("Id: "+id+" Progression du joueur: "+pseudonyme+" est inéxistante !");
				}
			}
			
			public int getTotalScore() {
				 return progression.scoreCumule();
			}
			
			public void addPartie(Partie p) { 
				progression.ajoutePartie(p);
				Utils.serialize(progressionSaveName+id, progression);
				System.out.println("Progression du joueur: ID: "+id+" pseudonyme: "+pseudonyme+" est sauvegardé !");
			}
			
			public void enregistrerPartieCourante() { 
				if(Utils.FileExists(progressionSaveName+id)) {
					Utils.serialize(progressionSaveName+id, progression);
				}
			}
			
			
			public Partie getPartieEncours() { 
				if(progression.getHistorique().size() > 0) { 
					return progression.getHistorique().get(progression.getHistorique().size() - 1);
				}
				else {
					System.err.println("Aucune Partie n'a été trouvé !");
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
				return "ID: "+id+" pseudo: "+this.pseudonyme+" mot de passe: "+this.motDePasse+ " Score: "+getTotalScore();
			}


			public Progression getProgression() {
				return progression;
			}

			
}
