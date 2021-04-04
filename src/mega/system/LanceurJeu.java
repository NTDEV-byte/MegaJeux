package mega.system;

import java.io.Serializable;

import mega.main.Application;
import mega.system.Partie.Jeu;
import mega.ui.UiMain;

public class LanceurJeu implements Serializable{


		private Jeu jeu;
		private Application application;
		private Joueur joueur1,joueur2;
				
				public LanceurJeu(UiMain vuePrincipale,Jeu jeu) { 
					this.jeu = jeu;
					this.application = vuePrincipale.getApplication();
					this.joueur1 = vuePrincipale.getJoueur1();
					this.joueur2 = vuePrincipale.getJoueur2();
				}
		

			public void lanceJeu() { 
            if(joueur1 != null && joueur2 != null) {
            	System.out.println("LanceJeur !!!!!!!!!!!!!!!!!!!!!");
			joueur1.chargeProgressionJoueur();
			//System.out.println("Chargement Progression Joueur 1 ");
			//joueur1.getProgression().afficheHistorique();
			//System.out.println("Chargement Progression Joueur 2 ");
			joueur2.chargeProgressionJoueur();
			
			System.out.println(joueur1.toString());
			System.out.println(joueur2.toString());
			
			Partie p1 = new Partie(joueur1.getPseudonyme(),joueur2.getPseudonyme(),jeu);
			Partie p2 = new Partie(joueur2.getPseudonyme(),joueur1.getPseudonyme(),jeu);
			
			joueur1.addNouvellePartie(p1);
			joueur2.addNouvellePartie(p2);
			if(jeu == Jeu.CHESS) { 
				application.switchToChess(joueur1, joueur2);
			}
			else {
				application.switchToTicTacToe(joueur1, joueur2);
			}
		}
	}


			public Jeu getJeu() {
				return jeu;
			}


			public void setJeu(Jeu jeu) {
				this.jeu = jeu;
			}


			public Application getApplication() {
				return application;
			}


			public void setApplication(Application application) {
				this.application = application;
			}


			public Joueur getJoueur1() {
				return joueur1;
			}


			public void setJoueur1(Joueur joueur1) {
				this.joueur1 = joueur1;
			}


			public Joueur getJoueur2() {
				return joueur2;
			}


			public void setJoueur2(Joueur joueur2) {
				this.joueur2 = joueur2;
			}

	
}
