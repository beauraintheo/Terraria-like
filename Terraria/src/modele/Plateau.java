package modele;

import java.util.ArrayList;

import vue.ObservateurPlateau;

public class Plateau {

	private int[][] plateau;
	private ArrayList<ObservateurPlateau> obs;
	private BuilderPlateau bp = new BuilderPlateau();
	private String url = "Ressources/Maps/mapTest.csv";
	
	public Plateau() {
		this.obs = new ArrayList<ObservateurPlateau>();
		this.bp.lireFichier(this.url);
		this.plateau = bp.getPlateau();
	}
	
	public int casePlateau(Coordonnees coo) {
		return this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16];
	}
	
	public void casserBloc(Coordonnees coo) {
		if (this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16] != 0) {
			this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16] = 0;
			avertirObs(0, coo);
		}
	}
	
	public void poserBloc(Coordonnees coo) {
		if (this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16] == 0) {
			this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16] = 1;
			avertirObs(1, coo);
		}
	}
	
	public void addObs(ObservateurPlateau newVuePlateau) {
		this.obs.add(newVuePlateau);
	}
	
	private void avertirObs(int newValue, Coordonnees coo) {
		for (int i = 0; i < this.obs.size() ; i++) {
			this.obs.get(i).valueChanged(newValue, coo);
		}
	}
	
	public int[][] getPlateau() {
		return this.plateau;
	}
}