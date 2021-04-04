package mega.jeux.tictactoe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
//***************************************
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mega.main.Application;
import mega.system.InterfaceJeu;
import mega.system.Joueur;
import mega.system.Partie.Etat;
import mega.system.integrateur.IntegrateurMegaJeu;
import mega.system.integrateur.QuitteListener;
import mega.system.integrateur.SauvegardeListener;

public class TicTacToe extends JPanel implements ActionListener,Serializable,InterfaceJeu{
	
    
	public static int parties = 0;
	Random random = new Random();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons;
	boolean player1_turn;
	
	/*
	 * MODIFICATION
	 */
	private IntegrateurMegaJeu integrateur;
	private int id;
	

	public TicTacToe(Application app,Joueur j1,Joueur j2){
		integrateur = new IntegrateurMegaJeu(app,j1,j2);
		buttons = new JButton[9];
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		this.id = parties;
		parties++;
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

		/*
		 * integrateur modification
		 */
		this.add(integrateur.getUI(),BorderLayout.EAST);
		integrateur.setActionForSauvegarde(new SauvegardeListener(this,integrateur));
		integrateur.setActionForQuitte(new QuitteListener(this,integrateur));
		
		firstTurn();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
				){
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
				//modif
				integrateur.setWinnerAndLoserOrDraw(Etat.EGALITE, Etat.EGALITE);
				integrateur.setEtatPartie(Etat.EGALITE);
			}
		}
		if(win) { 
			//modif
			integrateur.setEtatPartie(Etat.GAGNEE);
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
		integrateur.setWinnerAndLoserOrDraw(Etat.GAGNEE, Etat.PERDUE);
	
	}
	
	public void oWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
		integrateur.setWinnerAndLoserOrDraw(Etat.PERDUE, Etat.GAGNEE);
		//modif
	}


private void logTerminal() { 
	for(int i=0;i<3;i++) {
	for(int j=0;j<3;j++) { 
		System.out.println("x = "+j+" y= "+i+"  "+buttons[j + i * 3].getText());
		}
	}
}

public IntegrateurMegaJeu getIntegrateur() {
	return integrateur;
}

public void setIntegrateur(IntegrateurMegaJeu integrateur) {
	this.integrateur = integrateur;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

}



//***************************************

