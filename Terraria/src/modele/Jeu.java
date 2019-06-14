//GrandChefMH

package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import vue.ObservateurPlateau;
import modele.Item;

public class Jeu {

	private boolean musiqueGrotte;
	private Plateau plateau;
	private Joueur joueur;
	private Inventaire inventaire;
	private Item item;
	private Playlist playlist;
	private ObservableList<Ennemi> ennemis;

	public Jeu() {
		this.plateau = new Plateau();
		this.joueur = new Joueur(plateau, 16, 304, 5, 1);
		this.ennemis = FXCollections.observableArrayList();
		this.inventaire = new Inventaire();
		this.item = new Item();
		this.playlist = new Playlist();
		this.musiqueGrotte = false;
		
		this.ajouterEcouteurEnnemis();
		this.ajouterNouveauEnnemi();
	}

	/* ==== Méthodes Gestion Inventaire ==== */

	public int itemChoisi(int id) {
		int value = inventaire.recupererNbExemplaire(id);
		return value;
	}

	public int ajouterBlocInventaire(Coordonnees coord) {
		if (this.joueur.peutPoserCasserBloc(coord)) {
			this.playlist.jouerMusique(1);

			if (plateau.getCasePlateau(coord) == 1 || plateau.getCasePlateau(coord) == 0) {	//Terre - Herbe (bloc)
				this.item = this.inventaire.item(1);
				this.item.ajouterExemplaireItem();
			}
			else if (plateau.getCasePlateau(coord) == 2) {	//Pierre
				this.item = this.inventaire.item(2);
				this.item.ajouterExemplaireItem();
			}
			else if (plateau.getCasePlateau(coord) == 5) {	//Bois
				this.item = this.inventaire.item(5);
				this.item.ajouterExemplaireItem();
			}
			else if (plateau.getCasePlateau(coord) == 6) {	//Charbon
				this.item = this.inventaire.item(6);
				this.item.ajouterExemplaireItem();
			}
			else if (plateau.getCasePlateau(coord) == 7) {	//Fer
				this.item = this.inventaire.item(7);
				this.item.ajouterExemplaireItem();
			}
			else if (plateau.getCasePlateau(coord) == 17) {	//Fleur magique
				this.item = this.inventaire.item(17);
				this.item.ajouterExemplaireItem();
			}
		}
		return -2;
	}

	public void retirerBlocInventaire(int idBloc) {
		this.inventaire.retirerUnExemplaire(idBloc);
	}

	public Item getItemInventaire(int position) {
		return this.inventaire.getItem(position);
	}

	public IntegerProperty nbExemplaireItemProperty(int position) {
		return getItemInventaire(position).getExemplaireProperty();
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

	public void changerIdItemJoueur(int id) {
		this.joueur.setIdItemEnMain(id);
	}

	public int getIdItemJoueur() {
		return this.joueur.getIdItem();
	}

	/* ===== Méthodes gestion Ennemis ===== */

	public void ajouterEnnemi(Ennemi e) {
		this.ennemis.add(e);
	}

	public void ajouterNouveauEnnemi() {
		this.ennemis.add(new Ennemi(this.plateau, 50, 50, 1, 5, 6));
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
			if (this.joueur.peutPoserCasserBloc(coord) && this.joueur.peutPoserBlocDessous(coord) && this.plateau.blocVide(coord)) {
				if(this.inventaire.recupererNbExemplaire(this.joueur.getIdItem()) > 0) {
					retirerBlocInventaire(this.joueur.getIdItem());
					this.plateau.poserBloc(coord, this.joueur.getIdItem());
				}
			}
		}

		else if (changement.equals("Casser")) {
			if (this.joueur.peutPoserCasserBloc(coord)) {
				this.plateau.casserBloc(coord);
			}
		}
	}

	public boolean peutEtreCasseMain(int idBloc) {
		return this.plateau.blocAvecSupport(idBloc);
	}

	public int getCasePlateau(Coordonnees coord) {
		return this.plateau.getCasePlateau(coord);
	}
}