package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueEnnemi {

	private Image imageG, imageD;
	private ImageView imgPersonnage;

	public VueEnnemi() {
		imageG = chargerImgGauche();
		imageD = chargerImgDroite();

		imgPersonnage = new ImageView(imageD);
		imgPersonnage.setFitHeight(16);
		imgPersonnage.setFitWidth(16);
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
		this.getPersonnage().setImage(imageG);
	}

	public void orientationDroite() {
		this.getPersonnage().setImage(imageD);
	}

	public ImageView getPersonnage() {
		return this.imgPersonnage;
	}

}