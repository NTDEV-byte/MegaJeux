package mega.vues;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import mega.main.Application;
import mega.system.InterfaceJeu;
import mega.system.Joueur;
import mega.system.LanceurJeu;
import mega.system.MegaJeuModel;
import mega.system.Partie;
import mega.system.Partie.Jeu;
import mega.system.Statistique;



public class MainVue extends JPanel {
	
	public static int index_partie;
	
	private Partie.Jeu jeu_selectionner = Partie.Jeu.CHESS;
	private String titre_jeu_selectionner = "Chess";
	private JLabel lb_title_jeu_selectionner;
	private Joueur joueur1,joueur2;
	private MegaJeuModel model;
	private Application application;
	private LanceurJeu lanceur;
	private Statistique statistique;
	
	/**
	 * UI_VOTRE_PROGRESSION
	 */
	private JLabel lb_progression;
	private JLabel lb_total_score; 
	private JLabel lb_parties_gagnees;
	private JLabel lbl_partie_egalites;
	private JLabel lbl_parties_perdues;

	
	/**
	 * UI_PARTIES_SAUVEGARDEE
	 */
	private JLabel lbl_partie_sauvegarder1;
	private JLabel lbl_partie_sauvegarder2;
	private JLabel lbl_partie_sauvegarder3;
	
	
	/**
	 * UI_MEILLEURS_JOUEURS
	 * 
	 * 
	 */
	private JLabel lbl_j1;
	private JLabel lbl_j2;
	private JLabel lbl_j3;
	private JLabel lbl_j4;
	private JLabel lbl_j5;
	
	private JLabel lbl_sc1;
	private JLabel lbl_sc2;
	private JLabel lbl_sc3;
	private JLabel lbl_sc4;
	private JLabel lbl_sc5;
	
