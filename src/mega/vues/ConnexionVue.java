package mega.vues;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.MegaJeuModel;

public class ConnexionVue extends JPanel {

	private JTextField textField;
	private JPasswordField passwordField;
	private Application application;
	private MegaJeuModel model;
	private Joueur jConnecter;
	/**
	 * Create the frame.
	 */
	public ConnexionVue(Application app) {
		this.model = app.getModel();
		this.application = app;
		initialize();
	}
	
	private void connexion() { 
		String pseudo;
		String mot_de_passe;
		HashMap<String,Joueur> joueurs = (HashMap<String, Joueur>) model.getJoueurs();
		
		pseudo = textField.getText();
		mot_de_passe = passwordField.getText();
		
		if(joueurs.containsKey(pseudo)) { 
			jConnecter = joueurs.get(pseudo);
				if(jConnecter.getMotDePasse().equals(mot_de_passe)) { 
					 System.out.println("connexion réussi !");
					 	 application.switchToMainPanel();
					}
				else {
					System.out.println("Mot de passe érronée");
				}
			}
		else {
			System.out.println("Vous n'êtes pas inscrit !");
		}
	}
	/*
	 * 
	 * 
	 * 
	 */
	public void initialize() { 
		//this.setSize(new Dimension(application.getWidth() / 2, application.getHeight() / 2));
		//this.setBounds(0, 0, 800, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connectez-vous");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(284, 45, 208, 33);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(267, 217, 208, 19);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pseudonyme");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(267, 166, 119, 24);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(267, 281, 109, 33);
		this.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(267, 344, 208, 19);
		this.add(passwordField);
		
		JButton btnNewButton = new JButton("Commencer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexion();
			}
		});
		btnNewButton.setBounds(300, 424, 119, 21);
		this.add(btnNewButton);

		JLabel lbl_inscription = new JLabel("Vous n'\u00EAtes pas inscrit ?");
		lbl_inscription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_inscription.setBounds(10, 477, 174, 19);
		this.add(lbl_inscription);

		JButton inscrivez_vous = new JButton("Inscrivez-vous");
		inscrivez_vous.setBounds(20, 515, 126, 21);
		inscrivez_vous.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				  application.switchToInscriptionPanel();
			}
		});
		this.add(inscrivez_vous);
		this.setVisible(true);
	}
	
	
	public Joueur getJoueurConnecter() { 
			return jConnecter;
	}
}