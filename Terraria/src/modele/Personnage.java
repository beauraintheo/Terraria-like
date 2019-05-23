package modele;

public class Personnage {

	private Coordonnees position;

	public Personnage() {
		this.position = new Coordonnees();
	}

	public Personnage(int x, int y) {
		this.position = new Coordonnees(x, y);
	}

	public Coordonnees getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position.setCoordX(x);
		this.position.setCoordY(y);
	}

	public void deplacementGauche(int[][] plateau) {
		if (this.getPosition().casePlateau(plateau) == 0) {
			this.setPosition(this.getPosition().getCoordX().getValue() - 16, this.getPosition().getCoordY().getValue());
			System.out.println(this.getPosition().casePlateau(plateau));
		}
	}

	public void deplacementDroite(int[][] plateau) {
		if (this.getPosition().casePlateau(plateau) == 0) {
			
			this.setPosition(this.getPosition().getCoordX().getValue() + 16, this.getPosition().getCoordY().getValue());
			System.out.println(this.getPosition().casePlateau(plateau));
		}
	}
}
