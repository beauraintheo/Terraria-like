package modele;

public class Joueur extends Personnage {
	
	//Booléen estDansLeau
	//Booléen ou Objet possèdePioche / possèdeEpée

	private int idItemEnMain;
	
	public Joueur(Plateau plateau, int coordX, int coordY) {
		super(plateau, coordX, coordY);
		this.idItemEnMain = -2;
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
	
	public void setIdItemEnMain(int id) {
		this.idItemEnMain = id;
	}
	
	public int getIdItem() {
		return this.idItemEnMain;
	}
}
