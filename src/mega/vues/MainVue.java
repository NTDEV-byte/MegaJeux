package mega.vues;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import mega.main.Application;
import mega.main.InterfaceJeu;
import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.system.Partie;
import mega.system.Partie.Jeu;
import mega.system.Progression;
import mega.utils.Utils;
import tictactoe.TicTacToe;



public class MainVue extends JPanel {
	
	public static int index_partie;
	
	private Partie.Jeu jeu_selectionner = Partie.Jeu.CHESS;
	private String titre_jeu_selectionner = "Chess";
	private JLabel lb_title_jeu_selectionner;
	private Joueur joueur1,joueur2;
	private MegaJeuModel model;
	private Application application;
	
	/**
	 * UI_VOTRE_PROGRESSION
	 * 
	 */
	private JLabel lb_progression;
	private JLabel lb_total_score; 
	private JLabel lb_parties_gagnees;
	private JLabel lbl_partie_egalites;
	private JLabel lbl_parties_perdues;

	
	/**
	 * UI_PARTIES_SAUVEGARDEE
	 * 
	 * 
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
		
		if(joueur1 == null) { 
			System.err.println("Joueur 1 nulle !");
			System.exit(1);
		}
		else {
			System.out.println("Joueur 1 OK !");
		}
		this.application = app;
		this.model = app.getModel();

		setBounds(100, 100, 900, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(null);
		
		JPanel librairie_pan = new JPanel();
		librairie_pan.setBounds(0, 72, 126, 481);
		panel.add(librairie_pan);
		librairie_pan.setBackground(new Color(56, 20, 20));
		librairie_pan.setLayout(null);
		
		JLabel lb_ma_librairie = new JLabel("Ma librairie");
		lb_ma_librairie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_ma_librairie.setForeground(Color.WHITE);
		lb_ma_librairie.setBounds(10, 10, 106, 20);
		librairie_pan.add(lb_ma_librairie);
		
		JButton bt_chess = new JButton("Chess");
		bt_chess.setBounds(10, 40, 106, 21);
		librairie_pan.add(bt_chess);
		
		lb_title_jeu_selectionner = new JLabel(titre_jeu_selectionner);
		
		bt_chess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					onClickChess();
				}
		} );
		
		JButton bt_tic_tac_toe = new JButton("Tic Tac Toe");
		bt_tic_tac_toe.setBounds(10, 82, 106, 21);
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
		bt_lancer.setBounds(10, 450, 106, 21);
		librairie_pan.add(bt_lancer);
		
		JPanel title_pan = new JPanel();
		title_pan.setBounds(0, 0, 876, 67);
		panel.add(title_pan);
		title_pan.setBackground(new Color(56, 146, 242));
		
		title_pan.setLayout(null);
		
		JLabel lb_mg = new JLabel("MegaJeux");
		lb_mg.setForeground(Color.BLUE);
		lb_mg.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_mg.setBounds(10, 10, 129, 27);
		title_pan.add(lb_mg);
		lb_mg.setBackground(new Color(20, 20, 20));
		
		JPanel main_pan = new JPanel();
		main_pan.setBounds(125, 65, 751, 488);
		panel.add(main_pan);
		main_pan.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pan_selected_game = new JPanel();
		main_pan.add(pan_selected_game);
		pan_selected_game.setLayout(null);
	//	pan_selected_game.setBackground(new Color(20, 20, 20));
		
	
		lb_title_jeu_selectionner.setBounds(332, 5, 153, 27);
		lb_title_jeu_selectionner.setFont(new Font("Tahoma", Font.PLAIN, 22));
		pan_selected_game.add(lb_title_jeu_selectionner);
		lb_title_jeu_selectionner.setBackground(new Color(0, 20, 255));
		
		JPanel derniere_partie_pan = new JPanel();
		derniere_partie_pan.setBounds(29, 61, 335, 216);
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
		btn_reprendre1.setBounds(141, 52, 92, 21);
		btn_reprendre1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if(joueur.getPartiesSauvegarders().size() >= 1) { 
					TicTacToe partie1  = (TicTacToe)(joueur1.getPartiesSauvegarders().get(0));
					if(partie1 != null) { 
					    application.reprendrePartie(partie1);
					}
				}
			}
		} );
		derniere_partie_pan.add(btn_reprendre1);
		
		JButton btn_supprimer1 = new JButton("supprimer");
		btn_supprimer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(joueur1.getPartiesSauvegarders().size() > 0) {
					 joueur1.supprimePartieSauvegardee(0);
					 lbl_partie_sauvegarder1.setText("Partie 1 Vide");
				}
				
			}
		});
		btn_supprimer1.setBounds(243, 52, 92, 21);
		derniere_partie_pan.add(btn_supprimer1);
		
	    lbl_partie_sauvegarder2 = new JLabel("Partie 2 vide");
		lbl_partie_sauvegarder2.setBounds(10, 99, 127, 13);
		
		derniere_partie_pan.add(lbl_partie_sauvegarder2);
		
		JButton btn_reprendre2 = new JButton("reprendre");
		btn_reprendre2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(joueur.getPartiesSauvegarders().size() >= 2) { 
					TicTacToe  partie2 = (TicTacToe)(joueur1.getPartiesSauvegarders().get(1));
					if(partie2 != null) { 
					    application.reprendrePartie(partie2);
					}
				}
			}
		});
		btn_reprendre2.setBounds(141, 95, 92, 21);
		derniere_partie_pan.add(btn_reprendre2);
		
		JButton btn_supprimer2 = new JButton("supprimer");
		btn_supprimer2.setBounds(243, 95, 92, 21);
		btn_supprimer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(joueur1.getPartiesSauvegarders().size() > 1) {
				 joueur1.supprimePartieSauvegardee(1);
				 lbl_partie_sauvegarder2.setText("Partie 2 Vide");
			}
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
				TicTacToe  partie3 = (TicTacToe)(joueur1.getPartiesSauvegarders().get(2));
				if(partie3 != null) { 
				    application.reprendrePartie(partie3);
				}
			}
		}
		});
		btn_reprendre3.setBounds(141, 135, 92, 21);
		derniere_partie_pan.add(btn_reprendre3);
		
		JButton btn_supprimer3 = new JButton("supprimer");
		btn_supprimer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(joueur1.getPartiesSauvegarders().size() > 2) {
				joueur1.supprimePartieSauvegardee(2);
				lbl_partie_sauvegarder3.setText("Partie 3 Vide");
			}
		}
		});
		btn_supprimer3.setBounds(243, 135, 92, 21);
		derniere_partie_pan.add(btn_supprimer3);

		
		affichePartieSauvegarder();
		
		
		JPanel meilleur_joueur_pan = new JPanel();
		meilleur_joueur_pan.setBounds(374, 61, 343, 216);
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
		score_pan.setBounds(29, 287, 335, 171);
		pan_selected_game.add(score_pan);
		
		score_pan.setBackground(new Color(10, 200, 100));
		score_pan.setLayout(null);
		
		afficheMeilleursJoueurs();
		
		
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
		
		afficheTroisDernierePartieJouee();
		
		
		JPanel progession_pan = new JPanel();
		progession_pan.setBounds(374, 287, 343, 171);
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
		lb_pseudo_joueur_connecter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_pseudo_joueur_connecter.setBounds(26, 5, 153, 35);
		pan_selected_game.add(lb_pseudo_joueur_connecter);
		lb_pseudo_joueur_connecter.setText(joueur1.getPseudonyme());
		
		this.setVisible(true);
		updateUIMV();
	}
	
	
	/*
	 * 
	 * PARTIE MISE A JOUR VUE PRINCIPALE 
	 * 
	 */
	
