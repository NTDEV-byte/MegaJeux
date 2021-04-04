package mega.system;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import mega.ui.UiConnexion;
import mega.utils.TrieuseJoueur;
import mega.utils.Utils;

public class MegaJeuBD{
		
		public static int TOTAL_JOUEURS = 1;
		private HashMap<String,Joueur> liste_joueurs;
			
				public MegaJeuBD() { 
					chargementBaseDonnee();
				//	showPlayers();
				}
				
				private void chargementBaseDonnee() {
					File file = new File(Utils.DATA_PATH+"/joueurs");
					if(file.exists()) { 
						Object o = Utils.deserialize("joueurs");
						if(o instanceof HashMap) { 
							liste_joueurs = (HashMap)(o);
							TOTAL_JOUEURS = liste_joueurs.size();
						}
					}
					else {
						liste_joueurs = new HashMap<String,Joueur>();
						TOTAL_JOUEURS = 1; 
					}
				}
				
				public List<Joueur> topCinqJoueurs(){ 
					 TrieuseJoueur trieuse_joueur = new TrieuseJoueur();
					 Collection<Joueur> joueursC =  (liste_joueurs.values());
					 ArrayList<Joueur> liste_joueurs = new ArrayList<>(joueursC);
					 Collections.sort(liste_joueurs,trieuse_joueur);
					 showList(liste_joueurs);
					 return liste_joueurs;
				}

				private void showList(List<Joueur> js) { 
					for(Joueur j : js ) { 
						System.out.println(j.toString());
					}
				}
				
				
				private void showPlayers() {
					//System.out.println("Liste des joueurs: ");
					Collection<Joueur> liste = liste_joueurs.values();
					for(Joueur j : liste) {  
						System.out.println(j.toString());
					}
				}
				
				public HashMap<String, Joueur> getListeJoueurs() {
					return liste_joueurs;
				}

}
