package tictactoe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
//***************************************
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mega.main.Application;
import mega.main.InterfaceJeu;
import mega.system.Joueur;
import mega.system.Partie.Etat;

public class TicTacToe extends JPanel implements ActionListener,Serializable,InterfaceJeu{
	
	public static int TOTAL_PARTIE_TTT = 0;
	public static String PartieTTTSaveName = "parties/ttt/partie";

	Random random = new Random();
//	JFrame frame = new JFrame("TicTacToe");
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel uiPanel;
	JLabel textfield = new JLabel();
	JButton[] buttons;
	boolean player1_turn;
	String pseudoj1,pseudoj2;
	Date date;
	JButton btn_sauvegarder,btn_quitter;
	Joueur j1,j2;
	JLabel lblSavedStatus;
	Etat etat;
	transient Application app;

	public TicTacToe(Application app,Joueur j1,Joueur j2){
		this.j1 = j1;
		this.j2 = j2;
		this.pseudoj1 = j1.getPseudonyme();
		this.pseudoj2 = j2.getPseudonyme();
		this.app = app;
		this.date = new Date();
		this.etat = Etat.DEFAULT;
		buttons = new JButton[9];
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		for(int i=0;i<9;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		title_panel.add(textfield);
		
		this.setLayout(new BorderLayout());
		this.add(title_panel,BorderLayout.NORTH);
		this.add(button_panel);
		
		
		uiPanel();
		
	//	frame.add(title_panel,BorderLayout.NORTH);
	//	frame.add(button_panel);
		lblSavedStatus = new JLabel("");
		uiPanel.add(lblSavedStatus);
		firstTurn();
		
		
		TOTAL_PARTIE_TTT++;
	}

	
	public void uiPanel() { 
		uiPanel = new JPanel();
		uiPanel.setPreferredSize(new Dimension(200,800));
		uiPanel.setBackground(Color.YELLOW);
		uiPanel.setLayout(null);
		
		btn_sauvegarder = new JButton("Sauvegarder");
		btn_sauvegarder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_sauvegarder.addActionListener(new SauvegardeButton());
		/*
		 * new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				
			}
		});
		 */
		btn_sauvegarder.setBounds(42, 286, 118, 21);
		uiPanel.add(btn_sauvegarder);
		
	    btn_quitter = new JButton("Quitter");
		btn_quitter.addActionListener(new QuitteButton());
		btn_quitter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_quitter.setBounds(55, 364, 85, 21);
		uiPanel.add(btn_quitter);
		
		JLabel lbl_title = new JLabel("TicTacToe");
		lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_title.setBounds(66, 10, 85, 13);
		uiPanel.add(lbl_title);
		
		JLabel lbl_j1 = new JLabel("Joueur1");
		lbl_j1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_j1.setBounds(10, 63, 62, 13);
		uiPanel.add(lbl_j1);
		
		JLabel lbl_vs = new JLabel("VS");
		lbl_vs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_vs.setBounds(91, 63, 35, 13);
		uiPanel.add(lbl_vs);
		
		JLabel lbl_j2 = new JLabel("Joueur2");
		lbl_j2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_j2.setBounds(136, 63, 54, 13);
		uiPanel.add(lbl_j2);
		
		
		this.add(uiPanel,BorderLayout.EAST);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		lblSavedStatus.setText("");
		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("O turn");
						check();
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X turn");
						check();
					}
				}
			}			
		}
	}
	
	
	private boolean draw() { 
		for(int i=0;i<buttons.length;i++) { 
			 if(buttons[i].getText().equals("")) {
				 return false;
			 }
		}
		return true;
	}
	
	public void firstTurn() {
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			player1_turn=true;
			textfield.setText("X turn");
	}
	
	public void check() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) { 
				System.out.println("x = "+j+" y= "+i+"  "+buttons[j + i * 3].getText());
			}
		}
		boolean win = false;
		//check X win conditions
		if(
				(buttons[0].getText().equalsIgnoreCase("X")) &&
				(buttons[1].getText().equalsIgnoreCase("X")) &&
				(buttons[2].getText().equalsIgnoreCase("X"))
				) {
			
			xWins(0,1,2);
			win = true;
			
		}
		if(
				(buttons[3].getText().equalsIgnoreCase("X")) &&
				(buttons[4].getText().equalsIgnoreCase("X")) &&
				(buttons[5].getText().equalsIgnoreCase("X"))
				) {
			xWins(3,4,5);
			win = true;
		}
		if(
				(buttons[6].getText().equalsIgnoreCase("X")) &&
				(buttons[7].getText().equalsIgnoreCase("X")) &&
				(buttons[8].getText().equalsIgnoreCase("X"))
				) {
			xWins(6,7,8);
			win = true;
		}
		if(
				(buttons[0].getText().equalsIgnoreCase("X")) &&
				(buttons[3].getText().equalsIgnoreCase("X")) &&
				(buttons[6].getText().equalsIgnoreCase("X"))
				) {
			xWins(0,3,6);
			win = true;
		}
		if(
				(buttons[1].getText().equalsIgnoreCase("X")) &&
				(buttons[4].getText().equalsIgnoreCase("X")) &&
				(buttons[7].getText().equalsIgnoreCase("X"))
				) {
			xWins(1,4,7);
			win = true;
		}
		if(
				(buttons[2].getText().equalsIgnoreCase("X")) &&
				(buttons[5].getText().equalsIgnoreCase("X")) &&
				(buttons[8].getText().equalsIgnoreCase("X"))
				) {
			xWins(2,5,8);
			win = true;
		}
		if(
				(buttons[0].getText().equalsIgnoreCase("X")) &&
				(buttons[4].getText().equalsIgnoreCase("X")) &&
				(buttons[8].getText().equalsIgnoreCase("X"))
				) {
			xWins(0,4,8);
			win = true;
		}
		if(
				(buttons[2].getText().equalsIgnoreCase("X")) &&
				(buttons[4].getText().equalsIgnoreCase("X")) &&
				(buttons[6].getText().equalsIgnoreCase("X"))
				) {
			xWins(2,4,6);
			win = true;
		}
		//check O win conditions
		if(
				(buttons[0].getText().equalsIgnoreCase("O")) &&
				(buttons[1].getText().equalsIgnoreCase("O")) &&
				(buttons[2].getText().equalsIgnoreCase("O"))
				) {
			oWins(0,1,2);
			win = true;
		}
		if(
				(buttons[3].getText().equalsIgnoreCase("O")) &&
				(buttons[4].getText().equalsIgnoreCase("O")) &&
				(buttons[5].getText().equalsIgnoreCase("O"))
				) {
			oWins(3,4,5);
			win = true;
		}
		if(
				(buttons[6].getText().equalsIgnoreCase("O")) &&
				(buttons[7].getText().equalsIgnoreCase("O")) &&
				(buttons[8].getText().equalsIgnoreCase("O"))
				) {
			oWins(6,7,8);
			win = true;
		}
		if(
				(buttons[0].getText().equalsIgnoreCase("O")) &&
				(buttons[3].getText().equalsIgnoreCase("O")) &&
				(buttons[6].getText().equalsIgnoreCase("O"))
				) {
			oWins(0,3,6);
			win = true;
		}
		if(
				(buttons[1].getText().equalsIgnoreCase("O")) &&
				(buttons[4].getText().equalsIgnoreCase("O")) &&
				(buttons[7].getText().equalsIgnoreCase("O"))
				) {
			oWins(1,4,7);
			win = true;
		}
		if(
				(buttons[2].getText().equalsIgnoreCase("O")) &&
				(buttons[5].getText().equalsIgnoreCase("O")) &&
				(buttons[8].getText().equalsIgnoreCase("O"))
				) {
			oWins(2,5,8);
			win = true;
		}
		if(
				(buttons[0].getText().equalsIgnoreCase("O")) &&
				(buttons[4].getText().equalsIgnoreCase("O")) &&
				(buttons[8].getText().equalsIgnoreCase("O"))
				) {
			oWins(0,4,8);
			win = true;
		}
		if(
				(buttons[2].getText().equalsIgnoreCase("O")) &&
				(buttons[4].getText().equalsIgnoreCase("O")) &&
				(buttons[6].getText().equalsIgnoreCase("O"))
				) {
			oWins(2,4,6);
			win = true;
		}
		
		else {
			if(draw() && !win) { 
				j1.getPartieEncours().setEtat(Etat.EGALITE);
				j1.enregisterStatPartieCourante();
				j2.getPartieEncours().setEtat(Etat.EGALITE);
				j2.enregisterStatPartieCourante();
				etat = Etat.GAGNEE;
			}
		}
		
		if(win) { 
			etat = Etat.GAGNEE;
		}
		
	}
	
	public void xWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
		j1.getPartieEncours().setEtat(Etat.GAGNEE);
		j1.enregisterStatPartieCourante();
		j2.getPartieEncours().setEtat(Etat.PERDUE);
		j2.enregisterStatPartieCourante();
		
		
	}
	public void oWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
		j2.getPartieEncours().setEtat(Etat.GAGNEE);
		j2.enregisterStatPartieCourante();
		j1.getPartieEncours().setEtat(Etat.PERDUE);
		j1.enregisterStatPartieCourante();
		System.out.println("Check O !!!!");
	}

	
