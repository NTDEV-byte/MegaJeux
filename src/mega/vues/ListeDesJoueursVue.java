package mega.vues;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mega.main.Application;
import mega.system.Joueur;
import mega.system.MegaJeuModel;

public class ListeDesJoueursVue extends JPanel {

	/**
	 */
	private MegaJeuModel model;
	private Joueur adversaire;
	private boolean lancer;
	private MainVue main;
	private Application application;
	private String pseudo_adv = null;
	/**
	 * Create the frame.
	 */
	public ListeDesJoueursVue(Application app) {
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
		lb_adversiare.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_adversiare.setBounds(98, 24, 279, 47);
		this.add(lb_adversiare);
		
		/*
		 * On récupère les joueurs qui sont présents dans la class MegaJeuModel a l'aide 
		 * de la méthode chargerJoueurs()
		 */
		String pseudos[] = chargerJoueurs();
		JList<String>list_adversaires = new JList<String>(pseudos);
		list_adversaires.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
						pseudo_adv = list_adversaires.getSelectedValue();
				}
		} );

		list_adversaires.setBounds(83, 119, 294, 207);
		this.add(list_adversaires);
		
		
		JButton bt_valider = new JButton("Jouer");
		bt_valider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_valider.setBounds(145, 373, 177, 21);
		bt_valider.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) { 
				if(valideAdversaire()) 
				 jouer();
			}
		});
		
		this.add(bt_valider);
	}
	
	
	private String[] chargerJoueurs() {
		Collection<Joueur> adversaire = (Collection<Joueur>) model.getJoueurs().values();
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
			System.out.println(adversaire.toString());
			return true;
		}
		return false;
	}
	
	private Joueur choisirAdversaire() { 
		HashMap<String,Joueur> joueurs = model.getJoueurs();
		if(joueurs != null) {
			if(joueurs.containsKey(pseudo_adv)) { 
				return joueurs.get(pseudo_adv);
			}
			else {
				System.out.println("Le joueur: "+pseudo_adv+" n'est pas présent dans la liste !");
			}
		}
		return null;
	}
	
	private void jouer() { 
		if(pseudo_adv != null) {
			 main.lancerJeu();
			}
	}
	
	public boolean canLaunch() { 
		 return lancer;
	}

	public Joueur getAdversaire() {
		return adversaire;
	}
	
}
