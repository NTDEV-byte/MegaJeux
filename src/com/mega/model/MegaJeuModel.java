package com.mega.model;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mega.system.Joueur;
import com.mega.utils.Utils;
import com.mega.vues.Connexion;

public class MegaJeuModel {
		
		private Connexion connexion;
		private Map<String,Joueur> joueurs;
			
				public MegaJeuModel() { 
					chargementBaseDonnee();
					openConnexion();
					showPlayers();
				}
				
				
				private void chargementBaseDonnee() {
					File file = new File(Utils.PATH+"/joueurs");
					if(file.exists()) { 
						Object o = Utils.deserialize("joueurs");
						if(o instanceof HashMap) { 
							joueurs = (HashMap)(o);
						}
					}
					else {
						joueurs = new HashMap<String,Joueur>();
					}
				}
				
				
				
				public void openConnexion() {
					connexion = new Connexion(this);
					connexion.open();
				}
				
				
				
				private void showPlayers() {
					System.out.println("Liste des joueurs: ");
					Collection<Joueur> liste = joueurs.values();
					int numero = 1;
					for(Joueur j : liste) {  
						System.out.println("numero: "+numero+" "+j.toString());
						numero++;
					}
				}
				
				public Map<String, Joueur> getJoueurs() {
					return joueurs;
				}

				public void setJoueurs(Map<String, Joueur> joueurs) {
					this.joueurs = joueurs;
				}

}
