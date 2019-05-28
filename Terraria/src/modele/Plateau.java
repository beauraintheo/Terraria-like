/*
 * Plateau.java
 * Cette classe sert à créer notre map.
 */

package modele;

public class Plateau {
	
	private int[][] plateau;
	//private Coordonnees coord;
	private BuilderPlateau bp = new BuilderPlateau();
	private String url = "Ressources/Maps/mapTest.csv";
	
	public Plateau() {
		this.bp.lireFichier(this.url);
		this.plateau = bp.getPlateau();
	}
	
	public int casePlateau(Coordonnees coo) {
		return this.plateau[coo.getCoordY().getValue() / 16][coo.getCoordX().getValue() / 16];
	}
	
	//TODO essayer de se débarasser du getPlateau en ayant à la place les méthodes qui vont bien pour répondre aux questions qu'on a à poser au plateau.
	public int[][] getPlateau() {
		return plateau;
	}
}
