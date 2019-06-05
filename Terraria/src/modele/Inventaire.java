package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

	private ObservableList<Item> items;
	private int tailleMax;
	private boolean enExemplaire;

	public Inventaire() {
		this.items = FXCollections.observableArrayList();
	}

	public void ajouterItem(Item item) {
		if (regarderExemplaire(item)) {
			System.out.println("+1");
		}

		else {
			items.add(item);
		}
		inventairePlein();
	}

	public void enleverItem(Item item) {
		// vérifier si l'item existe, si il est en exemplaire et le retirer de l'index
		// ou il se trouve
	}

	public void deplacerItem(int index, Item item) {
		// verifier si l'item existe dans la liste
		items.set(index, item);
	}

	public boolean inventairePlein() {
		return true;
	}

	public boolean regarderExemplaire(Item item) {
		for (int i = 0; i < items.size() && i < tailleMax; i++) {
			if (items.get(i).getNom().equals(item.getNom()) && items.get(i).getTailleStacking() != 1) {
				this.items.get(i).ajouterExemplaire();
				return true;
			}
		}
		return false;
	}

}
