package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueJoueur {
	
	private Image imageG, imageD;
	private ImageView personnage;
	
	public VueJoueur() {
		imageG = gauche();
		imageD = droite();
		personnage = new ImageView(imageD);
		personnage.setFitHeight(50);
		personnage.setFitWidth(50);
	}
	
	public Image gauche() {
		File imgGauche = new File("Ressources/Sprites/Personnage/voltali-gauche.gif");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
		return persoVueGauche;
	}

	public Image droite() {
		File imgDroit = new File("Ressources/Sprites/Personnage/voltali-droite.gif");
		Image persoVueDroit = new Image(imgDroit.toURI().toString());
		return persoVueDroit;
	}
	
	public Image getVueGauche() {
		return this.imageG;
	}
	
	public Image getVueDroite() {
		return this.imageD;
	}
	
	public ImageView getPersonnage() {
		return this.personnage;
	}
}
