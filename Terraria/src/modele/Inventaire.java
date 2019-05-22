package modele;

import java.util.ArrayList;

public class Inventaire {
	
	private ArrayList<Objet> objets;
	private int taille;
	private int nbItems;
	
	public Inventaire() {
		objets = new ArrayList<Objet>();
		this.taille = 20;
	}
	
	public void ajouter(Objet objet) {
		for (int i = 0; i < objets.size() || i <= taille; i++) {
			if (objets.get(i).equals(objet)) {
				
			}
		}
		this.objets.add(objet);
	}
}
