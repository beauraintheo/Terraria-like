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

	public Coordonnees getPositionPlus(int vitesseX, int vitesseY) {
		Coordonnees coorProvisoires ;

		coorProvisoires = new Coordonnees(this.position.getCoordX().getValue() + vitesseX, this.position.getCoordY().getValue() + vitesseY);
		return coorProvisoires ;
	}

	public void setPosition(int x, int y) {
		this.position.setCoordX(x);
		this.position.setCoordY(y);
	}

	public void deplacementGauche(int[][] plateau) {
		if (this.getPositionPlus(-16, 0).casePlateau(plateau) == 0) {
			this.setPosition(this.getPosition().getCoordX().getValue() - 16, this.getPosition().getCoordY().getValue());
			System.out.println(this.getPosition().casePlateau(plateau));
		}
	}

	public void deplacementDroite(int[][] plateau) {
		if (this.getPositionPlus(16, 0).casePlateau(plateau) == 0) {
			this.setPosition(this.getPosition().getCoordX().getValue() + 16, this.getPosition().getCoordY().getValue());
			System.out.println(this.getPosition().casePlateau(plateau));
		}
	}

	public void gravité(int[][] plateau) {
		while (this.getPositionPlus(0, 16).casePlateau(plateau) == 0) {
			this.setPosition(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue() + 16);
		}
	}
}
