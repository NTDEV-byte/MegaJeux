package mega.main.integrateur;

import java.awt.event.ActionEvent;

import mega.main.InterfaceJeu;

public class QuitteListener extends MegaJeuListener{

		
		
			public QuitteListener(InterfaceJeu jeu,IntegrateurMegaJeu integrateur) { 
				  super(jeu,integrateur);
			}

			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				integrateur.quittePartieAction();
			}

}
