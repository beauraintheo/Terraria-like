/*
 * VueCurseur.java
 * Cette classe sert à afficher un curseur différent de celui de base
 * Inutilisé dans notre jeu mais fonctionnel
 */

package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueCurseur extends ImageView {
	
	private Image imgPioche, imgEpee;
	
	public VueCurseur() {
		imgPioche = chargerPioche();
		imgEpee = chargerEpee();
	}
	
	public Image chargerPioche() {
		File img = new File("../Ressources/Sprites/Items/pioche-diamant.gif");
		Image curs = new Image(img.toURI().toString());
		return curs;
	}
	
	public Image chargerEpee() {
		File img = new File("../Ressources/Sprites/Items/epee-diamant.gif");
		Image curs = new Image(img.toURI().toString());
		return curs;
	}
	
	public Image pioche() {
		return this.imgPioche;
	}
	
	public Image epee() {
		return this.imgEpee;
	}
}
