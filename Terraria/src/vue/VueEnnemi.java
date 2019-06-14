/*
 * VueEnnemi.java
 * Cette classe s'occupe d'afficher nos différents types d'ennemis
 */

package vue;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Coordonnees;

public class VueEnnemi extends ImageView implements ObservateurEnnemi {

	private Image imageG, imageD;
	private StringProperty orientationProperty;

	public VueEnnemi() {
		imageG = chargerImgGauche();
		imageD = chargerImgDroite();

		this.orientationProperty = new SimpleStringProperty("Gauche");
		this.setImage(imageG);
		this.setFitHeight(16);
		this.setFitWidth(16);
	}

	public void chargerImage() {
		if (this.orientationProperty.equals("Gauche"))
			this.orientationGauche();
		else
			this.orientationDroite();
	}

	public final StringProperty orientationProperty() {
		return this.orientationProperty;
	}

	// Méthode pour charger l'image gauche du personnage principal

	public Image chargerImgGauche() {
		File imgGauche = new File("Ressources/Sprites/Personnage/metaknight-gauche.gif");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
		return persoVueGauche;
	}

	// Méthode pour charger l'image droite du personnage principal

	public Image chargerImgDroite() {
		File imgDroit = new File("Ressources/Sprites/Personnage/metaknight-droite.gif");
		Image persoVueDroit = new Image(imgDroit.toURI().toString());
		return persoVueDroit;
	}

	public void orientationGauche() {
		this.setImage(imageG);
	}

	public void orientationDroite() {
		this.setImage(imageD);
	}

	@Override
	public void valueChanged(int newValuePV, Coordonnees coo) {

	}
}