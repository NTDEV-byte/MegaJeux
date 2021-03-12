package tictactoe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
//***************************************
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.Partie.Etat;

public class TicTacToe extends JPanel implements ActionListener{

	Random random = new Random();
//	JFrame frame = new JFrame("TicTacToe");
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel uiPanel;
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	Application app;
	boolean player1_turn;
	Joueur j1,j2;

	public TicTacToe(Application app,Joueur j1,Joueur j2){
		this.j1 = j1;
		this.j2 = j2;
		this.app = app;
		
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
		
		
		
		
		firstTurn();
	}

	
	private void uiPanel() { 
		uiPanel = new JPanel();
		uiPanel.setPreferredSize(new Dimension(200,800));
		uiPanel.setBackground(Color.YELLOW);
		uiPanel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Charger");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(42, 327, 118, 21);
		uiPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sauvegarder");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(42, 286, 118, 21);
		uiPanel.add(btnNewButton_2);
		
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				 app.switchToMainPanel();
			}
		});
		quitter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quitter.setBounds(55, 364, 85, 21);
		uiPanel.add(quitter);
		
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
		//check X win conditions
		if(
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				) {
			xWins(0,1,2);
			
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				) {
			xWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				) {
			xWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(2,4,6);
		}
		//check O win conditions
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				) {
			oWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				) {
			oWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				) {
			oWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(2,4,6);
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
		j1.enregistrerPartieCourante();
		j2.getPartieEncours().setEtat(Etat.PERDUE);
		j2.enregistrerPartieCourante();
		
	}
	public void oWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
		j1.getPartieEncours().setEtat(Etat.PERDUE);
		j1.enregistrerPartieCourante();
		j2.getPartieEncours().setEtat(Etat.GAGNEE);
		j2.enregistrerPartieCourante();
	}
	
}


//***************************************

