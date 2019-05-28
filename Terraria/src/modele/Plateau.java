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
	
	//TODO essayer de se débarasser du getPlateau en ayant à la place les méthodes qui vont bien pour répondre aux questions qu'on a à poser au plateau.
	public int[][] getPlateau() {
		return plateau;
	}
}
