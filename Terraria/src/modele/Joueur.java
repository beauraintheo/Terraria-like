package modele;

public class Joueur extends Personnage {
	
	//Booléen estDansLeau

	private int idItemEnMain;
	private int pv;
	private int ptAtk;
	
	public Joueur(Plateau plateau, int coordX, int coordY, int pv, int ptAtk) {
		super(plateau, coordX, coordY, pv, ptAtk);
		this.idItemEnMain = -2;
		this.pv = 5;
		this.ptAtk = 1;
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
	
	public String toString() {
		return "Pv : " + this.pv + "\nPtAtk : " + this.ptAtk;
	}
}
