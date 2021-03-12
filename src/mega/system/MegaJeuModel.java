package mega.system;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import mega.utils.Utils;
import mega.vues.ConnexionVue;

public class MegaJeuModel{
		
		public static int TOTAL_JOUEURS = 1;
	
		private ConnexionVue connexion;
		private HashMap<String,Joueur> joueurs;
			
				public MegaJeuModel() { 
					chargementBaseDonnee();
					showPlayers();
				}
				
				private void chargementBaseDonnee() {
					File file = new File(Utils.DATA_PATH+"/joueurs");
					if(file.exists()) { 
						Object o = Utils.deserialize("joueurs");
						if(o instanceof HashMap) { 
							joueurs = (HashMap)(o);
							TOTAL_JOUEURS = joueurs.size();
						}
					}
					else {
						joueurs = new HashMap<String,Joueur>();
						TOTAL_JOUEURS = 1; 
					}
				}
				
				private void showPlayers() {
					System.out.println("Liste des joueurs: ");
					Collection<Joueur> liste = joueurs.values();
					for(Joueur j : liste) {  
						System.out.println(j.toString());
					}
				}
				
				public HashMap<String, Joueur> getJoueurs() {
					return joueurs;
				}

				public void setJoueurs(HashMap<String, Joueur> joueurs) {
					this.joueurs = joueurs;
				}
}
