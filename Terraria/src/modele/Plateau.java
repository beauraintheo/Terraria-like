package modele;

import java.util.ArrayList;

import vue.ObservateurPlateau;

//TODO: Le plateau dit si un bloc est cassable ou non, sans se soucier de la position du joueur.

public class Plateau {

	private int[][] plateau;
	private ArrayList<ObservateurPlateau> obs;
	private BuilderPlateau bp = new BuilderPlateau();
	private String url = "Ressources/Maps/mapMainSansScroll.csv";

	public Plateau() {
		this.obs = new ArrayList<ObservateurPlateau>();
		this.bp.lireFichier(this.url);
		this.plateau = bp.getPlateau();
	}

	public int getCasePlateau(Coordonnees coo) {
		return this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16];
	}

	public void casserBloc(Coordonnees coord) {
		if (blocCassable(this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16])) {
			this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16] = -1;
			avertirObs(-1, coord);

			Coordonnees coordBlocDessus = new Coordonnees(coord.getCoordX(), (coord.getCoordY() - 16));

			if (blocAvecSupport(this.plateau[coordBlocDessus.getCoordY() / 16][coordBlocDessus.getCoordX() / 16])) {
				this.plateau[coordBlocDessus.getCoordY() / 16][coordBlocDessus.getCoordX() / 16] = -1;
				avertirObs(-1, coordBlocDessus);
			}

			if (blocLiquide(this.plateau[coordBlocDessus.getCoordY() / 16][coordBlocDessus.getCoordX() / 16])) {
				while (this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16] == -1) {
					this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16] = 16;
					avertirObs(16, coord);
					coord.setCoordY(coord.getCoordY() + 16);
				}
			}

		}
	}

	public void poserBloc(Coordonnees coord, int idBloc) {
		if (blocPosable(this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16])) {
			this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16] = idBloc;
			avertirObs(idBloc, coord);
		}

		Coordonnees coordBlocDessous = new Coordonnees(coord.getCoordX(), (coord.getCoordY() + 16));

		if (blocHerbe(this.plateau[coordBlocDessous.getCoordY() / 16][coordBlocDessous.getCoordX() / 16]) && idBloc != 17) {
			this.plateau[coordBlocDessous.getCoordY() / 16][coordBlocDessous.getCoordX() / 16] = 1;
			avertirObs(1, coordBlocDessous);
		}

		Coordonnees coordBlocDessus = new Coordonnees(coord.getCoordX(), (coord.getCoordY() - 16));

		if (blocVide(this.plateau[coordBlocDessus.getCoordY() / 16][coordBlocDessus.getCoordX() / 16]) && idBloc == 1) {
			this.plateau[coord.getCoordY() / 16][coord.getCoordX() / 16] = 0;
			avertirObs(0, coord);
		}
	}

	public boolean blocPosable(int casePlateau) {
		switch (casePlateau) {
		case -1: // Air
			return true;
		case 11: // Herbe-Courte
			return true;
		case 12: // Herbe-Longue
			return true;
		case 13: // Champignon
			return true;
		case 15: // Eau-Sommet
			return true;
		case 16: // Eau
			return true;
		case 17: // Fleur
			return true;
		default:
			return false;
		}
	}

	public boolean blocCassable(int casePlateau) {
		if (casePlateau != -1)
			return true;
		return false;
	}

	public boolean blocLiquide(int casePlateau) {
		if (casePlateau == 15 || casePlateau == 16) {
			return true;
		}
		return false;
	}

	public boolean blocHerbe(int casePlateau) {
		if (casePlateau == 0)
			return true;
		return false;
	}
	
	public boolean blocVide(Coordonnees coord) {
		if(getCasePlateau(coord) == -1)
			return true;
		return false;
	}

	public boolean blocVide(int casePlateau) {
		if (casePlateau == -1)
			return true;
		return false;
	}

	public boolean blocAvecSupport(int casePlateau) {
		switch (casePlateau) {
		case 11: // Herbe-Courte
			return true;
		case 12: // Herbe-Longue
			return true;
		case 13: // Champignon
			return true;
		case 17: // Fleur
			return true;
		default:
			return false;
		}
	}

	public boolean blocTraversable(int casePlateau) {
		switch (casePlateau) {
		case -1: // Air (Vide)
			return true;
		case 10: // Feuille
			return true;
		case 11: // Herbe-Courte
			return true;
		case 12: // Herbe-Longue
			return true;
		case 13: // Champignon
			return true;
		case 15: // Eau-Sommet
			return true;
		case 16: // Eau
			return true;
		case 17: // Fleur
			return true;
		default:
			return false;
		}
	}

	public void addObs(ObservateurPlateau newVuePlateau) {
		this.obs.add(newVuePlateau);
	}

	private void avertirObs(int newValue, Coordonnees coord) {
		for (int i = 0; i < this.obs.size(); i++) {
			this.obs.get(i).valueChanged(newValue, coord);
		}
	}

	public int[][] getPlateau() {
		return this.plateau;
	}

	public int getTaillePlateauX() {
		return this.plateau.length;
	}

	public int getTaillePlateauY() {
		return this.plateau[0].length;
	}
}