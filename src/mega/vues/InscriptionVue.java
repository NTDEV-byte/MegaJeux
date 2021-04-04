package mega.vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.utils.Utils;
import static mega.main.Application.*;


public class InscriptionVue extends JPanel{

	private JTextField textField;
	private JPasswordField passwordField;
	private JTextArea textArea;
	private JLabel error_label;
	private MegaJeuModel model;
	private Application application;
	
	/**
	 * Create the application.
	 */
	public InscriptionVue(Application application) {
		setBackground(new Color(0, 128, 0));
		setForeground(new Color(0, 153, 102));
		this.model = application.getModel();
		this.application = application;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(0, 10, 1073, 800);
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Créer votre pseudonyme");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 24));
		lblNewLabel.setBounds(412, 90, 300, 31);
		this.add(lblNewLabel);
		
		error_label = new JLabel("");
		error_label.setForeground(Color.RED);
		error_label.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 16));
		error_label.setBounds(397, 206, 332, 25);
		this.add(error_label);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		textField.setBounds(397, 158, 332, 31);
		textField.setBackground(new Color(0xffd09f));
		this.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		textArea.setBounds(266, 241, 586, 215);
		textArea.setBackground(new Color(0xffd09f));
		textArea.setEditable(false);
		this.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Cr\u00E9er votre mot de passe");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(428, 509, 262, 33);
		this.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(0xffd09f));
		passwordField.setBounds(397, 552, 332, 34);
		this.add(passwordField);
		
		JButton btnNewButton = new JButton("S'incrire");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(0x673824));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				        if(ajouteJoueur()) { 
				        	JOptionPane.showMessageDialog(null,"Vous venez de vous inscrire sur MegaJeux \n\t Bienvenue parmi nous !");
				        }else {
				        		error_label.setText("Ce Pseudo est d�ja pris !");
				        	  System.out.println("Ce pseudo est d�ja pris !");
				        }
			}
		});
		btnNewButton.setBounds(467, 633, 185, 46);
		this.add(btnNewButton);
		
		JButton connexion = new JButton("Connexion");
		connexion.setForeground(new Color(255, 255, 255));
		connexion.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		connexion.setBackground(new Color(0x673824));
		connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.switchToConnexionPanel();
			}
		});
		connexion.setBounds(479, 724, 161, 38);
		add(connexion);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenue sur MegaJeux");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(398, 30, 351, 31);
		add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(388, 10, 341, 12);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(388, 71, 341, 12);
		add(separator_1);
	}
	
	public boolean ajouteJoueur() { 
		String pseudo;
		String mdp;
		pseudo = textField.getText();
		mdp = passwordField.getText();
		
		if(model.getJoueurs().containsKey(pseudo)) { // conflict dans le cas o� le pseudo est d�ja pris
			  generateValidePseudos(pseudo);
			  return false;
		}
		else {// inscription d'un nouveau joueur 
				Joueur nouveau_inscrit = new Joueur(pseudo,mdp);
				model.getJoueurs().put(pseudo, nouveau_inscrit);
				Utils.serialize("joueurs",model.getJoueurs());
		}
		
		System.out.println("nouveau joueur inscrit ! pseudo: "+pseudo+ "mot de passe : "+mdp);
		return true;
	}
	
	
	private void generateValidePseudos(String pseudoEntree){ 
		 int total_alea_gen = 5;
		 int max = 10000;
		 String pseudos[] = new String[total_alea_gen];
		 for(int i=0;i<pseudos.length;i++) { 
			 	pseudos[i] = pseudoEntree+(int)(Math.random() * max)+"";
		 }
		 
		 String proposition ="";
		 
		 for(int i=0;i<pseudos.length;i++) {
			   proposition = proposition + pseudos[i] + "\n";
		 }
		 textArea.setText(proposition);
	}
	

	public void paint(Graphics g) { 
		g.drawImage(image, 0, 0, image.getWidth() / 4, image.getHeight() / 4,null);
		g.setColor(new Color(0,0,0,80));
		g.fillRect(388, 10, 341, 61); //bienvenue sur megaJeux (fond)
		g.fillRect(412, 90, 300, 31);// pseudo (fond)
		g.fillRect(428, 509, 262, 33);// mdp (fond)
		super.paintComponents(g);
	}
	
	
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JLabel getError_label() {
		return error_label;
	}
	public void setError_label(JLabel error_label) {
		this.error_label = error_label;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
}
