package mega.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.LanceurJeu;
import mega.system.MegaJeuBD;
import mega.system.Partie.Jeu;
import mega.utils.Utils;

public class UiListeDesJoueurs extends JPanel {

	/**
	 */
	private MegaJeuBD model;
	private BufferedImage fond;
	private Joueur adversaire;
	private boolean lancer;
	private UiMain main;
	private Application application;
	private String pseudo_adv = null;
	/**
	 * Create the frame.
	 */
	public UiListeDesJoueurs(Application app) {
		this.application = app;
		this.model = app.getModel();
		this.main = app.getVuePrincipale();
		this.application.setListesJoueurs(this);
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 500, 500);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lb_adversiare = new JLabel("Choisissez votre adversaire");
		lb_adversiare.setForeground(Color.white);
		lb_adversiare.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 26));
		lb_adversiare.setBounds(397, 25, 350, 50);
		this.add(lb_adversiare);
		
		JSeparator separator_lb_adversiare = new JSeparator();
		separator_lb_adversiare.setForeground(new Color(255, 255, 255));
		separator_lb_adversiare.setBounds(267, 80, 540, 50);
		add(separator_lb_adversiare);
		/*
		 * On r�cup�re les joueurs qui sont pr�sents dans la class MegaJeuModel a l'aide 
		 * de la m�thode chargerJoueurs()
		 */
		String pseudos[] = chargerJoueurs();
		JList<String> list_adversaires = new JList<String>(pseudos);
		list_adversaires.setBounds(397, 100, 280, 330);
		list_adversaires.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 22));
		list_adversaires.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
						pseudo_adv = list_adversaires.getSelectedValue();
				}
		} );

		this.add(list_adversaires);
		
		
		JButton bt_valider = new JButton("Jouer");
		bt_valider.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		bt_valider.setBounds(452, 460, 170, 20);
		bt_valider.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				if(valideAdversaire()) 
				 jouer();
			}
		});
		
		this.add(bt_valider);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(267, 600, 540, 50);
		add(separator);
		
		JLabel lb_retour = new JLabel("Retourner au Menu Principale");
		lb_retour.setForeground(Color.white);
		lb_retour.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
		lb_retour.setBounds(405, 620, 280, 50);
		this.add(lb_retour);
		
		JButton bt_retour = new JButton("Retour");
		bt_retour.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		bt_retour.setBounds(452, 670, 170, 20);
		bt_retour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				/*
				 * switch to main panel
				 */
				 application.switchToMainPanel();
			}
		});
		
		this.add(bt_retour);
		
		if(main.getJeu_selectionner() == Jeu.TICTACTOE) { 
				fond = Utils.loadIMG("/ttt.jpg");
		}
		else {
			fond = Utils.loadIMG("/chess.jpg");
		}
		
	}
	
	
	public void paint(Graphics g) {
		if(main.getJeu_selectionner() == Jeu.CHESS) {
			g.drawImage(fond, -430, 0, fond.getWidth()  , fond.getHeight()  , null);
		}
		else {
			g.drawImage(fond, -380, -300, fond.getWidth()  , fond.getHeight()  , null);
		}
		super.paintComponents(g);
		repaint();
	}
	
	
	private String[] chargerJoueurs() {
		Collection<Joueur> adversaire = (Collection<Joueur>) model.getListeJoueurs().values();
		String joueurs_pseudos[] = new String[adversaire.size()];
		int numero =0;
		for(Joueur j : adversaire) {  
			if(!j.getPseudonyme().equalsIgnoreCase(main.getJoueur1().getPseudonyme())){
				joueurs_pseudos[numero++] = j.getPseudonyme();
			}
		}
		return joueurs_pseudos;
	}
	
	private boolean valideAdversaire() { 
		adversaire = choisirAdversaire();
		if(adversaire != null) { 
			main.setJoueur2(adversaire);
			main.getLanceur().setJoueur2(adversaire);
		//	System.out.println(adversaire.toString());
			return true;
		}
		return false;
	}
	
	private Joueur choisirAdversaire() { 
		HashMap<String,Joueur> joueurs = model.getListeJoueurs();
		if(joueurs != null) {
			if(joueurs.containsKey(pseudo_adv)) { 
				return joueurs.get(pseudo_adv);
			}
			else {
			//	System.out.println("Le joueur: "+pseudo_adv+" n'est pas pr�sent dans la liste !");
			}
		}
		return null;
	}
	
	
	private void jouer() { 
		if(pseudo_adv != null) {
			 LanceurJeu launcher  = main.getLanceur();
			 launcher.lanceJeu();
			}
	}

	public boolean canLaunch() { 
		 return lancer;
	}
	public Joueur getAdversaireSelectionner() {
		return adversaire;
	}
	
}
