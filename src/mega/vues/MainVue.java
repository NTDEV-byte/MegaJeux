package mega.vues;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.system.Partie;
import mega.system.Partie.Jeu;



public class MainVue extends JPanel {
	
	private Partie.Jeu jeu_selectionner = Partie.Jeu.CHESS;
	private String titre_jeu_selectionner = "Chess";
	private JLabel lb_title_jeu_selectionner;
	private Joueur joueur1,joueur2;
	private MegaJeuModel model;
	private Application application;
	
	/**
	 * VOTRE_PROGRESSION_UI
	 * 
	 */
	JLabel lb_progression;
	JLabel lb_total_score; 
	JLabel lb_parties_gagnees;
	JLabel lbl_partie_egalites;
	JLabel lbl_parties_perdues;

	/**
	 * Create the Frame.
	 */
	public MainVue(Joueur joueur,Application app) {
		this.joueur1 = joueur;
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
		 * 
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
		derniere_partie_pan.setBounds(29, 61, 304, 205);
		pan_selected_game.add(derniere_partie_pan);
		
		derniere_partie_pan.setBackground(new Color(20, 255, 20));
		derniere_partie_pan.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Parties Sauvegard\u00E9es");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 10, 145, 28);
		derniere_partie_pan.add(lblNewLabel);
		
		JLabel partie_sauvegarder = new JLabel("PartieX");
		partie_sauvegarder.setBounds(10, 48, 45, 13);
		derniere_partie_pan.add(partie_sauvegarder);
		
		JButton btn_reprendre = new JButton("reprendre");
		btn_reprendre.setBounds(65, 48, 105, 21);
		derniere_partie_pan.add(btn_reprendre);
		
		JButton btn_supprimer = new JButton("supprimer");
		btn_supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btn_supprimer.setBounds(180, 48, 115, 21);
		derniere_partie_pan.add(btn_supprimer);

		JPanel meilleur_joueur_pan = new JPanel();
		meilleur_joueur_pan.setBounds(437, 61, 280, 205);
		pan_selected_game.add(meilleur_joueur_pan);
		meilleur_joueur_pan.setBackground(new Color(20, 255, 0));
		meilleur_joueur_pan.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Meilleurs Joueurs");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 10, 147, 26);
		meilleur_joueur_pan.add(lblNewLabel_1);
		
		JPanel score_pan = new JPanel();
		score_pan.setBounds(29, 298, 304, 160);
		pan_selected_game.add(score_pan);
		
		score_pan.setBackground(new Color(10, 200, 100));
		score_pan.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Score et R\u00E9sultats");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(0, 10, 141, 13);
		score_pan.add(lblNewLabel_2);
		
		JPanel progession_pan = new JPanel();
		progession_pan.setBounds(437, 298, 280, 160);
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
	
	private void afficheStats() { 
		
		
	}
	
	public void updateUIMV() {
		 joueur1.chargeProgression();
		 lb_total_score.setText("Votre Score: "+joueur1.getProgression().getScore());
		 lb_parties_gagnees.setText("Partie Gagn\u00E9es:"+joueur1.getProgression().getNbG());
		 lbl_partie_egalites.setText("Egalit\u00E9s:"+joueur1.getProgression().getNbE());
		 lbl_parties_perdues.setText("Perdues: "+joueur1.getProgression().getNbP());
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
			
			joueur1.chargeProgression();
			//System.out.println("Chargement Progression Joueur 1 ");
			//joueur1.getProgression().afficheHistorique();
			//System.out.println("Chargement Progression Joueur 2 ");
			joueur2.chargeProgression();
			
			System.out.println(joueur1.toString());
			System.out.println(joueur2.toString());
			
			Partie p1 = new Partie(joueur1,joueur2,jeu);
			Partie p2 = new Partie(joueur2,joueur1,jeu);
			
			joueur1.addPartie(p1);
			joueur2.addPartie(p2);
			
			if(jeu == Jeu.CHESS) { 
				chess.Game.main(null);
			}
			else {
				application.switchToTicTacToe(joueur2, joueur1);
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
