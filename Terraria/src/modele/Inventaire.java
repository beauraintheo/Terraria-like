/*
 * Inventaire.java
 * Cette classe déclare une liste d'items qui pourront être utilisés
 */

package modele;

import java.util.ArrayList;

public class Inventaire {

	private ArrayList<Item> items;

	public Inventaire() {
		items = new ArrayList<Item>();

		this.ajouterItem(new Item());
		this.ajouterItem(new Item());
		this.ajouterItem(new BlocTerre(16));
		this.ajouterItem(new BlocPierre(0));
		this.ajouterItem(new BlocBois(16));
		this.ajouterItem(new BlocCharbon(0));
		this.ajouterItem(new BlocFer(0));
		this.ajouterItem(new BlocFleur(0));
	}

	public void ajouterItem(Item item) {
		this.items.add(item);
	}

	public void retirerItem(Item item) {
		this.items.remove(item);
	}

	public int recupererNbExemplaire(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == id)
				return items.get(i).getExemplaire();
		}
		return 0;
	}

	public void retirerUnExemplaire(int id) {
		boolean itemRetire = false;
		int i = 0;

		while (!itemRetire) {
			if (this.items.get(i).getId() == id) {
				this.items.get(i).retirerExemplaireItem();
				itemRetire = true;
			}
			i++;
		}
		i = 0;
	}

	public void afficherItems() {
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i));
		}
	}

	public Item getItem(int position) {
		return this.items.get(position);
	}

	public Item item(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == id)
				return items.get(i);
		}
		return null;
	}
}