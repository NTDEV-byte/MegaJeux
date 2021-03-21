package mega.system;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import mega.utils.TrieuseJoueur;
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
				
				public List<Joueur> topCinqJoueurs(){ 
					 TrieuseJoueur trieuse_joueur = new TrieuseJoueur();
					 Collection<Joueur> joueursC =  (joueurs.values());
					 ArrayList<Joueur> liste_joueurs = new ArrayList<>(joueursC);
					 Collections.sort(liste_joueurs,trieuse_joueur);
					 showList(liste_joueurs);
					// System.out.println("Ok !XXXXXXXXXXXXXXXX");
					 return liste_joueurs;
				}

				private void showList(List<Joueur> js) { 
					for(Joueur j : js ) { 
						System.out.println(j.toString());
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
