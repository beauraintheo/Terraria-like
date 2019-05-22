package modele;

public class Personnage {
	
	private int pv, ptAtk, ptDef;
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

	public int attaquer(Personnage p) {
		
		return this.ptAtk;
	}
	
	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPtAtk() {
		return ptAtk;
	}

	public void setPtAtk(int ptAtk) {
		this.ptAtk = ptAtk;
	}

	public int getPtDef() {
		return ptDef;
	}

	public void setPtDef(int ptDef) {
		this.ptDef = ptDef;
	}
}
