/*
 * GrandChefMH.java
 * Cette classe sert à regrouper toutes les méthodes du modèle et construit tous les éléments nécessaires au fonctionnement du jeu
 * On aiment nos profs
 */

package modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import vue.ObservateurPlateau;
import modele.Item;
import test.HorsDuTerrainException;

public class GrandChefMH {

	private Plateau plateau;
	private Joueur joueur;
	private Inventaire inventaire;
	private Item item;
	private Playlist playlist;
	private ArrayList<Ennemi> ennemis;

	public GrandChefMH(int numeroMap, int coordXJoueur, int coordYJoueur) throws HorsDuTerrainException {
		this.choixMap(numeroMap);
		this.joueur = new Joueur(plateau, coordXJoueur, coordYJoueur, 5, 1);
		
		if (!HorsDuTerrainException.verifCoordJoueur(joueur, plateau)) {
			throw new HorsDuTerrainException(joueur, plateau);
		}
		
		this.ennemis = new ArrayList<Ennemi>();
		this.inventaire = new Inventaire();
		this.item = new Item();
		this.playlist = new Playlist();

		this.ajouterEnnemis();
	}

	/* ==== Création, choix de différentes maps ==== */

	private void choixMap(int numeroMap) {

		switch (numeroMap) {
		case 1: // MapTest
			this.plateau = new Plateau(10, 10, "Ressources/Maps/testMap.csv");
			break;
		case 2: // MapMain
			this.plateau = new Plateau(80, 80, "Ressources/Maps/mapMainSansScroll.csv");
			break;
		default: // mapMain
			this.plateau = new Plateau(80, 80, "Ressources/Maps/mapMainSansScroll.csv");
		}
	}

	/* ==== Méthodes Gestion Inventaire ==== */

	public int itemChoisi(int id) {
		int value = inventaire.recupererNbExemplaire(id);
		return value;
	}

	public int ajouterBlocInventaire(Coordonnees coord) {
		if (this.joueur.peutPoserCasserBloc(coord)) {
			this.playlist.jouerMusique(1);

			if (plateau.getCasePlateau(coord) == 1 || plateau.getCasePlateau(coord) == 0) { // Terre - Herbe (bloc)
				this.item = this.inventaire.item(1);
				this.item.ajouterExemplaireItem();
			} else if (plateau.getCasePlateau(coord) == 2) { // Pierre
				this.item = this.inventaire.item(2);
				this.item.ajouterExemplaireItem();
			} else if (plateau.getCasePlateau(coord) == 5) { // Bois
				this.item = this.inventaire.item(5);
				this.item.ajouterExemplaireItem();
			} else if (plateau.getCasePlateau(coord) == 6) { // Charbon
				this.item = this.inventaire.item(6);
				this.item.ajouterExemplaireItem();
			} else if (plateau.getCasePlateau(coord) == 7) { // Fer
				this.item = this.inventaire.item(7);
				this.item.ajouterExemplaireItem();
			} else if (plateau.getCasePlateau(coord) == 17) { // Fleur magique
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

	/* ===== Méthodes pour la gameloop ===== */

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
				if (this.ennemis.get(i).getId() == 1) {

					if (!this.ennemis.get(i).detectionBlocPlein(-16, 0) && this.ennemis.get(i).getDirection() == 0) {
						this.ennemis.get(i).deplacementGauche();
						this.ennemis.get(i).setOrientation("Gauche");
						if (this.ennemis.get(i).getCasePositionPlus(-16, 0) != -1) {
							this.ennemis.get(i).changerDirection();
						}
					}

					else if (!this.ennemis.get(i).detectionBlocPlein(16, 0)
							&& this.ennemis.get(i).getDirection() == 1) {
						this.ennemis.get(i).deplacementDroite();
						this.ennemis.get(i).setOrientation("Droite");
						if (this.ennemis.get(i).getCasePositionPlus(16, 0) != -1) {
							this.ennemis.get(i).changerDirection();

						}
					}
				}
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

	public IntegerProperty coordonneesJoueurXProperty() {
		return this.joueur.getPosition().coordXProperty();
	}

	public IntegerProperty coordonneesJoueurYProperty() {
		return this.joueur.getPosition().coordYProperty();
	}

	public int getCoordJoueurX() {
		return this.joueur.getCoordXPosition();
	}

	public int getCoordJoueurY() {
		return this.joueur.getCoordYPosition();
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

	public void ajouterEnnemis() {
		this.ennemis.add(new MetaKnight(this.plateau, 896, 432, 1, 5, 6));
		this.ennemis.add(new MetaKnight(this.plateau, 720, 768, 1, 5, 6));
		this.ennemis.add(new MetaKnight(this.plateau, 1072, 816, 1, 5, 6));
	}

	public int nbEnnemis() {
		return this.ennemis.size();
	}

	public Ennemi getEnnemi(int i) {
		return this.ennemis.get(i);
	}

	public IntegerProperty coordonneesEnnemiX(Ennemi e) {
		return e.getPosition().coordXProperty();
	}

	public IntegerProperty coordonneesEnnemiY(Ennemi e) {
		return e.getPosition().coordYProperty();
	}

	/* ===== Méthodes gestion Plateau ===== */

	public Plateau getPlateau() {
		return this.plateau;
	}

	public void ajouterObsPlateau(ObservateurPlateau newVuePlateau) {
		this.plateau.addObs(newVuePlateau);
	}
	
	public void avertirChangementPlateau(String changement, Coordonnees coord) {
		if (changement.equals("Poser")) {
			if (this.joueur.peutPoserCasserBloc(coord) && this.joueur.peutPoserBlocDessous(coord)
					&& this.plateau.blocVide(coord)) {
				if (this.inventaire.recupererNbExemplaire(this.joueur.getIdItem()) > 0) {
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