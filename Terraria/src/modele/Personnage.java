package modele;

public class Personnage {
	
	private int pv, ptAtk, ptDef;
	private Inventaire inv;
	private Coordonnees position;
	
	public Personnage() {
		this.position = new Coordonnees();
	}
	
	public Personnage(double x, double y) {
		this.position = new Coordonnees(x,y);
	}
	
	public Coordonnees getPosition() {
		return this.position;
	}
	
	public void setPosition(double x, double y) {
		this.position.setCoordX(x);
		this.position.setCoordY(y);
	}
}
