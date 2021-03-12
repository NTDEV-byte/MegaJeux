package mega.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.vues.ConnexionVue;
import mega.vues.InscriptionVue;
import mega.vues.ListeDesJoueursVue;
import mega.vues.MainVue;
import tictactoe.TicTacToe;

public class Application extends JFrame{
	
	
	
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
					vuePrincipale = new MainVue(connexion.getJoueurConnecter(),this);
					this.setContentPane(vuePrincipale);
					this.revalidate();
		}
		
		
		public void switchToTicTacToe(Joueur j1,Joueur j2) { 
			this.setContentPane(new TicTacToe(this,j1,j2));
			this.revalidate();
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



}
