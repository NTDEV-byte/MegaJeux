package com.mega;

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

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private Launch launch;
	/**
	 * Create the frame.
	 */
	public Connexion(Launch launcher) {
		this.launch = launcher;
	}
	
	
	private void connexion() { 
		String pseudo;
		String mot_de_passe;
		HashMap<String,Joueur> joueurs = (HashMap<String, Joueur>) launch.getJoueurs();
		
		pseudo = textField.getText();
		mot_de_passe = passwordField.getText();
		
		if(joueurs.containsKey(pseudo)) { 
			Joueur j = joueurs.get(pseudo);
				if(j.getMotDePasse().equals(mot_de_passe)) { 
					 System.out.println("connexion réussi !");
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
		
		JLabel lblNewLabel_3 = new JLabel("vous n'\u00EAtes pas inscrit ?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 289, 174, 19);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Inscrivez-vous");
		btnNewButton_1.setBounds(10, 332, 126, 21);
		contentPane.add(btnNewButton_1);
		
		this.setVisible(true);
	}
}
