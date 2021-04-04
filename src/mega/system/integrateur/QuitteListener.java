package mega.system.integrateur;

import java.awt.event.ActionEvent;

import mega.system.InterfaceJeu;

public class QuitteListener extends MegaJeuListener{

		
		
			public QuitteListener(InterfaceJeu jeu,IntegrateurMegaJeu integrateur) { 
				  super(jeu,integrateur);
			}

			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				integrateur.quittePartieAction();
			}

}
