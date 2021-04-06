package mega.system.integrateur;

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
import mega.system.InterfaceJeu;
import mega.system.Joueur;
import mega.system.Partie.Etat;
import mega.system.Partie.Jeu;
import mega.utils.Utils;

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
	//	System.out.println("Height:" +application.getHeight());
		btn_sauvegarder.setBounds(42,  540, 118, 21);
		btn_sauvegarder.setBackground(Color.orange);
		btn_sauvegarder.setForeground(Color.white);
		btn_sauvegarder.setOpaque(true);
		btn_sauvegarder.setContentAreaFilled(true);
		btn_sauvegarder.setBorderPainted(false);
		uiPanel.add(btn_sauvegarder);
		
	    btn_quitter = new JButton("Quitter");
		btn_quitter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_quitter.setBounds(58, 620, 85, 21);
		btn_quitter.setBackground(Color.orange);
		btn_quitter.setForeground(Color.white);
		btn_quitter.setOpaque(true);
		btn_quitter.setContentAreaFilled(true);
		btn_quitter.setBorderPainted(false);
		
		uiPanel.add(btn_quitter);
		
		JLabel lbl_title = new JLabel(application.getVuePrincipale().getJeu_selectionner().toString());
		lbl_title.setForeground(Color.green);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_title.setBounds(55, 10, 130, 20);
		uiPanel.add(lbl_title);
		
		JLabel lbl_j1 = new JLabel(pseudoj1);
		lbl_j1.setForeground(Color.green);
		lbl_j1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_j1.setBounds(10, 63, 62, 13);
		uiPanel.add(lbl_j1);
		
		JLabel lbl_vs = new JLabel("VS");
		lbl_vs.setForeground(Color.green);
		lbl_vs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_vs.setBounds(91, 63, 35, 13);
		uiPanel.add(lbl_vs);
		
		JLabel lbl_j2 = new JLabel(pseudoj2);
		lbl_j2.setForeground(Color.green);
		lbl_j2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_j2.setBounds(136, 63, 54, 13);
		uiPanel.add(lbl_j2);
		
		adapteLabelsUI(lbl_j1,lbl_j2);
		lblSavedStatus = new JLabel("");
		lblSavedStatus.setBounds(50, 310, 180, 30);
		uiPanel.add(lblSavedStatus);
		
		return uiPanel;
	}
	
	public void setWinnerAndLoserOrDraw(Etat e1,Etat e2) { 
		joueur1.getPartieEncours().setEtat(e1);
		joueur1.enregistreProgression();
		joueur2.getPartieEncours().setEtat(e2);
		joueur2.enregistreProgression();
	}

	public void sauvegardePartieAction(InterfaceJeu jeu) {
		if(application.getVuePrincipale().getJeu_selectionner() == Jeu.TICTACTOE) {
			 if(joueur1.canSaveTicTacToe()) { 
				   lblSavedStatus.setBounds(50, 310, 180, 30);
				   lblSavedStatus.setText("Partie Sauvegardé !");
				   lblSavedStatus.setForeground(Color.green);
				   joueur1.sauvegardePartieJeuEnCours(jeu);
			   }
			   else {
				   lblSavedStatus.setBounds(25, 310, 180, 30);
				   lblSavedStatus.setText("Tout les slots sont remplis  !");
				   lblSavedStatus.setForeground(Color.red);
			   }
		}
		else
			if(application.getVuePrincipale().getJeu_selectionner() == Jeu.CHESS){
				if(joueur1.canSaveChess()) {
					 lblSavedStatus.setBounds(50, 310, 180, 30);
					   lblSavedStatus.setText("Partie Sauvegardé !");
					   lblSavedStatus.setForeground(Color.green);
					   joueur1.sauvegardePartieJeuEnCours(jeu);
				}
		   }
		   else {
			   lblSavedStatus.setBounds(25, 310, 180, 30);
			   lblSavedStatus.setText("Tout les slots sont remplis  !");
			   lblSavedStatus.setForeground(Color.red);
		   }
		}
	
	public void quittePartieAction(InterfaceJeu jeu) { 
		 if(joueur1.canSaveTicTacToe()) {
			   joueur1.sauvegardePartieJeuEnCours(jeu);
		 }
		 if(joueur1.canSaveChess()) {
			   joueur1.sauvegardePartieJeuEnCours(jeu);
		 }
		 joueur1.supprimeAutoPartiesTerminee();
		 application.switchToMainPanel();
	}
	
	private void adapteLabelsUI(JLabel p1,JLabel p2) { 
		if(application.getVuePrincipale().getJeu_selectionner() == Jeu.TICTACTOE) {
				 p1.setForeground(Color.red);
				 p2.setForeground(Color.blue);
		}
		else {
			p1.setForeground(Color.white);
			p2.setForeground(Color.black);
		}
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
	
	
	public void setInformationPourPartieReprise(Application application,Joueur j1,Joueur j2) { 
			this.application = application;
			this.joueur1 = j1;
			this.joueur2 = j2;
	}
	
	
	public String toString() { 
		return "Integrateur Partie SVGRD : \n"+"J1: "+joueur1.getPseudonyme()+" J2: "+joueur2.getPseudonyme()+" Date: "+Utils.dateToString(null, date)+" Etat: "+etatPartie.toString();
	}
	
	
}