	private void afficheTroisDernierePartieJouee() { 
		joueur1.chargeProgressionJoueur();
		Progression p = joueur1.getProgression();
		if(p != null) { 
			List<Partie> troisDerniereParties = p.troisDerniereParties();
			
			switch(p.getHistorique().size()) { 
				
			case 0:
				return;
				
			case 1:
				
				lbl_partie1.setText(p.getHistorique().get(0).statPartie());
				break;
			case 2: 

				lbl_partie1.setText(p.getHistorique().get(0).statPartie());
				lbl_partie2.setText(p.getHistorique().get(1).statPartie());
				break;
				
			default:
				if(troisDerniereParties != null) {
					lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
					lbl_partie2.setText(troisDerniereParties.get(1).statPartie());
					lbl_partie3.setText(troisDerniereParties.get(2).statPartie());
				}
				break;
			}
		}
	}
	
	
	/**
	 * 
	 * 	PARTIES SAUVEGARDEE
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	
	private void affichePartieSauvegarder() { 
		  joueur1.chargeProgressionJoueur();
		  List<InterfaceJeu> partiesSauvegarder = joueur1.getPartiesSauvegarders();
		  TicTacToe partie1,partie2,partie3;
		  
		  if(partiesSauvegarder != null) { 
			   switch(partiesSauvegarder.size()) { 
			   	
			   case 0:
				   return;
			   case 1:
				   partie1 = (TicTacToe)partiesSauvegarder.get(0);
				   checkPartieDisponibleTTT(partie1,lbl_partie_sauvegarder1,1);

				   break;
				   
			   case 2:
				    partie1 = (TicTacToe)partiesSauvegarder.get(0);
				    checkPartieDisponibleTTT(partie1,lbl_partie_sauvegarder1,1);
				    partie2 = (TicTacToe)partiesSauvegarder.get(1);
				    checkPartieDisponibleTTT(partie2,lbl_partie_sauvegarder2,2);
				   break;
				   
			   default:
				    partie1 = (TicTacToe)partiesSauvegarder.get(0);
				    checkPartieDisponibleTTT(partie1,lbl_partie_sauvegarder1,1);					   
					partie2 = (TicTacToe)partiesSauvegarder.get(1);
					checkPartieDisponibleTTT(partie2,lbl_partie_sauvegarder2,2);		
					partie3 = (TicTacToe)partiesSauvegarder.get(2);
					checkPartieDisponibleTTT(partie3,lbl_partie_sauvegarder3,3);
				   break;
			   }
		  }
	}
	
	
	private void checkPartieDisponibleTTT(TicTacToe partie1,JLabel label,int index) { 
			if(partie1 != null) {
				 label.setText("Vs "+partie1.getIntegrateur().getPseudoj2()+" "+Utils.dateToString("YYYY-MM-dd",partie1.getIntegrateur().getDate()));
			}
			else {
				label.setText("Partie "+index+" Vide");
			}
	}
	
	/*
	 * affichage des meilleurs joueurs 
	 * 
	 */
	
