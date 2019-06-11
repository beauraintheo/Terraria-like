package modele;

public class Joueur extends Personnage {

	public Joueur(Plateau plateau, int coordX, int coordY) {
		super(plateau, coordX, coordY);
	}

	public boolean peutPoserCasserBloc(Coordonnees coord) {
		if (coord.getCoordX() / 16 <= (this.getCoordXPosition() / 16) + 1
				&& coord.getCoordX() / 16 >= (this.getCoordXPosition() / 16) - 1
				&& coord.getCoordY() / 16 <= (this.getCoordYPosition() / 16) + 1
				&& coord.getCoordY() / 16 >= (this.getCoordYPosition() / 16) - 1) {
			return true;
		}
		return false;
	}

	public boolean peutPoserBlocDessous(Coordonnees coord) {
		if (coord.getCoordX() / 16 == this.getCoordXPosition() / 16
				&& coord.getCoordY() / 16 == this.getCoordYPosition() / 16) {
			return false;
		}
		return true;
	}
}
