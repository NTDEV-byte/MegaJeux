package mega.main.integrateur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mega.main.Application;
import mega.main.InterfaceJeu;
import mega.system.Joueur;
import mega.system.Partie.Etat;

public class IntegrateurMegaJeu implements Serializable{
		
	private transient Application application;
	private transient Joueur joueur1,joueur2;
	private String pseudoj1,pseudoj2;
	private Date date;
	private Etat etatPartie;
	private JButton btn_sauvegarder,btn_quitter;
	private JLabel lblSavedStatus;

	
		public IntegrateurMegaJeu(Application application,Joueur j1,Joueur j2) { 
			this.joueur1 = j1; 
			this.joueur2 = j2; 
			this.application = application;
			this.date = new Date();
			this.etatPartie = Etat.DEFAULT;
			this.pseudoj1 = j1.getPseudonyme();
			this.pseudoj2 = j2.getPseudonyme();
		}
		
			
	public JPanel getUI() { 
		JPanel uiPanel = new JPanel();
		uiPanel.setPreferredSize(new Dimension(200,800));
		uiPanel.setBackground(new Color(51,51,51));
		uiPanel.setLayout(null);
		
	
		
		btn_sauvegarder = new JButton("Sauvegarder");
		btn_sauvegarder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		System.out.println("Height:" +application.getHeight());
		btn_sauvegarder.setBounds(42,  540, 118, 21);
		btn_sauvegarder.setBackground(Color.orange);
		btn_sauvegarder.setForeground(Color.white);
		btn_sauvegarder.setOpaque(true);
		btn_sauvegarder.setContentAreaFilled(true);
		btn_sauvegarder.setBorderPainted(false);
		uiPanel.add(btn_sauvegarder);
		
	    btn_quitter = new JButton("Quitter");
		btn_quitter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_quitter.setBounds(55, 620, 85, 21);
		btn_quitter.setBackground(Color.orange);
		btn_quitter.setForeground(Color.white);
		btn_quitter.setOpaque(true);
		btn_quitter.setContentAreaFilled(true);
		btn_quitter.setBorderPainted(false);
		
		uiPanel.add(btn_quitter);
		
		JLabel lbl_title = new JLabel("TicTacToe");
		lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_title.setBounds(66, 10, 85, 13);
		uiPanel.add(lbl_title);
		
		JLabel lbl_j1 = new JLabel(pseudoj1);
		lbl_j1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_j1.setBounds(10, 63, 62, 13);
		uiPanel.add(lbl_j1);
		
		JLabel lbl_vs = new JLabel("VS");
		lbl_vs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_vs.setBounds(91, 63, 35, 13);
		uiPanel.add(lbl_vs);
		
		JLabel lbl_j2 = new JLabel(pseudoj2);
		lbl_j2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_j2.setBounds(136, 63, 54, 13);
		uiPanel.add(lbl_j2);
		
		lblSavedStatus = new JLabel("");
		lblSavedStatus.setBounds(50, 310, 180, 30);
		uiPanel.add(lblSavedStatus);
		
		return uiPanel;
	}
	
	public void setWinnerAndLoserOrDraw(Etat e1,Etat e2) { 
		joueur1.getPartieEncours().setEtat(e1);
		joueur1.enregisterStatPartieCourante();
		joueur2.getPartieEncours().setEtat(e2);
		joueur2.enregisterStatPartieCourante();
	}
	
	public void sauvegardePartieAction(InterfaceJeu jeu) {
		  if(joueur1.canSave()) { 
			   System.out.println("sauvegarder !!!");
			   lblSavedStatus.setBounds(50, 310, 180, 30);
			   lblSavedStatus.setText("Partie Sauvegardé !");
			   lblSavedStatus.setForeground(Color.green);
			   joueur1.sauvegardePartieJeuEnCours(jeu);
		   }
		   else {
			   lblSavedStatus.setBounds(25, 310, 180, 30);
			   lblSavedStatus.setText("Tout les slots sont remplis  !");
			   lblSavedStatus.setForeground(Color.red);
			   System.out.println("tout les slot sont remplies !");
		   }
	}
	
	public void quittePartieAction() { 
		 application.getVuePrincipale().updateUIMV();
		 application.switchToMainPanel();
		 System.out.println("Quitter !!!");
	}

	public void clearLBLSavedStatus() { 
		lblSavedStatus.setText("");
	}
	
	public void clearAllLbls() { 
		lblSavedStatus.setText("");
	}
	
	public void setActionForSauvegarde(ActionListener listener) { 
		btn_sauvegarder.addActionListener(listener);
		
	}
	
	public void setActionForQuitte(ActionListener listener) {
		btn_quitter.addActionListener(listener);
	}
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

	public String getPseudoj1() {
		return pseudoj1;
	}

	public String getPseudoj2() {
		return pseudoj2;
	}

	public Date getDate() {
		return date;
	}

	public Etat getEtatPartie() {
		return etatPartie;
	}

	public void setEtatPartie(Etat etat) {
		this.etatPartie = etat;
	}

	public JButton getBtn_sauvegarder() {
		return btn_sauvegarder;
	}

	public JButton getBtn_quitter() {
		return btn_quitter;
	}
	public JLabel getLblSavedStatus() {
		return lblSavedStatus;
	}

	public void setLblSavedStatus(JLabel lblSavedStatus) {
		this.lblSavedStatus = lblSavedStatus;
	}
	
	
	

	/*
	
	public void sauvegardePartieAuto(InterfaceJeu jeu) { 
		   if(j1.canSave() && !j1.fullSlotsSave()) 
		   	{ 
			   System.out.println("sauvegarder !!!");
			   lblSavedStatus.setText("Partie Sauvegardé !");
			   lblSavedStatus.setForeground(Color.green);
			   j1.sauvegardePartieJeuEnCours(jeu);
		   	}
		   
		   else 
			   if(j1.fullSlotsSave()) { 
				   j1.sauvegardePartieJeuEnCours(jeu,MainVue.index_partie);
			   }
		   
		   else {
			   lblSavedStatus.setText("Tout les slots sont remplis  !");
			   lblSavedStatus.setForeground(Color.red);
			   System.out.println("tout les slot sont remplies !");
		   }
	}*/
	
	
}


