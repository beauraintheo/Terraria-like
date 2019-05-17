package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Personnage {
	
	private DoubleProperty coordX;
	private DoubleProperty coordY;
	
	public Personnage() {
		this.coordX = new SimpleDoubleProperty(250);
		this.coordY = new SimpleDoubleProperty(270);
	}
	
	public void setCoordX(int x) {
		this.coordX.setValue(x);
	}
	
	public void setCoordY(int y) {
		this.coordY.setValue(y);
	}
	
	public DoubleProperty getCoordX() {
		return this.coordX;
	}
	
	public DoubleProperty getCoordY() {
		return this.coordY;
	}
	
}
