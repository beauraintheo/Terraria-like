/*
 * Cette classe contient tous les items du jeu et leurs donne un id.
 */

package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item {

	private int exemplaireMax;
	private IntegerProperty exemplaire;
	private int id;
	private String nom;

	/*
	 * ID des blocks : 1 -> Terre 2 -> Pierre 5 -> Bois 6 -> Charbon 7 -> Fer 17 ->
	 * Fleur magique 9 -> Epee 4 -> Pioche
	 */
	
	public Item() {
		this.id = -2;				//-2 car 0 correspond à l'id du bloc d'herbe dans le plateau et -1 correspond à l'id du bloc vide dans le plateau
		this.nom = null;
		this.exemplaire = new SimpleIntegerProperty();
		this.exemplaireMax = 10000;
	}

	public Item(int id, String nom, int exemplaire, int exemplaireMax) {
		this.id = id;
		this.nom = nom;
		this.exemplaire = new SimpleIntegerProperty(exemplaire);
		this.exemplaireMax = exemplaireMax;
	}

	public void ajouterExemplaireItem() {
		if (tailleItem())
			this.exemplaire.set(this.exemplaire.get() + 1);
	}

	public void retirerExemplaireItem() {
		if (tailleItem())
			this.exemplaire.set(this.exemplaire.get() - 1);
	}

	public boolean tailleItem() {
		if (exemplaire.getValue() < 0 || exemplaire.getValue() >= 64)
			return false;
		return true;
	}

	public int getId() {
		return this.id;
	}

	public IntegerProperty getExemplaireProperty() {
		return this.exemplaire;
	}

	public int getExemplaire() {
		return this.exemplaire.getValue();
	}

	public String toString() {
		return "ID : " + this.id + "\nNom : " + this.nom + "\nTaille : " + this.getExemplaire() + "\nTaille max : "
				+ this.exemplaireMax;
	}
}