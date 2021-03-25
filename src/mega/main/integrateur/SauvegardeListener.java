package mega.main.integrateur;

import java.awt.event.ActionEvent;

import mega.main.InterfaceJeu;

public class SauvegardeListener extends MegaJeuListener{
	
			
				public SauvegardeListener(InterfaceJeu jeu,IntegrateurMegaJeu integrateur) { 
							super(jeu,integrateur);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					 integrateur.sauvegardePartieAction(jeu);
				}
	

}




