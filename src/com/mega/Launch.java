package com.mega;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mega.utils.Utils;

public class Launch {
	
		
		private Inscription inscription;
		private Connexion connexion;
		private Map<String,Joueur> joueurs;
			
			
				public Launch(Inscription inscription) { 
					this.inscription = inscription;
					joueurs = new HashMap<String,Joueur>();
					ajouteJoueur();
					showPlayers();
					this.inscription.getFrame().dispose();
				}
				
				private void ajouteJoueur() { 
					File file = new File(Utils.PATH+"/joueurs");
					if(file.exists()) { 
						Object o = Utils.deserialize("joueurs");
						if(o instanceof HashMap) { 
							joueurs = (HashMap)(o);
						}
					}
					
					String pseudo;
					String mdp;
					pseudo = inscription.getTextField().getText();
					mdp = inscription.getPasswordField().getText();
					
					if(joueurs.containsKey(pseudo)) {
						  System.out.println("Ce pseudo est déja pris !");
						  /*
						   * Affiche les propositions 
						   */
						  
					}
					else {
							Joueur nouveau_inscrit = new Joueur(pseudo,mdp);
							joueurs.put(pseudo, nouveau_inscrit);
							Utils.serialize("joueurs",joueurs);
					}
					
					System.out.println("nouveau joueur inscrit ! pseudo: "+pseudo+ "mot de passe : "+mdp);
					
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
