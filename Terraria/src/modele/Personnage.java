package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
	
	private IntegerProperty coordX;
	private IntegerProperty coordY;
	
	public Personnage() {
		this.coordX = new SimpleIntegerProperty(250);
		this.coordY = new SimpleIntegerProperty(270);
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
