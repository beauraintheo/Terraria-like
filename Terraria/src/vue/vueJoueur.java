package vue;

import java.io.File;

import javafx.scene.image.Image;

public class vueJoueur {
	
	public Image gauche() {
		File imgGauche = new File("Ressources/Sprites/Personnage/player_left.png");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
		return persoVueGauche;
	}
	
}
