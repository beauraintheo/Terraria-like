/*
 * Cette classe contient tous les items du jeu et leurs donne un id.
 */

package modele;

public class Item {

	private int tailleStacking; // Le tailleStacking est le nombre d'exemplaire max que peut avoir un stack
								// d'objet.
	private int exemplaire; // Le nombre d'exemplaire
	private int id;
	private String nom;

	public Item(int stacking, int id, String nom) {
		this.tailleStacking = stacking;
		this.id = id;
		this.nom = nom;
		this.exemplaire = 1;
	}

	public int getID() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public int getTailleStacking() {
		return this.tailleStacking;
	}

	public int nbExemplaire() {
		return this.exemplaire;
	}

	public void ajouterExemplaire() {
		this.exemplaire++;
	}

	public void utiliser() {
		this.exemplaire--;
	}
}