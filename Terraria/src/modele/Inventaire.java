package modele;

import java.util.ArrayList;

public class Inventaire {

	private ArrayList<Item> items;
	
	public Inventaire() {
		items = new ArrayList<Item>();
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
	
	public int recupererIdItem(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == item.getId())
				return item.getId();
		}
		return 0;
	}
	
	public void afficherItems() {
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i));
		}
	}
}