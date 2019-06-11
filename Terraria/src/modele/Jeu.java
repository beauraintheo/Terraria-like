//GrandChefMH

package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import vue.ObservateurPlateau;

public class Jeu {

	private Plateau plateau;
	private Joueur joueur;
	private Inventaire inventaire;
	private ObservableList<Ennemi> ennemis;

	public Jeu() {
		this.plateau = new Plateau();
		this.joueur = new Joueur(plateau, 16, 320);
		this.ennemis = FXCollections.observableArrayList();
		this.inventaire = new Inventaire();
		this.ajouterEcouteurEnnemis();
		this.ajouterNouveauEnnemi();
	}

	/* ==== Méthodes Gestion Inventaire ==== */

	public int itemChoisi(Item item) {
		if (inventaire.recupererIdItem(item) == 0) {
			return 0;
		}
		return item.getExemplaire();
	}

	/* ===== ===== */

	public void unTour(int timer) {
		if (timer % 4 == 0) {
			if (this.videSousJoueur()) {
				this.avertirDeplacementJoueur("Bas");

				if (!this.videSousJoueur()) {
					this.joueur.setOrientation("Spawn");
				}
			}
		}

		if (timer % 8 == 0) {
			for (int i = 0; i < ennemis.size(); i++) {
				if (this.ennemis.get(i).getId() == 1)
					if (this.ennemis.get(i).detectionBlocPlein(-16, 0)) {
						// this.vueEnnemi.orientationDroite();
					}
				this.ennemis.get(i).deplacementGauche();

				if (this.ennemis.get(i).detectionBlocPlein(16, 0)) {
					// this.vueEnnemi.orientationGauche();
				}
				this.ennemis.get(i).deplacementDroite();
			}
		}

	}

	/* ===== Méthodes gestion Joueur ===== */

	public void avertirDeplacementJoueur(String direction) {
		if (direction.equals("Gauche")) {
			this.joueur.deplacementGauche();
		}

		else if (direction.equals("Droite")) {
			this.joueur.deplacementDroite();
		}

		else if (direction.equals("Haut")) {
			this.joueur.sauter();
		}

		else if (direction.equals("Bas")) {
			this.joueur.tomber();
		}

	}

	public boolean videSousJoueur() {
		return this.joueur.detectionVide();
	}

	public IntegerProperty coordonneesJoueurX() {
		return this.joueur.getPosition().coordXProperty();
	}

	public IntegerProperty coordonneesJoueurY() {
		return this.joueur.getPosition().coordYProperty();
	}

	public StringProperty orientationJoueur() {
		return this.joueur.orientationProperty();
	}

	/* ===== Méthodes gestion Ennemis ===== */

	public void ajouterEnnemi(Ennemi e) {
		this.ennemis.add(e);
	}

	public void ajouterNouveauEnnemi() {
		this.ennemis.add(new Ennemi(this.plateau, 50, 50, 1));
	}

	public int nbEnnemis() {
		return this.ennemis.size();
	}

	public IntegerProperty coordonneesEnnemiX(Ennemi e) {
		return e.getPosition().coordXProperty();
	}

	public IntegerProperty coordonneesEnnemiY(Ennemi e) {
		return e.getPosition().coordYProperty();
	}

	public void ajouterEcouteurEnnemis() {
		this.ennemis.addListener(new ListChangeListener<Ennemi>() {

			@Override
			public void onChanged(ListChangeListener.Change<? extends Ennemi> change) {

				while (change.next()) {
					if (change.wasAdded()) {
						System.out.println("Ajout d'un ennemi");
					}
				}

			}

		});
	}

	/* ===== Méthodes gestion Plateau ===== */

	public int[][] getTabPlateau() {
		return this.plateau.getPlateau();
	}

	public void ajouterObsPlateau(ObservateurPlateau newVuePlateau) {
		this.plateau.addObs(newVuePlateau);
	}

	public void avertirChangementPlateau(String changement, Coordonnees coord) {
		if (changement.equals("Poser")) {
			if (this.joueur.peutPoserCasserBloc(coord) && this.joueur.peutPoserBlocDessous(coord)) {
				this.plateau.poserBloc(coord);
			}
		}

		else if (changement.equals("Casser")) {
			if (this.joueur.peutPoserCasserBloc(coord)) {
				this.plateau.casserBloc(coord);
			}
		}
	}

}