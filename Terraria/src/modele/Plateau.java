/*
 * Plateau.java
 * Cette classe sert à créer notre map.
 */

package modele;

public class Plateau {
	
	private int[][] plateau;
	private BuilderPlateau bp = new BuilderPlateau();
	private String url = "Ressources/Maps/mapTest.csv";
	
	public Plateau() {
		this.bp.lireFichier(this.url);
		this.plateau = bp.getPlateau();
	}
	
	public int casePlateau(Coordonnees coo) {
		return this.plateau[coo.getCoordY() / 16][coo.getCoordX() / 16];
	}
	
	public int[][] getPlateau() {
		return plateau;
	}
}