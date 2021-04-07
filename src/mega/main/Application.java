package mega.main;


import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mega.jeux.chess.Chess;
import mega.jeux.chess.Pieces;
import mega.jeux.tictactoe.TicTacToe;
import mega.system.InterfaceJeu;
import mega.system.Joueur;
import mega.system.MegaJeuBD;
import mega.ui.UiConnexion;
import mega.ui.UiInscription;
import mega.ui.UiListeDesJoueurs;
import mega.ui.UiMain;
import mega.utils.Utils;

public class Application extends JFrame implements WindowListener{

	
	public static final BufferedImage image = Utils.loadIMG("/fond-min.jpg");
	public static final String TITLE = "MegaJeux";
	public static int width = 1073; //1073
	public static int height = 800; //800
	
	private MegaJeuBD model;
	private UiConnexion connexion;
	private UiInscription inscription;
	private UiMain vuePrincipale;
	private UiListeDesJoueurs listesJoueurs;
	
		public Application() { 
			init();
			this.setTitle(TITLE);
			this.addWindowListener(this);
			this.setVisible(true);
			this.setResizable(false);
			this.setLocationRelativeTo(this);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setContentPane(connexion);
			this.setSize(new Dimension(width,height));
		}

		private void init() { 
			model = new MegaJeuBD();
			connexion = new UiConnexion(this);
		}
		
		public void switchToInscriptionPanel() { 
			if(inscription == null) { 
				inscription = new UiInscription(this);
				this.setContentPane(inscription);
				this.revalidate();
			}else {
				this.setContentPane(inscription);
				this.revalidate();
			}
		}
		
		public void switchToConnexionPanel() { 
			if(connexion != null) { 
				this.setContentPane(connexion);
				this.revalidate();
			}
		}
	
		public void switchToMainPanel() { 
					if(vuePrincipale == null) { 
						vuePrincipale = new UiMain(connexion.getJoueurConnecter(),this);
						vuePrincipale.updateUIMV();
						this.setContentPane(vuePrincipale);
						this.revalidate();
					}
					else { 
						 vuePrincipale.updateUIMV();
						this.setContentPane(vuePrincipale);
						this.revalidate();
					}
		}
		
		public void switchToTicTacToe(Joueur j1,Joueur j2) { 
			this.setContentPane(new TicTacToe(this,j1,j2));
			this.revalidate();
		}

		public void switchToChess(Joueur j1,Joueur j2) { 
	        Pieces pieces = new Pieces();
	        pieces.setGUIGame(true);
			this.setContentPane(new Chess(pieces,this,j1,j2));
			this.revalidate();
		}
		
		public void reprendrePartie(InterfaceJeu j) { 
			if(j instanceof TicTacToe) { 
				TicTacToe partie = (TicTacToe) j;
				Joueur joueur2 = model.getListeJoueurs().get(partie.getIntegrateur().getPseudoj2());
				if(joueur2 != null) { 
					joueur2.chargePartiesJeuSauvegarder();
					joueur2.chargeProgressionJoueur();
					vuePrincipale.setJoueur2(joueur2);
				}
				partie.getIntegrateur().setInformationPourPartieReprise(this, vuePrincipale.getJoueur1(), vuePrincipale.getJoueur2());
				this.setContentPane(partie);
				this.revalidate();
			}
			else {
				Chess partie = (Chess) j;
				Joueur joueur2 = model.getListeJoueurs().get(partie.getIntegrateur().getPseudoj2());
				if(joueur2 != null) { 
					joueur2.chargePartiesJeuSauvegarder();
					joueur2.chargeProgressionJoueur();
					vuePrincipale.setJoueur2(joueur2);
				}
				partie.getIntegrateur().setInformationPourPartieReprise(this, vuePrincipale.getJoueur1(), vuePrincipale.getJoueur2());
				this.setContentPane(partie);
				this.revalidate();
			}
		}
		
		public void switchToChoisirAdversaire() { 
			this.setContentPane(listesJoueurs);
			this.revalidate();
		}
		
		public static void main(String[] args) {
				SwingUtilities.invokeLater(new Runnable() {
						public void run() { 
							new Application();
						}
				} );
		}

		public MegaJeuBD getModel() {
			return model;
		}

		public void setModel(MegaJeuBD model) {
			this.model = model;
		}

		public UiConnexion getConnexion() {
			return connexion;
		}

		public void setConnexion(UiConnexion connexion) {
			this.connexion = connexion;
		}

		public UiInscription getInscription() {
			return inscription;
		}

		public void setInscription(UiInscription inscription) {
			this.inscription = inscription;
		}

		public UiListeDesJoueurs getListesJoueurs() {
			return listesJoueurs;
		}

		public void setListesJoueurs(UiListeDesJoueurs listesJoueurs) {
			this.listesJoueurs = listesJoueurs;
		}

		public UiMain getVuePrincipale() {
			return vuePrincipale;
		}

		public void setVuePrincipale(UiMain vuePrincipale) {
			this.vuePrincipale = vuePrincipale;
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			if(vuePrincipale!=null) { 
				Joueur j1 = vuePrincipale.getJoueur1();
				if(j1 != null)j1.forceSaveAll();
			}
			System.out.println("Application Exited !");
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

}