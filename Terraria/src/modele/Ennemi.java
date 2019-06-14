/*
 * Ennemi.java
 * Cette classe nous permet un ennemi avec une position sur le plateau, des pv, des points d'attaques et un id
 */

package modele;

import java.util.ArrayList;

import vue.ObservateurEnnemi;

public class Ennemi extends Personnage {

	private int id;
	private int pv;
	private int ptAtq;
	private ArrayList<ObservateurEnnemi> obs;
	private int direction;

	public Ennemi(Plateau plateau, int coordX, int coordY, int id, int pv, int ptAtq) {
		super(plateau, coordX, coordY, pv, ptAtq);
		this.id = id;
		this.pv = pv;
		this.ptAtq = ptAtq;
		this.obs = new ArrayList<ObservateurEnnemi>();
		this.direction = 0;   // 0 = gauche   - 1 = droite
	}

	public int getId() {
		return this.id;
	}

	public int getDirection() {
		return this.direction;
	}

	public void changerDirection() {
		if (this.direction == 0)
			this.direction = 1;
		else
			this.direction = 0;
	}

	public String toString() {
		return "Pv : " + this.pv + "\nPtAtq : " + this.ptAtq;
	}

	public void perdrePV(int degats) {
		super.perdrePV(degats);
		avertirObs(super.getPV(), super.getPosition());
	}
	
	public void addObs(ObservateurEnnemi nouvelleVueEnnemi) {
		this.obs.add(nouvelleVueEnnemi);
	}

	private void avertirObs(int newValuePV, Coordonnees coord) {
		for (int i = 0; i < this.obs.size(); i++) {
			this.obs.get(i).valueChanged(newValuePV, coord);
		}
	}
}