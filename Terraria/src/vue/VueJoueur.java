package vue;

import java.io.File;

import javafx.scene.image.Image;

public class VueJoueur {
	
	public Image gauche() {
		File imgGauche = new File("Ressources/Sprites/Personnage/player_left.png");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
		return persoVueGauche;
	}
	
	public Image droite() {
		File imgDroit = new File("Ressources/Sprites/Personnage/player_right.png");
		Image persoVueDroit = new Image(imgDroit.toURI().toString());
		return persoVueDroit;
	}
}
