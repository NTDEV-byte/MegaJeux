package com.mega.system;

import java.io.Serializable;

public class Joueur implements Serializable{

	private String pseudonyme;
	private String motDePasse;
	
	
		
			public Joueur(String pseudonyme,String motDePasse) { 
				 this.pseudonyme = pseudonyme;
				 this.motDePasse = motDePasse;
			}



			public String getPseudonyme() {
				return pseudonyme;
			}



			public void setPseudonyme(String pseudonyme) {
				this.pseudonyme = pseudonyme;
			}



			public String getMotDePasse() {
				return motDePasse;
			}



			public void setMotDePasse(String motDePasse) {
				this.motDePasse = motDePasse;
			}
	
			
			
			
			public String toString() { 
				return "pseudo: "+this.pseudonyme+" mot de passe: "+this.motDePasse;
			}
			
			
			
}
