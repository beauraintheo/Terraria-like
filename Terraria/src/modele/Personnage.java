package modele;

public class Personnage {

	private Coordonnees position;
	private Plateau plateau;

	public Personnage(Plateau plateau, int coordX, int coordY) {
		this.position = new Coordonnees(coordX, coordY);
		this.plateau = plateau;
	}

	public Coordonnees getPosition() {
		return this.position;
	}

	public Coordonnees getPositionPlus(int vitesseX, int vitesseY) {
		Coordonnees coorProvisoires;

		coorProvisoires = new Coordonnees(this.position.getCoordX() + vitesseX, this.position.getCoordY() + vitesseY);
		return coorProvisoires;
	}

	public void setPosition(int x, int y) {
		this.position.setCoordX(x);
		this.position.setCoordY(y);
	}

	public void deplacementGauche() {
		if (this.getPositionPlus(-16, 0).getCoordX() >= 0) {
			if (this.plateau.casePlateau(this.getPositionPlus(-16, 0)) == 0) {
				this.setPosition(this.getPosition().getCoordX() - 16, this.getPosition().getCoordY());
			}
		}

		else {
			this.getPosition().setCoord(this.getPosition().getCoordX(), this.getPosition().getCoordY());
		}
	}

	public void deplacementDroite() {
		if (this.getPositionPlus(16, 0).getCoordX() <= 1248) {
			if (this.plateau.casePlateau(this.getPositionPlus(16, 0)) == 0) {
				this.setPosition(this.getPosition().getCoordX() + 16,
						this.getPosition().getCoordY());
			}
		}

		else {
			this.getPosition().setCoord(this.getPosition().getCoordX(),
					this.getPosition().getCoordY());
		}
	}

	public void sauter() {
		if (this.getPositionPlus(0, -16).getCoordY() >= 0) {
			if (this.plateau.casePlateau(this.getPositionPlus(0, -16)) == 0) {
				this.setPosition(this.getPosition().getCoordX(),
						this.getPosition().getCoordY() - 16);
			}
		}
	}

	public void tomber() {
		if (this.getPositionPlus(0, 16).getCoordY() <= 1248) {
			if (this.plateau.casePlateau(this.getPositionPlus(0, 16)) == 0) {
				this.setPosition(this.getPosition().getCoordX(),
						this.getPosition().getCoordY() + 16);
			}
		}
	}

	public boolean detectionVide() {
		if (this.plateau.casePlateau(this.getPositionPlus(0, 16)) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean detectionBlocPlein(int vitesseX, int vitesseY) {
		if (this.plateau.casePlateau(getPositionPlus(vitesseX, vitesseY)) == 0) {
			return false;
		}
		return true;
	}
}