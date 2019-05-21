/*
 * Coordonnees.java
 * Cette méthode est utilisée pour avoir les coordonnées de notre personnage sur la map. 
 * Elle est utilisée pour faire les déplacements de celui-ci;
 */

package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Coordonnees {
	
	private DoubleProperty coordX;
	private DoubleProperty coordY;
	
	public Coordonnees() {
		this.coordX = new SimpleDoubleProperty(250);
		this.coordY = new SimpleDoubleProperty(270);
	}
	
	public Coordonnees(double x, double y) {
		this.coordX = new SimpleDoubleProperty(x);
		this.coordY = new SimpleDoubleProperty(y);
	}
	
	public void setCoordX(double x) {
		this.coordX.setValue(x);
	}
	
	public void setCoordY(double y) {
		this.coordY.setValue(y);
	}
	
	public DoubleProperty getCoordX() {
		return this.coordX;
	}
	
	public DoubleProperty getCoordY() {
		return this.coordY;
	}
	
}
