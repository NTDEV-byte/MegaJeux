package tictactoe;
import javax.swing.SwingUtilities;

import mega.system.Joueur;

public class Main {

		
	
			public static void main(Joueur j1,Joueur j2) {
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run() { 
						// new TicTacToe(j1,j2);
					}
				});
			
			}
}
