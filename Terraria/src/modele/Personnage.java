package modele;

public class Personnage {

	private Coordonnees position;
	private Plateau plateau;
	
	public Personnage(Plateau plateau) {
		this.position = new Coordonnees();
		this.plateau = plateau;
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

	
	public void deplacementGauche() {
		if (this.getPositionPlus(- 16, 0).getCoordX().getValue() >= 0) {
			if (this.plateau.casePlateau(this.getPositionPlus(-16, 0)) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue() - 16, this.getPosition().getCoordY().getValue());
			}
		}
		
		else {
			this.getPosition().setCoord(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue());
		}
	}
		
	public void deplacementDroite() {
		if (this.getPositionPlus(16, 0).getCoordX().getValue() <= 1248) {
			if (this.plateau.casePlateau(this.getPositionPlus(16, 0)) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue() + 16, this.getPosition().getCoordY().getValue());
			}
		}
		
		else {
			this.getPosition().setCoord(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue());
		}
	}

	public void sauter() {	
		if (this.getPositionPlus(0, - 16).getCoordY().getValue() >= 0) {
			if (this.plateau.casePlateau(this.getPositionPlus(0, -16)) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue() - 16);
			}
		}
	}

	public void tomber() {
		if (this.getPositionPlus(0, 16).getCoordY().getValue() <= 1248) {
			if (this.plateau.casePlateau(this.getPositionPlus(0, 16)) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue() + 16);
			}
		}
	}
	
	public boolean détectionSol() {
		//if (this.getPositionPlus(0, 16).casePlateau(plateau) == 0) {
		if (this.plateau.casePlateau(this.getPositionPlus(0, 16)) == 0) {
			return true;
		}
		return false;
	}
	
	/*public boolean détectionPlafond(int[][] plateau) {
		if (this.getPositionPlus(0, - 16).casePlateau(plateau) == 0) {
			return true;
		}
		return false;
	}*/
	
	/*public void tombe(int[][] plateau) {
		if (this.getPositionPlus(0, 16).casePlateau(plateau) == 0) {
			this.setPosition(this.getPosition().getCoordX().getValue());
		}
	}*/
}
