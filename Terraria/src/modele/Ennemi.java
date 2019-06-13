package modele;

public class Ennemi extends Personnage {

	private int id;
	private int pv;
	private int ptAtk;

	public Ennemi(Plateau plateau, int coordX, int coordY, int id, int pv, int ptAtk) {
		super(plateau, coordX, coordY, pv, ptAtk);
		this.id = id;
		this.pv = pv;
		this.ptAtk = ptAtk;
	}

	public int getId() {
		return this.id;
	}
	
	public String toString() {
		return "Pv : " + this.pv + "\nPtAtk : " + this.ptAtk;
	}
}