package mega.vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import static mega.main.Application.*;
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
		setBackground(new Color(51, 102, 0));
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
					 System.out.println("connexion r�ussi !");
					 	 application.switchToMainPanel();
					}
				else {
					System.out.println("Mot de passe �rron�e");
				}
			}
		else {
			System.out.println("Vous n'�tes pas inscrit !");
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
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 35));
		lblNewLabel.setBounds(431, 41, 262, 33);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(0xffd09f));
		textField.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		textField.setBounds(379, 208, 367, 33);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pseudonyme");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(473, 144, 179, 39);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(470, 335, 185, 33);
		this.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(0xffd09f));
		
		passwordField.setBounds(379, 389, 367, 33);
		this.add(passwordField);
		
		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0x673824));
		btnNewButton.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexion();
			}
		});
		btnNewButton.setBounds(473, 482, 179, 45);
		this.add(btnNewButton);

		JLabel lbl_inscription = new JLabel("Vous n'\u00EAtes pas inscrit ?");
		lbl_inscription.setForeground(Color.WHITE);
		lbl_inscription.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		lbl_inscription.setBounds(444, 624, 236, 19);
		this.add(lbl_inscription);

		JButton inscrivez_vous = new JButton("Inscrivez-vous");
		inscrivez_vous.setBackground(new Color(0x673824));
		inscrivez_vous.setForeground(Color.WHITE);
		inscrivez_vous.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
		inscrivez_vous.setBounds(473, 679, 179, 33);
		inscrivez_vous.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				  application.switchToInscriptionPanel();
			}
		});
		this.add(inscrivez_vous);
		
	
		
		JLabel lbl_lock_img = new JLabel("");
		lbl_lock_img.setIcon(new ImageIcon("/lock_w.png"));
		lbl_lock_img.setBounds(431, 335, 38, 33);
		add(lbl_lock_img);
		
		JLabel lbl_user_ico = new JLabel("");
		lbl_user_ico.setIcon(new ImageIcon("/user.png"));
		lbl_user_ico.setBounds(431, 144, 38, 39);
		add(lbl_user_ico);
		

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(362, 24, 400, 50);
		add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(362, 84, 400, 50);
		add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(362, 603, 400, 50);
		add(separator_2);
		
	}
	
	public void paint(Graphics g) { 
		g.drawImage(image, 0, 0, image.getWidth() / 4, image.getHeight() / 4,null);
		g.setColor(new Color(100,81,62,80));
		g.fillRect(362, 24, 400, 60);//connectez-vous
		g.fillRect(473, 144, 179, 39);//pseudonyme
		g.fillRect(470, 335, 185, 33);//mdp
		g.fillRect(444, 624, 236, 19);//vous n'etes pas inscrit
		super.paintComponents(g);
	}
	
	
	public Joueur getJoueurConnecter() { 
			return jConnecter;
	}
}