package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Personnage {

	private Coordonnees position;
	private StringProperty orientationProperty;
	private Plateau plateau;

	public Personnage(Plateau plateau, int coordX, int coordY) {
		this.position = new Coordonnees(coordX, coordY);
		this.orientationProperty = new SimpleStringProperty("Droite");
		this.plateau = plateau;
	}

	public Coordonnees getPosition() {
		return this.position;
	}

	public int getCoordXPosition() {
		return this.position.getCoordX();
	}

	public int getCoordYPosition() {
		return this.position.getCoordY();
	}

	public Coordonnees getPositionPlus(int vitesseX, int vitesseY) {
		Coordonnees coorProvisoires;

		coorProvisoires = new Coordonnees(this.position.getCoordX() + vitesseX, this.position.getCoordY() + vitesseY);
		return coorProvisoires;
	}

	public void setPosition(int x, int y) {
		this.position.setCoordX(x);
		this.position.setCoordY(y);
	}

	public final String getOrientation() {
		return this.orientationProperty.getValue();
	}

	public final void setOrientation(String direction) {
		this.orientationProperty.set(direction);
	}

	public final StringProperty orientationProperty() {
		return this.orientationProperty;
	}

	public void deplacementGauche() {
		this.setOrientation("Gauche");
		if (this.getPositionPlus(-16, 0).getCoordX() >= 0) {
			if (this.plateau.blocTraversable(this.plateau.getCasePlateau(this.getPositionPlus(-16, 0)))) {
				this.setPosition(this.getPosition().getCoordX() - 16, this.getPosition().getCoordY());
			}
		}
	}

	public void deplacementDroite() {
		this.setOrientation("Droite");
		if (this.getPositionPlus(16, 0).getCoordX() <= 1248) {
			if (this.plateau.blocTraversable(this.plateau.getCasePlateau(this.getPositionPlus(16, 0)))) {
				this.setPosition(this.getPosition().getCoordX() + 16, this.getPosition().getCoordY());
			}
		}
	}

	public void sauter() {
		if (this.getPositionPlus(0, -16).getCoordY() >= 0) {
			if (this.plateau.blocTraversable(this.plateau.getCasePlateau(this.getPositionPlus(0, -16)))) {
				this.setPosition(this.getPosition().getCoordX(), this.getPosition().getCoordY() - 16);
				this.setOrientation("Haut");
			}
		}
	}

	public void tomber() {
		if (this.getPositionPlus(0, 16).getCoordY() <= 1248) {
			if (this.plateau.blocTraversable(this.plateau.getCasePlateau(this.getPositionPlus(0, 16)))) {
				this.setPosition(this.getPosition().getCoordX(), this.getPosition().getCoordY() + 16);
				this.setOrientation("Bas");
			}
		}
	}

	public boolean detectionVide() {
		if (this.plateau.blocTraversable(this.plateau.getCasePlateau(this.getPositionPlus(0, 16)))) {
			return true;
		}
		return false;
	}

	public boolean peutCasser() {
			//
		return false;
	}

	public boolean detectionBlocPlein(int vitesseX, int vitesseY) {
		if (this.plateau.getCasePlateau(getPositionPlus(vitesseX, vitesseY)) == 0)
			return false;
		return true;
	}
}