package com.mega.vues;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mega.system.Joueur;

public class MainInterface extends JFrame {

	private JPanel contentPane;
	private Joueur joueur;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MegaJeu frame = new MegaJeu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public MainInterface(Joueur joueur) {
		this.joueur = joueur;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
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
		
		bt_chess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					 
				}
		} );
		
		
		JButton bt_tic_tac_toe = new JButton("Tic Tac Toe");
		bt_tic_tac_toe.setBounds(10, 82, 106, 21);
		librairie_pan.add(bt_tic_tac_toe);
		
		JButton bt_lancer = new JButton("Lancer");
		bt_lancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		
		
		JLabel title_jeu_selectionner = new JLabel("Chess");
		title_jeu_selectionner.setBounds(346, 5, 59, 27);
		title_jeu_selectionner.setFont(new Font("Tahoma", Font.PLAIN, 22));
		pan_selected_game.add(title_jeu_selectionner);
		title_jeu_selectionner.setBackground(new Color(0, 20, 255));
		
		JPanel derniere_partie_pan = new JPanel();
		derniere_partie_pan.setBounds(29, 50, 304, 205);
		pan_selected_game.add(derniere_partie_pan);
		
		derniere_partie_pan.setBackground(new Color(20, 255, 20));

		JPanel meilleur_joueur_pan = new JPanel();
		meilleur_joueur_pan.setBounds(437, 50, 280, 205);
		pan_selected_game.add(meilleur_joueur_pan);
		meilleur_joueur_pan.setBackground(new Color(20, 255, 0));
		
		
		JPanel score_pan = new JPanel();
		score_pan.setBounds(29, 298, 304, 160);
		pan_selected_game.add(score_pan);
		
		score_pan.setBackground(new Color(10, 200, 100));
		
		JPanel progession_pan = new JPanel();
		progession_pan.setBounds(437, 298, 280, 160);
		pan_selected_game.add(progession_pan);
		
		progession_pan.setBackground(new Color(85, 215, 155));
		
		JLabel lb_pseudo_joueur_connecter = new JLabel("pseudo");
		lb_pseudo_joueur_connecter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_pseudo_joueur_connecter.setBounds(26, 5, 153, 35);
		pan_selected_game.add(lb_pseudo_joueur_connecter);
		lb_pseudo_joueur_connecter.setText(joueur.getPseudonyme());
		
		this.setVisible(true);

	}
}
