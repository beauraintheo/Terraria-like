//GrandChefMH

package modele;

import java.util.ArrayList;

public class Jeu {

	private Plateau plateau;
	private Joueur joueur;
	private ArrayList<Ennemi> ennemis;
	
	public Jeu() {
		this.plateau = new Plateau();
		this.joueur = new Joueur(plateau, 400, 448);
		this.ennemis = new ArrayList<Ennemi>();
		this.ennemis.add(new Ennemi(plateau, 448, 448));
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public Plateau getPlateau() {
		return this.plateau;
	}
	
	public Ennemi getEnnemi() {
		return this.ennemis.get(0);
	}

	public void ajouterEnnemi(Ennemi e) {
		this.ennemis.add(e);
	}
}