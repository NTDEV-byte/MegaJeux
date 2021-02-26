import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MegaJeuModel {
		
		private Connexion connexion;
		private Map<String,Joueur> joueurs;
			
				public MegaJeuModel() { 
					chargementBaseDonnee();
					connexion = new Connexion(this);
					showPlayers();
				}
				
				
				private void chargementBaseDonnee() {
					File file = new File(Utils.PATH+"/joueurs");
					if(file.exists()) { 
						Object o = Utils.deserialize("joueurs");
						if(o instanceof HashMap) { 
							joueurs = (HashMap)(o);
						}
					}
					else {
						joueurs = new HashMap<String,Joueur>();
					}
				}
				
				
				
				
				
				private void showPlayers() {
					System.out.println("Liste des joueurs: ");
					Collection<Joueur> liste = joueurs.values();
					int numero = 1;
					for(Joueur j : liste) {  
						System.out.println("numero: "+numero+" "+j.toString());
						numero++;
					}
				}
				
				public Map<String, Joueur> getJoueurs() {
					return joueurs;
				}

				public void setJoueurs(Map<String, Joueur> joueurs) {
					this.joueurs = joueurs;
				}

}