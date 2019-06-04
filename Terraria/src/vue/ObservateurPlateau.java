package vue;

import modele.Coordonnees;

public interface ObservateurPlateau {
	
	public void valueChanged(int newValue, Coordonnees coo);

}