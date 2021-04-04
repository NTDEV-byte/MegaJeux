package mega.main;


import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mega.jeux.chess.Chess;
import mega.jeux.chess.Pieces;
import mega.jeux.tictactoe.TicTacToe;
import mega.system.InterfaceJeu;
import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.utils.Utils;
import mega.vues.ConnexionVue;
import mega.vues.InscriptionVue;
import mega.vues.ListeDesJoueursVue;
import mega.vues.MainVue;

public class Application extends JFrame{
	
	/******************
	 * Objectifs
	 ******************
	 * mainvue fixe 
	 * intégrateur button les commentaires 
	 * slots remplie joueur1 vs joueur 2
	 * message erreur connexion inscription (mot de passe incorrect pseudo)
	 * password vide pas autorisé 
	 * remove joptionpane
	 * sauvegarde + label ajour + rapport + test fonctionnel + soutenance 
	 *******************************
	 *Implémentation du jeu d'échec
	 *******************************
	 * redo
	 */
	
	public static final BufferedImage image = Utils.loadIMG("/fond-min.jpg");;
	public static final String TITLE = "MegaJeux";
	public static int width,height;
	private MegaJeuModel model;
	private ConnexionVue connexion;
	private InscriptionVue inscription;
	private MainVue vuePrincipale;
	private ListeDesJoueursVue listesJoueurs;
	
		public Application() { 
			init();
			this.setTitle(TITLE);
			this.setVisible(true);
			this.setResizable(false);
			this.setLocationRelativeTo(this);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setContentPane(connexion);
			this.setSize(new Dimension(WIDTH,HEIGHT));
		}
		
		public Application(int width,int height) { 
			init();
			Application.width = width;
			Application.height = height;
			SwingUtilities.updateComponentTreeUI(this);
			this.setTitle(TITLE);
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

		public void switchToChess(Joueur j1,Joueur j2) { 
	        Pieces pieces = new Pieces();
	        pieces.setGUIGame(true);
			this.setContentPane(new Chess(pieces,this,j1,j2));
			this.revalidate();
		}
		
		public void reprendrePartie(InterfaceJeu j) { 
			if(j instanceof TicTacToe) { 
				TicTacToe partie = (TicTacToe) j;
				partie.getIntegrateur().setApplication(this);
				partie.getIntegrateur().setJoueur1(vuePrincipale.getJoueur1());
				partie.getIntegrateur().setJoueur2(vuePrincipale.getJoueur2());
				this.setContentPane(partie);
				this.revalidate();
			}
			else {
				Chess partie = (Chess) j;
				partie.getIntegrateur().setApplication(this);
				partie.getIntegrateur().setJoueur1(vuePrincipale.getJoueur1());
				partie.getIntegrateur().setJoueur2(vuePrincipale.getJoueur2());
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
							new Application(1073,800);
						}
				} );
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
}