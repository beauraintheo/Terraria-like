package modele;

public class Ennemi extends Personnage {

	private int id;

	public Ennemi(Plateau plateau, int coordX, int coordY, int id) {
		super(plateau, coordX, coordY);
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}