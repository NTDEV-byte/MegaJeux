package mega.vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.MegaJeuModel;
import mega.utils.Utils;

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
		this.model = application.getModel();
		this.application = application;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(0, 10, 900, 600);
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cr\u00E9er votre pseudo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(345, 26, 247, 31);
		this.add(lblNewLabel);
		
		error_label = new JLabel("");
		error_label.setForeground(Color.RED);
		error_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		error_label.setBounds(334, 126, 200, 25);
		this.add(error_label);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(334, 83, 195, 19);
		this.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
		textArea.setBounds(296, 169, 262, 161);
		this.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Cr\u00E9er votre mot de passe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(316, 361, 262, 33);
		this.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(316, 417, 241, 34);
		this.add(passwordField);
		
		JButton btnNewButton = new JButton("s'incrire");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				        if(ajouteJoueur()) { 
				        	JOptionPane.showMessageDialog(null,"Vous venez de vous inscrire sur MegaJeux \n\t Bienvenue parmi nous !");
				        }else {
				        		error_label.setText("Ce Pseudo est déja pris !");
				        	  System.out.println("Ce pseudo est déja pris !");
				        }
			}
		});
		btnNewButton.setBounds(371, 481, 158, 31);
		this.add(btnNewButton);
		
		JButton connexion = new JButton("Connexion");
		connexion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.switchToConnexionPanel();
			}
		});
		connexion.setBounds(10, 530, 109, 21);
		add(connexion);
	}
	
	public boolean ajouteJoueur() { 
		String pseudo;
		String mdp;
		pseudo = textField.getText();
		mdp = passwordField.getText();
		
		if(model.getJoueurs().containsKey(pseudo)) { // conflict dans le cas où le pseudo est déja pris
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
