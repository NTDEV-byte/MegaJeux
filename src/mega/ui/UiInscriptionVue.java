package mega.ui;

import static mega.main.Application.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.MegaJeuBD;
import mega.utils.Utils;


public class UiInscriptionVue extends JPanel{

	private JTextField tf_pseudo;
	private JPasswordField pf_creationMDP;
	private JTextArea ta_proposition_generee;
	private JLabel lbl_Pseudo_error_label;
	private MegaJeuBD model;
	private Application application;
	private Graphics gG;
	/**
	 * Create the application.
	 */
	public UiInscriptionVue(Application application) {
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
		
		JLabel lbl_creePseudo = new JLabel("Créer votre pseudonyme");
		lbl_creePseudo.setForeground(new Color(255, 255, 255));
		lbl_creePseudo.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 24));
		lbl_creePseudo.setBounds(412, 90, 300, 31);
		this.add(lbl_creePseudo);
		
		lbl_Pseudo_error_label = new JLabel("");
		lbl_Pseudo_error_label.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		lbl_Pseudo_error_label.setForeground(Color.red);
		lbl_Pseudo_error_label.setBounds(380, 206, 369, 25);
		this.add(lbl_Pseudo_error_label);
		
		JLabel lbl_information = new JLabel("");
		lbl_information.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 16));
		lbl_information.setBounds(10, 687, 459, 75);
		
		JLabel lbl_error_mdp = new JLabel("");
		lbl_error_mdp.setForeground(Color.RED);
		lbl_error_mdp.setFont(new Font("Microsoft Tai Le", Font.BOLD, 20));
		lbl_error_mdp.setBounds(397, 596, 332, 27);
		add(lbl_error_mdp);
		
		
		tf_pseudo = new JTextField();
		tf_pseudo.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		tf_pseudo.setBounds(397, 158, 332, 31);
		tf_pseudo.setBackground(new Color(0xffd09f));
		this.add(tf_pseudo);
		tf_pseudo.setColumns(10);
		
		ta_proposition_generee = new JTextArea();
		ta_proposition_generee.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		ta_proposition_generee.setBounds(266, 241, 586, 215);
		ta_proposition_generee.setBackground(new Color(0xffd09f));
		ta_proposition_generee.setEditable(false);
		this.add(ta_proposition_generee);
		
		JLabel lbl_creationMDP = new JLabel("Cr\u00E9er votre mot de passe");
		lbl_creationMDP.setForeground(new Color(255, 255, 255));
		lbl_creationMDP.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		lbl_creationMDP.setBounds(428, 509, 262, 33);
		this.add(lbl_creationMDP);
		
		
		
		pf_creationMDP = new JPasswordField();
		pf_creationMDP.setBackground(new Color(0xffd09f));
		pf_creationMDP.setBounds(397, 552, 332, 34);
		this.add(pf_creationMDP);

		
		add(lbl_information);
		JButton btn_sInscire = new JButton("S'incrire");
		btn_sInscire.setForeground(new Color(255, 255, 255));
		btn_sInscire.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		btn_sInscire.setBackground(new Color(0x673824));
		btn_sInscire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(lbl_information ,lbl_Pseudo_error_label, lbl_error_mdp);
				inscription(lbl_information,lbl_Pseudo_error_label,lbl_error_mdp);
				
			}
		});
		btn_sInscire.setBounds(467, 633, 185, 46);
		this.add(btn_sInscire);
		
		JButton btn_connexion = new JButton("Connexion");
		btn_connexion.setForeground(new Color(255, 255, 255));
		btn_connexion.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		btn_connexion.setBackground(new Color(0x673824));
		btn_connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.switchToConnexionPanel();
				reset(lbl_information,lbl_Pseudo_error_label,lbl_error_mdp);
			}
		});
		btn_connexion.setBounds(479, 724, 161, 38);
		add(btn_connexion);
		
		JLabel lbl_BienVenueMG = new JLabel("Bienvenue sur MegaJeux");
		lbl_BienVenueMG.setForeground(new Color(255, 255, 255));
		lbl_BienVenueMG.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
		lbl_BienVenueMG.setBounds(398, 30, 351, 31);
		add(lbl_BienVenueMG);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(388, 10, 341, 12);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(388, 71, 341, 12);
		add(separator_1);
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(380, 703, 369, 2);
		add(separator_2);
		
	}
	
	public boolean ajouteJoueur(JLabel lbl_Pseudo_error_label,JLabel lbl_error_mdp) { 
		String pseudo;
		String mdp;
		pseudo = tf_pseudo.getText();
		mdp = pf_creationMDP.getText();
		
		if(model.getListeJoueurs().containsKey(pseudo)) { // conflict dans le cas o� le pseudo est d�ja pris
			  generateValidePseudos(pseudo);
			  lbl_Pseudo_error_label.setText("Ce Pseudonyme est déja pris !");
			  return false;
		}
		else if(!pseudo.isEmpty() && !mdp.isEmpty()) {// ajout d'un nouveau joueur dans la liste des incrits
				Joueur nouveau_inscrit = new Joueur(pseudo,mdp);
				model.getListeJoueurs().put(pseudo, nouveau_inscrit);
				Utils.serialize("joueurs",model.getListeJoueurs()); // enregistrement
				return true;
				
		}
		else if(mdp.isEmpty()) { 
			lbl_error_mdp.setText("Mot de passe invalide !");
		}
		return false;
	//	System.out.println("nouveau joueur inscrit ! pseudo: "+pseudo+ "mot de passe : "+mdp);
	}
	
	
	private void inscription(JLabel infoLBL,JLabel lbl_Pseudo_error_label,JLabel lbl_error_mdp) { 
	     if(ajouteJoueur(lbl_Pseudo_error_label,lbl_error_mdp)) { 
	    	 infoLBL.setForeground(Color.green);
	    	 infoLBL.setText("Vous venez de vous inscrire sur MegaJeux \n\t Bienvenue parmi nous !");
	        }else {
	        	 infoLBL.setForeground(Color.red);
		    	 infoLBL.setText("Inscription echouée  !");
	        }
	}
	
	private void reset(JLabel infoLBL,JLabel lbl_Pseudo_error_label,JLabel lbl_error_mdp) { 
		infoLBL.setText("");
		lbl_error_mdp.setText("");
		lbl_Pseudo_error_label.setText("");
		ta_proposition_generee.setText("");
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
		 ta_proposition_generee.setText(proposition);
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
		return tf_pseudo;
	}

	public void setTextField(JTextField textField) {
		this.tf_pseudo = textField;
	}

	public JTextArea getTextArea() {
		return ta_proposition_generee;
	}
	public void setTextArea(JTextArea textArea) {
		this.ta_proposition_generee = textArea;
	}
	public JLabel getError_label() {
		return lbl_Pseudo_error_label;
	}
	public void setError_label(JLabel error_label) {
		this.lbl_Pseudo_error_label = error_label;
	}
	
	public JPasswordField getPasswordField() {
		return pf_creationMDP;
	}
	public void setPasswordField(JPasswordField passwordField) {
		this.pf_creationMDP = passwordField;
	}
}