	/*
	 * UI_TROIS_DERNIERE_PARTIES_JOUER
	 * 
	 * 
	 */
	private JLabel lbl_score_et_resultat;
	private JLabel lbl_partie1;
	private JLabel lbl_partie2;
	private JLabel lbl_partie3;
	/**
	 * Create the Frame.
	 */
	public MainVue(Joueur joueur,Application app) {
		this.joueur1 = joueur;
		this.model = app.getModel();
		this.application = app;
		this.lanceur = new LanceurJeu(this,jeu_selectionner);
		this.statistique = new Statistique(this);
		if(joueur1 == null) { 
			System.err.println("Joueur 1 null !");
			System.exit(1);
		}
		else {
			System.out.println("Joueur 1 OK !");
		}

		setBounds(100, 100, 1073, 800);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(null);
		
		JPanel librairie_pan = new JPanel();
		librairie_pan.setBounds(0, 72, 171, 718);
		panel.add(librairie_pan);
		librairie_pan.setBackground(new Color(56, 20, 20));
		librairie_pan.setLayout(null);
		
		JLabel lb_ma_librairie = new JLabel("Ma librairie");
		lb_ma_librairie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_ma_librairie.setForeground(Color.WHITE);
		lb_ma_librairie.setBounds(10, 10, 106, 20);
		librairie_pan.add(lb_ma_librairie);
		
		JButton bt_chess = new JButton("Chess");
		bt_chess.setBounds(10, 40, 151, 21);
		librairie_pan.add(bt_chess);
		
		bt_chess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					onClickChess();
				}
		} );
		
		JButton bt_tic_tac_toe = new JButton("Tic Tac Toe");
		bt_tic_tac_toe.setBounds(10, 82, 151, 21);
		librairie_pan.add(bt_tic_tac_toe);
		
		bt_tic_tac_toe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				onClickTicTacToe();
			}
		} );
		  
		/*
		 * Lorsque le joueur clique sur le button lancer c'est en ce moment 
		 * qu'on lance l'interface qui demande au joueur de choisir un adversaire
		 */
		
		JButton bt_lancer = new JButton("Lancer");
		bt_lancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choisirJoueurAdversaire();
			}
		});
		
		bt_lancer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bt_lancer.setBounds(10, 651, 151, 21);
		librairie_pan.add(bt_lancer);
		
		JPanel title_pan = new JPanel();
		title_pan.setBounds(0, 0, 1063, 67);
		panel.add(title_pan);
		title_pan.setBackground(new Color(56, 146, 242));
		
		title_pan.setLayout(null);
		
		JLabel lb_mg = new JLabel("MegaJeux");
		lb_mg.setForeground(Color.BLUE);
		lb_mg.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_mg.setBounds(10, 10, 129, 27);
		title_pan.add(lb_mg);
		lb_mg.setBackground(new Color(20, 20, 20));
		
		lb_title_jeu_selectionner = new JLabel(titre_jeu_selectionner);
		lb_title_jeu_selectionner.setForeground(new Color(255, 255, 0));
		
		JPanel pan_selected_game = new JPanel();
		pan_selected_game.setBackground(Color.DARK_GRAY);
		pan_selected_game.setBounds(130, 72, 933, 718);
		panel.add(pan_selected_game);
		pan_selected_game.setLayout(null);
		//	pan_selected_game.setBackground(new Color(20, 20, 20));
			
		
			lb_title_jeu_selectionner.setBounds(450, 10, 179, 27);
			lb_title_jeu_selectionner.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 25));
			pan_selected_game.add(lb_title_jeu_selectionner);
			lb_title_jeu_selectionner.setBackground(new Color(0, 20, 255));
			
			JPanel derniere_partie_pan = new JPanel();
			derniere_partie_pan.setBounds(47, 61, 426, 216);
			pan_selected_game.add(derniere_partie_pan);
			
			derniere_partie_pan.setBackground(new Color(20, 255, 20));
			derniere_partie_pan.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Parties Sauvegard\u00E9es");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBounds(0, 10, 145, 28);
			derniere_partie_pan.add(lblNewLabel);
			
	    lbl_partie_sauvegarder1 = new JLabel("Partie 1 vide");
	    lbl_partie_sauvegarder1.setBounds(10, 56, 127, 13);
	    derniere_partie_pan.add(lbl_partie_sauvegarder1);
	    
	    JButton btn_reprendre1 = new JButton("reprendre");
	    btn_reprendre1.setBounds(141, 52, 120, 21);
	    btn_reprendre1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
	    		if(joueur.getPartiesSauvegarders().size() >= 1) { 
	    			InterfaceJeu partie1 = getPartieXXX(0);
	    			if(partie1 != null) { 
	    			    application.reprendrePartie(partie1);
	    			    statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    				statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    			}
	    		}
	    	}
	    } );
	    derniere_partie_pan.add(btn_reprendre1);
	    
	    JButton btn_supprimer1 = new JButton("supprimer");
	    btn_supprimer1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		joueur1.supprimePartie(jeu_selectionner, 0);
	    		statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    		statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    	}
	    });
	    btn_supprimer1.setBounds(296, 52, 120, 21);
	    derniere_partie_pan.add(btn_supprimer1);
	    
	    lbl_partie_sauvegarder2 = new JLabel("Partie 2 vide");
	    lbl_partie_sauvegarder2.setBounds(10, 99, 127, 13);
	    
	    derniere_partie_pan.add(lbl_partie_sauvegarder2);
	    
	    JButton btn_reprendre2 = new JButton("reprendre");
	    btn_reprendre2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(joueur.getPartiesSauvegarders().size() >= 2) { 
	    			InterfaceJeu partie2 = getPartieXXX(1);
	    			if(partie2 != null) { 
	    			     application.reprendrePartie(partie2);
	    			     statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    				 statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    			}
	    		}
	    	}
	    });
	    btn_reprendre2.setBounds(141, 95, 120, 21);
	    derniere_partie_pan.add(btn_reprendre2);
	    
	    JButton btn_supprimer2 = new JButton("supprimer");
	    btn_supprimer2.setBounds(296, 95, 120, 21);
	    btn_supprimer2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		joueur1.supprimePartie(jeu_selectionner, 1);
	    		statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    		 statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    }
	    });
	    derniere_partie_pan.add(btn_supprimer2);
	    
	    lbl_partie_sauvegarder3 = new JLabel("Partie 3 vide");
	    lbl_partie_sauvegarder3.setBounds(10, 139, 127, 13);
	    derniere_partie_pan.add(lbl_partie_sauvegarder3);
	    
	    JButton btn_reprendre3 = new JButton("reprendre");
	    btn_reprendre3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(joueur.getPartiesSauvegarders().size() >= 3) { 
	    			InterfaceJeu partie3 = getPartieXXX(2);
	    		if(partie3 != null) { 
	    		    application.reprendrePartie(partie3);
	    		    statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    			 statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    		}
	    	}
	    }
	    });
	    btn_reprendre3.setBounds(141, 135, 120, 21);
	    derniere_partie_pan.add(btn_reprendre3);
	    
	    JButton btn_supprimer3 = new JButton("supprimer");
	    btn_supprimer3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		joueur1.supprimePartie(jeu_selectionner,2);
	    		statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    		 statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    	}
	    });
	    btn_supprimer3.setBounds(296, 135, 120, 21);
	    derniere_partie_pan.add(btn_supprimer3);
	    
	    		
	    	//	affichePartieSauvegarder();
	    		
	    		statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    		statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    		JPanel meilleur_joueur_pan = new JPanel();
	    		meilleur_joueur_pan.setBounds(497, 61, 410, 216);
	    		pan_selected_game.add(meilleur_joueur_pan);
	    		meilleur_joueur_pan.setBackground(new Color(20, 255, 0));
	    		meilleur_joueur_pan.setLayout(null);
	    		
	    		JLabel lblNewLabel_1 = new JLabel("Meilleurs Joueurs");
	    		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_1.setBounds(10, 10, 147, 26);
	    		meilleur_joueur_pan.add(lblNewLabel_1);
	    		
	    		
	    		lbl_j1 = new JLabel("Joueur1");
	    		lbl_j1.setBounds(10, 79, 100, 13);
	    		meilleur_joueur_pan.add(lbl_j1);
	    		
	    lbl_j2 = new JLabel("Joueur2");
	    lbl_j2.setBounds(10, 102, 100, 13);
	    meilleur_joueur_pan.add(lbl_j2);
	    
	    lbl_j3 = new JLabel("Joueur3");
	    lbl_j3.setBounds(10, 125, 100, 13);
	    meilleur_joueur_pan.add(lbl_j3);
	    
	    lbl_j4 = new JLabel("Joueur4");
	    lbl_j4.setBounds(10, 148, 88, 13);
	    meilleur_joueur_pan.add(lbl_j4);
	    
	    lbl_j5 = new JLabel("Joueur5");
	    lbl_j5.setBounds(10, 171, 100, 13);
	    meilleur_joueur_pan.add(lbl_j5);
	    
	    JLabel lbl_pseudonyme = new JLabel("Pseudonyme");
	    lbl_pseudonyme.setBounds(10, 46, 100, 13);
	    meilleur_joueur_pan.add(lbl_pseudonyme);
	    
	    JLabel lbl_score = new JLabel("Score");
	    lbl_score.setBounds(200, 46, 64, 13);
	    meilleur_joueur_pan.add(lbl_score);
	    
	    lbl_sc1 = new JLabel("Score 1");
	    lbl_sc1.setBounds(200, 79, 77, 13);
	    meilleur_joueur_pan.add(lbl_sc1);
	    
	    lbl_sc2 = new JLabel("Score 2");
	    lbl_sc2.setBounds(200, 102, 77, 13);
	    meilleur_joueur_pan.add(lbl_sc2);
	    
	    lbl_sc3 = new JLabel("Score 3");
	    lbl_sc3.setBounds(200, 125, 77, 13);
	    meilleur_joueur_pan.add(lbl_sc3);
	    
	    lbl_sc4 = new JLabel("Score 4");
	    lbl_sc4.setBounds(200, 148, 77, 13);
	    meilleur_joueur_pan.add(lbl_sc4);
	    
	    lbl_sc5 = new JLabel("Score 5");
	    lbl_sc5.setBounds(200, 171, 88, 13);
	    meilleur_joueur_pan.add(lbl_sc5);
	    
	    JSeparator separator = new JSeparator();
	    separator.setBounds(10, 59, 1, 2);
	    meilleur_joueur_pan.add(separator);
	    
	    JSeparator separator_1 = new JSeparator();
	    separator_1.setBounds(10, 67, 323, 2);
	    meilleur_joueur_pan.add(separator_1);
	    
	    JSeparator separator_1_1 = new JSeparator(JSeparator.VERTICAL);
	    separator_1_1.setBounds(178, 66, 12, 231);
	    meilleur_joueur_pan.add(separator_1_1);
	    
	    JPanel score_pan = new JPanel();
	    score_pan.setBounds(47, 307, 426, 260);
	    pan_selected_game.add(score_pan);
	    
	    score_pan.setBackground(new Color(10, 200, 100));
	    score_pan.setLayout(null);
	    
	    //afficheMeilleursJoueurs();
	    
	    statistique.afficheMeilleursJoueurs(lbl_j1, lbl_sc1, lbl_j2, lbl_sc2, lbl_j3, lbl_sc3, lbl_j4, lbl_sc4, lbl_j5, lbl_sc5);
	    lbl_score_et_resultat = new JLabel("Score et R\u00E9sultats");
	    lbl_score_et_resultat.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbl_score_et_resultat.setBounds(0, 10, 141, 13);
	    score_pan.add(lbl_score_et_resultat);
	    
	    lbl_partie1 = new JLabel("Partie 1 indisponible");
	    lbl_partie1.setBounds(0, 47, 335, 13);
	    score_pan.add(lbl_partie1);
	    
	    lbl_partie2 = new JLabel("Partie 2 indisponible");
	    lbl_partie2.setBounds(0, 88, 335, 13);
	    score_pan.add(lbl_partie2);
	    
	    lbl_partie3 = new JLabel("Partie 3 indisponible");
	    lbl_partie3.setBounds(0, 125, 335, 13);
	    score_pan.add(lbl_partie3);
	    
	    //afficheTroisDernierePartieJouee();
	    
	    statistique.afficheTroisDernierePartieJouee(jeu_selectionner,lbl_partie1, lbl_partie2, lbl_partie3);
	    
	    
	    JPanel progession_pan = new JPanel();
	    progession_pan.setBounds(497, 307, 410, 260);
	    pan_selected_game.add(progession_pan);
	    
	    progession_pan.setBackground(Color.PINK);
	    progession_pan.setLayout(null);
	    
	    lb_progression = new JLabel("Votre Progression: ");
	    lb_progression.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lb_progression.setBounds(0, 0, 280, 28);
	    progession_pan.add(lb_progression);
	    
	    lb_total_score = new JLabel("Votre Score: ");
	    lb_total_score.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lb_total_score.setBounds(0, 38, 280, 13);
	    progession_pan.add(lb_total_score);
	    
	 
	    lb_parties_gagnees = new JLabel("Partie Gagn\u00E9es:");
	    lb_parties_gagnees.setForeground(Color.GREEN);
	    lb_parties_gagnees.setBounds(0, 61, 270, 13);
	    progession_pan.add(lb_parties_gagnees);
	    
	     lbl_partie_egalites = new JLabel("Egalit\u00E9s:");
	     lbl_partie_egalites.setForeground(Color.BLUE);
	     lbl_partie_egalites.setBounds(0, 84, 270, 13);
	     progession_pan.add(lbl_partie_egalites);
	     
	     lbl_parties_perdues = new JLabel("Perdues: ");
	     lbl_parties_perdues.setForeground(Color.RED);
	     lbl_parties_perdues.setBounds(0, 107, 270, 13);
	     progession_pan.add(lbl_parties_perdues);
	     
	     JLabel lb_pseudo_joueur_connecter = new JLabel("pseudo");
	     lb_pseudo_joueur_connecter.setForeground(new Color(255, 255, 0));
	     lb_pseudo_joueur_connecter.setBounds(48, 27, 153, 35);
	     pan_selected_game.add(lb_pseudo_joueur_connecter);
	     lb_pseudo_joueur_connecter.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 24));
	     lb_pseudo_joueur_connecter.setText(joueur1.getPseudonyme());
		
		this.setVisible(true);
		updateUIMV();
	}
	
	private InterfaceJeu getPartieXXX(int index) { 
		if(jeu_selectionner == Jeu.TICTACTOE) {
		 return joueur1.getPartiesSauvegarderTTT().get(index);
		}
		else {
		return joueur1.getPartiesSauvegarderChess().get(index);	
		}
	}
	/*
	 * affichage des meilleurs joueurs 
	 * 
	 */

	public void updateUIMV() {
		 joueur1.chargeProgressionJoueur();
		 statistique.progressionTotal(lb_total_score, lb_parties_gagnees, lbl_partie_egalites, lbl_parties_perdues);
		 statistique.afficheMeilleursJoueurs(lbl_j1, lbl_sc1, lbl_j2, lbl_sc2, lbl_j3, lbl_sc3, lbl_j4, lbl_sc4, lbl_j5, lbl_sc5);
		 statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
		 statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
 		 statistique.afficheTroisDernierePartieJouee(jeu_selectionner,lbl_partie1, lbl_partie2, lbl_partie3);
	}
	
    private void onClickChess() {
		jeu_selectionner = Partie.Jeu.CHESS;
		lanceur.setJeu(jeu_selectionner);
	    titre_jeu_selectionner = "Chess";
	    lb_title_jeu_selectionner.setText(titre_jeu_selectionner);
	    statistique.updateLabelsSauvegardeChess(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
	    statistique.afficheTroisDernierePartieJouee(jeu_selectionner,lbl_partie1, lbl_partie2, lbl_partie3);
    }
	
	private void onClickTicTacToe() {
		jeu_selectionner = Partie.Jeu.TICTACTOE;
		lanceur.setJeu(jeu_selectionner);
		titre_jeu_selectionner = "TicTacToe";
		lb_title_jeu_selectionner.setText(titre_jeu_selectionner);
		statistique.updateLabelsSauvegardeTTT(lbl_partie_sauvegarder1, lbl_partie_sauvegarder2, lbl_partie_sauvegarder3);
		statistique.afficheTroisDernierePartieJouee(jeu_selectionner,lbl_partie1, lbl_partie2, lbl_partie3);
	}
	
	private void choisirJoueurAdversaire() { 
		ListeDesJoueursVue listes =  new ListeDesJoueursVue(application);
		application.switchToChoisirAdversaire();
		if(listes.getAdversaireSelectionner()!= null) { 
			 joueur2 = listes.getAdversaireSelectionner();
			 lanceur.setJoueur2(joueur2);
			 System.out.println("Adversaire Pseudonyme: "+joueur2.getPseudonyme());
			}
		else {
			System.out.println("Veuillez choisir un adversaire dans la liste !");
		}
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



	public MegaJeuModel getModel() {
		return model;
	}



	public void setModel(MegaJeuModel model) {
		this.model = model;
	}



	public Application getApplication() {
		return application;
	}



	public void setApplication(Application application) {
		this.application = application;
	}



	public Partie.Jeu getJeu_selectionner() {
		return jeu_selectionner;
	}



	public void setJeu_selectionner(Partie.Jeu jeu_selectionner) {
		this.jeu_selectionner = jeu_selectionner;
	}



	public LanceurJeu getLanceur() {
		return lanceur;
	}



	public void setLanceur(LanceurJeu lanceur) {
		this.lanceur = lanceur;
	}
}
