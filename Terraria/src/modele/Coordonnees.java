/*
 * Coordonnees.java
 * Cette méthode est utilisée pour avoir les coordonnées de notre personnage sur la map. 
 * Elle est utilisée pour faire les déplacements de celui-ci;
 */

package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Coordonnees {
	
	private IntegerProperty coordX;
	private IntegerProperty coordY;
	
	public Coordonnees() {
		this.coordX = new SimpleIntegerProperty(400);
		this.coordY = new SimpleIntegerProperty(448);
	}
	
	public Coordonnees(int x, int y) {
		this.coordX = new SimpleIntegerProperty(x);
		this.coordY = new SimpleIntegerProperty(y);
	}
	
	public void afficheCoordonnees() {
		System.out.println("x : " + this.getCoordX().getValue() + " y : " + this.getCoordY().getValue());
	}
	

	
	public void setCoord(int x, int y) {
		this.coordX.setValue(x);
		this.coordY.setValue(y);
	}
	
	public void setCoordX(int x) {
		this.coordX.setValue(x);
	}
	
	public void setCoordY(int y) {
		this.coordY.setValue(y);
	}
	
	public IntegerProperty getCoordX() {
		return this.coordX;
	}
	
	public IntegerProperty getCoordY() {
		return this.coordY;
	}
}
