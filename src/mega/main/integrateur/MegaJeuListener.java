package mega.main.integrateur;

import java.awt.event.ActionListener;
import java.io.Serializable;

import mega.main.InterfaceJeu;

public abstract class MegaJeuListener implements ActionListener,Serializable{

	
	protected InterfaceJeu jeu;
	protected IntegrateurMegaJeu integrateur;
		
		
				public MegaJeuListener(InterfaceJeu jeu,IntegrateurMegaJeu integrateur) { 
						this.jeu = jeu;
						this.integrateur = integrateur;
				}

				public InterfaceJeu getJeu() {
					return jeu;
				}

				public void setJeu(InterfaceJeu jeu) {
					this.jeu = jeu;
				}

				public IntegrateurMegaJeu getIntegrateur() {
					return integrateur;
				}

				public void setIntegrateur(IntegrateurMegaJeu integrateur) {
					this.integrateur = integrateur;
				}
	
	
}
