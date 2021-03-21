package mega.utils;

import java.util.Comparator;

import mega.system.Joueur;

public class TrieuseJoueur implements Comparator<Joueur>{

	@Override
	public int compare(Joueur o1, Joueur o2) {
		if(o1.calculeScore() < o2.calculeScore()) {
			return 1;
		}
		else 
			if(o2.calculeScore() < o1.calculeScore()) {
				return -1;
			}
		return 0;
	}

	
}