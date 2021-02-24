package com.mega.vues;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mega.model.MegaJeuModel;
import com.mega.system.Joueur;

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private MegaJeuModel jeu;
	/**
	 * Create the frame.
	 */
	public Connexion(MegaJeuModel jeu) {
		this.jeu = jeu;
	}
	
	private void connexion() { 
		String pseudo;
		String mot_de_passe;
		HashMap<String,Joueur> joueurs = (HashMap<String, Joueur>) jeu.getJoueurs();
		
		pseudo = textField.getText();
		mot_de_passe = passwordField.getText();
		
		if(joueurs.containsKey(pseudo)) { 
			Joueur joueur = joueurs.get(pseudo);
				if(joueur.getMotDePasse().equals(mot_de_passe)) { 
					 System.out.println("connexion réussi !");
					 	 new MainInterface(joueur);
					 	 this.dispose();
					}
				else {
					System.out.println("Mot de passe érronée");
				}
			}
		else {
			System.out.println("Vous n'êtes pas inscrit !");
		}
	}
	
	public void open() { 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connectez-vous");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(201, 30, 208, 33);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 132, 208, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pseudonyme");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(201, 98, 119, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(201, 161, 109, 33);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 204, 208, 19);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Commencer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexion();
			}
		});
		btnNewButton.setBounds(244, 265, 119, 21);
		contentPane.add(btnNewButton);

		JLabel lbl_inscription = new JLabel("Vous n'\u00EAtes pas inscrit ?");
		lbl_inscription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_inscription.setBounds(10, 289, 174, 19);
		contentPane.add(lbl_inscription);

		JButton inscrivez_vous = new JButton("Inscrivez-vous");
		inscrivez_vous.setBounds(10, 332, 126, 21);
		inscrivez_vous.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				 	new Inscription(jeu);
			}
		});
		contentPane.add(inscrivez_vous);
		this.setVisible(true);
	}
}
