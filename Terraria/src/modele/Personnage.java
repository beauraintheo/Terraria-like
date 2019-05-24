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
		if (this.getPositionPlus(- 16, 0).getCoordX().getValue() != - 16) {
			if (this.getPositionPlus(-16, 0).casePlateau(plateau) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue() - 16, this.getPosition().getCoordY().getValue());
				System.out.println(this.getPosition().casePlateau(plateau));
			}
		}
		
		else {
			this.getPosition().setCoord(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue());
		}
	}
		
	public void deplacementDroite(int[][] plateau) {
		if (this.getPositionPlus(16, 0).getCoordX().getValue() != 1264) {
			if (this.getPositionPlus(16, 0).casePlateau(plateau) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue() + 16, this.getPosition().getCoordY().getValue());
				System.out.println(this.getPosition().casePlateau(plateau));
			}
		}
		
		else {
			this.getPosition().setCoord(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue());
		}
	}

	public void sauter(int[][] plateau) {	
		if (this.getPositionPlus(0, - 16).getCoordY().getValue() != - 16) {
			if (this.getPositionPlus(0, - 16).casePlateau(plateau) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue() - 16);
			}
		}
	}

	public void gravité(int[][] plateau) {
		if (this.getPositionPlus(0, 16).getCoordY().getValue() != 1264) {
			if (this.getPositionPlus(0, 16).casePlateau(plateau) == 0) {
				this.setPosition(this.getPosition().getCoordX().getValue(), this.getPosition().getCoordY().getValue() + 16);
			}
		}
	}
	
	public boolean détectionSol(int[][] plateau) {
		if (this.getPositionPlus(0, 16).casePlateau(plateau) == 0) {
			return true;
		}
		return false;
	}
}
