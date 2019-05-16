package vue;

import modele.BuilderPlateau;

public class vuePlateau {
	
	private int [][] notreMap;	
	private BuilderPlateau bp = new BuilderPlateau();
	private String url = "Ressources/Maps/mapTest.csv";
	
	public int[][] getMap() {
		this.bp.lireFichier(this.url);
		this.notreMap = bp.getPlateau();
		return this.notreMap;
	}

}
