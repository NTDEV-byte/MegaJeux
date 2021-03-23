package mega.main;


import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.vues.ConnexionVue;
import mega.vues.InscriptionVue;
import mega.vues.ListeDesJoueursVue;
import mega.vues.MainVue;
import tictactoe.TicTacToe;

public class Application extends JFrame implements WindowListener{
	
	/******************
	 * Objectifs
	 ******************
	 * Detection des états des parties lorsque on quitte la partie et on reprend XXX 
	 * suppression des parties finis sauvegardés XX 
	 * reste quand on clique sur quitter on doit supprimer les parties terminer sauvegarder
	 * affichage de progression doit synchrone avec le reste de l'interface
	 * Mise à jour de l'interface principale 
	 * Amélioration de la présentation
	 * 
	 *******************************
	 *Implémentation du jeu d'échec
	 *******************************
	 * redo
	 */
	
	
	private int width,height;
	private MegaJeuModel model;
	private ConnexionVue connexion;
	private InscriptionVue inscription;
	private MainVue vuePrincipale;
	private ListeDesJoueursVue listesJoueurs;
	
		public Application() { 
			init();
			this.setVisible(true);
			this.setResizable(false);
			this.setLocationRelativeTo(this);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.addWindowListener(this);
			this.setContentPane(connexion);
		}
		
		
		public Application(int width,int height) { 
			init();
			this.width = width;
			this.height = height;
			this.setVisible(true);
			this.setResizable(false);
			this.setLocationRelativeTo(this);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setContentPane(connexion);
			this.addWindowListener(this);
			this.setSize(new Dimension(width,height));
		}

		private void init() { 
			model = new MegaJeuModel();
			connexion = new ConnexionVue(this);
		}
		
		
		public void switchToInscriptionPanel() { 
			if(inscription == null) { 
				inscription = new InscriptionVue(this);
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
					System.out.println(connexion.getJoueurConnecter().getPseudonyme());
					if(vuePrincipale == null) { 
						vuePrincipale = new MainVue(connexion.getJoueurConnecter(),this);
						this.setContentPane(vuePrincipale);
						this.revalidate();
					}
					else { 
						this.setContentPane(vuePrincipale);
						this.revalidate();
					}
		}
		
		public void switchToTicTacToe(Joueur j1,Joueur j2) { 
			this.setContentPane(new TicTacToe(this,j1,j2));
			this.revalidate();
		}

		
		public void reprendrePartie(InterfaceJeu j) { 
			if(j instanceof TicTacToe) { 
				TicTacToe partie = (TicTacToe) j;
				partie.setApp(this);
				System.out.println("Application passed: "+partie.getApp());
				this.setContentPane(partie);
				this.revalidate();
			}
			else {
				
			}
		}
	
	
		
		public void switchToChoisirAdversaire() { 
			this.setContentPane(listesJoueurs);
			this.revalidate();
			
		}
		
		public static void main(String[] args) {
			 new Application(800,600);
		}


		public MegaJeuModel getModel() {
			return model;
		}


		public void setModel(MegaJeuModel model) {
			this.model = model;
		}


		public ConnexionVue getConnexion() {
			return connexion;
		}


		public void setConnexion(ConnexionVue connexion) {
			this.connexion = connexion;
		}


		public InscriptionVue getInscription() {
			return inscription;
		}


		public void setInscription(InscriptionVue inscription) {
			this.inscription = inscription;
		}


		public ListeDesJoueursVue getListesJoueurs() {
			return listesJoueurs;
		}


		public void setListesJoueurs(ListeDesJoueursVue listesJoueurs) {
			this.listesJoueurs = listesJoueurs;
		}


		public MainVue getVuePrincipale() {
			return vuePrincipale;
		}


		public void setVuePrincipale(MainVue vuePrincipale) {
			this.vuePrincipale = vuePrincipale;
		}


		public int getWidth() {
			return width;
		}


		public void setWidth(int width) {
			this.width = width;
		}


		public int getHeight() {
			return height;
		}


		public void setHeight(int height) {
			this.height = height;
		}


		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			if(vuePrincipale != null)	vuePrincipale.updateUIMV();
		}


		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}


		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			System.out.println("iconified !");
		}


		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowActivated(WindowEvent e) {
			System.out.println("Activated !");
			if(vuePrincipale != null)vuePrincipale.updateUIMV();
		}


		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}



}