	private void afficheMeilleursJoueurs() { 
		List<Joueur> joueurs = model.topCinqJoueurs();
		for(Joueur j : joueurs) { 
			j.chargeProgressionJoueur();
		}
		if(joueurs != null) {
		
		switch(joueurs.size()) { 
			case 0:
				return;
				
			case 1:
					
				lbl_j1.setText(joueurs.get(0).getPseudonyme());
				lbl_sc1.setText(joueurs.get(0).calculeScore()+"");
				break;
			case 2:
				lbl_j1.setText(joueurs.get(0).getPseudonyme());
				lbl_j2.setText(joueurs.get(1).getPseudonyme());
				lbl_sc1.setText(joueurs.get(0).calculeScore()+"");
				lbl_sc2.setText(joueurs.get(1).calculeScore()+"");
				
				break;
			case 3:
				lbl_j1.setText(joueurs.get(0).getPseudonyme());
				lbl_j2.setText(joueurs.get(1).getPseudonyme());
				lbl_j3.setText(joueurs.get(2).getPseudonyme());
				
				lbl_sc1.setText(joueurs.get(0).calculeScore()+"");
				lbl_sc2.setText(joueurs.get(1).calculeScore()+"");
				lbl_sc3.setText(joueurs.get(2).calculeScore()+"");
				break;
				
			case 4:
				lbl_j1.setText(joueurs.get(0).getPseudonyme());
				lbl_j2.setText(joueurs.get(1).getPseudonyme());
				lbl_j3.setText(joueurs.get(2).getPseudonyme());
				lbl_j4.setText(joueurs.get(3).getPseudonyme());
				
				lbl_sc1.setText(joueurs.get(0).getPseudonyme());
				lbl_sc2.setText(joueurs.get(1).getPseudonyme());
				lbl_sc3.setText(joueurs.get(2).getPseudonyme());
				lbl_sc4.setText(joueurs.get(3).getPseudonyme());
				break;

				default:
					lbl_j1.setText(joueurs.get(0).getPseudonyme());
					lbl_j2.setText(joueurs.get(1).getPseudonyme());
					lbl_j3.setText(joueurs.get(2).getPseudonyme());
					lbl_j4.setText(joueurs.get(3).getPseudonyme());
					lbl_j5.setText(joueurs.get(4).getPseudonyme());
					
					lbl_sc1.setText(joueurs.get(0).getPseudonyme());
					lbl_sc2.setText(joueurs.get(1).getPseudonyme());
					lbl_sc3.setText(joueurs.get(2).getPseudonyme());
					lbl_sc4.setText(joueurs.get(3).getPseudonyme());
					lbl_sc5.setText(joueurs.get(4).getPseudonyme());
					
					
				break;
			}
		}
	}
	