public String getJoueur1() { 
	return pseudoj1;
}

public String getJoueur2() { 
	return pseudoj2;
}


public Application getApp() {
	return app;
}


public void setApp(Application app) {
	this.app = app;
}


public Date getDate() {
	return date;
}


public void setDate(Date date) {
	this.date = date;
}


private class SauvegardeButton implements ActionListener,Serializable{

	@Override
	public void actionPerformed(ActionEvent e) {
		   if(j1.canSave()) { 
			   System.out.println("sauvegarder !!!");
			   lblSavedStatus.setBounds(50, 310, 180, 30);
			   lblSavedStatus.setText("Partie Sauvegardé !");
			   lblSavedStatus.setForeground(Color.green);
			   j1.sauvegardePartieJeuEnCours(TicTacToe.this);
		   }
		   else {
			   lblSavedStatus.setBounds(25, 310, 180, 30);
			   lblSavedStatus.setText("Tout les slots sont remplis  !");
			   lblSavedStatus.setForeground(Color.red);
			   System.out.println("tout les slot sont remplies !");
		   }
	} 
}

private class QuitteButton implements ActionListener,Serializable{
	@Override
	public void actionPerformed(ActionEvent e) {
		 System.out.println("Quitter !!!");
		 app.getVuePrincipale().updateUIMV();
		 app.switchToMainPanel();
	} 
}

public Etat getEtat() {
	return etat;
}


}



//***************************************

