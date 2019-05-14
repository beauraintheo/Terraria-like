package vue;

import modele.BuilderPlateau;

public class vuePlateau {
	
	BuilderPlateau bp = new BuilderPlateau();
	String url="src/vue/maps/map1.csv";
	int [][] notremap;
	
	public void afficherMap() {
		this.bp.lireFichier(this.url);
		this.notremap=bp.getPlateau();
		
	}
	

}