	public void updateUIMV() {
		 joueur1.chargeProgressionJoueur();
		 if(joueur1.getProgression() != null) { 
			 lb_total_score.setText("Votre Score: "+joueur1.getProgression().getScore());
			 lb_parties_gagnees.setText("Partie Gagn\u00E9es:"+joueur1.getProgression().getNbG());
			 lbl_partie_egalites.setText("Egalit\u00E9s:"+joueur1.getProgression().getNbE());
			 lbl_parties_perdues.setText("Perdues: "+joueur1.getProgression().getNbP());
		 }
		 afficheMeilleursJoueurs();
		 affichePartieSauvegarder();
		 afficheTroisDernierePartieJouee();
		 int indexSupprimer = joueur1.supprimePartieSauvegardeeEtTerminee();
		 updatePartiesSauvegarderLBLApresSuppression(indexSupprimer);
	}
	
	private void updatePartiesSauvegarderLBLApresSuppression(int index) { 
		 if(index != -1) { 
			 switch(index) { 
			 case 0:
				 
				 lbl_partie_sauvegarder1.setText("Partie 1 Vide");
				 
				 break;
				 
			 case 1:
				 lbl_partie_sauvegarder2.setText("Partie 2 Vide");
				 break;
				 
			 case 2:
				 lbl_partie_sauvegarder3.setText("Partie 3 Vide");
				 
				 break;
			 }
		 }
	}
    private void onClickChess() {
		jeu_selectionner = Partie.Jeu.CHESS;
	    titre_jeu_selectionner = "Chess";
	    lb_title_jeu_selectionner.setText(titre_jeu_selectionner);
	}
	
	private void onClickTicTacToe() {
		jeu_selectionner = Partie.Jeu.TICTACTOE;
		titre_jeu_selectionner = "TicTacToe";
		lb_title_jeu_selectionner.setText(titre_jeu_selectionner);
	}
	
	private void choisirJoueurAdversaire() { 
		ListeDesJoueursVue listes =  new ListeDesJoueursVue(application);
		application.switchToChoisirAdversaire();
		if(listes.getAdversaire()!= null) { 
			 joueur2 = listes.getAdversaire();
			 System.out.println("Adversaire Pseudonyme: "+joueur2.getPseudonyme());
			}
		else {
			System.out.println("Veuillez choisir un adversaire dans la liste !");
		}
	}
	
	private void lancerPartie(Jeu jeu) { 
		updateUIMV();
		if(joueur1 != null && joueur2 != null) {
			
			joueur1.chargeProgressionJoueur();
			//System.out.println("Chargement Progression Joueur 1 ");
			//joueur1.getProgression().afficheHistorique();
			//System.out.println("Chargement Progression Joueur 2 ");
			joueur2.chargeProgressionJoueur();
			
			System.out.println(joueur1.toString());
			System.out.println(joueur2.toString());
			
			Partie p1 = new Partie(joueur1.getPseudonyme(),joueur2.getPseudonyme(),jeu);
			Partie p2 = new Partie(joueur2.getPseudonyme(),joueur1.getPseudonyme(),jeu);
			
			joueur1.addNouvellePartie(p1);
			joueur2.addNouvellePartie(p2);
			
			if(jeu == Jeu.CHESS) { 

				application.switchToChess(joueur1, joueur2);
			}
			else {
				application.switchToTicTacToe(joueur1, joueur2);
			}
		}
	}

	public void lancerJeu() { 
			lancerPartie(jeu_selectionner);
		    System.out.println(joueur1.getProgression().afficheHistorique());
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
}
