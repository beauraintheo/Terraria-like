/*
 * VueJoueur.java
 * Cette classe sert à créer notre joueur côté vue
 */

package vue;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueJoueur extends ImageView {

	private Image imageSpawn, imageG, imageD, imageH, imageB, imageDouleur;
	private StringProperty orientationProperty;

	public VueJoueur() {
		imageSpawn = chargerImgSpawn();
		imageG = chargerImgGauche();
		imageD = chargerImgDroite();
		imageH = chargerImgSaut();
		imageB = chargerImgChute();
		imageDouleur = chargerImgBobo();

		this.orientationProperty = new SimpleStringProperty("Droite");
		ajouterEcouteurOrientation();

		this.setImage(imageSpawn);
		this.setFitHeight(16);
		this.setFitWidth(16);
	}

	public final StringProperty orientationProperty() {
		return this.orientationProperty;
	}

	// Cette méthode sert à savoir notre orientation pour lier le modèle à la vue et adapter ces déplacements en fonction de notre orientation
	
	public void ajouterEcouteurOrientation() {
		orientationProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("Gauche")) {
					orientationGauche();
				} else if (newValue.equals("Droite")) {
					orientationDroite();
				} else if (newValue.equals("Haut")) {
					orientationHaut();
				} else if (newValue.equals("Bas")) {
					orientationBas();
				} else if (newValue.equals("Spawn")) {
					orientationSpawn();
				}
			}
		});
	}

	public Image chargerImgSpawn() {
		File imgSpawn = new File("Ressources/Sprites/Personnage/pero-spawn.png");
		Image persoVueSpawn = new Image(imgSpawn.toURI().toString());
		return persoVueSpawn;
	}

	// Méthode pour charger l'image gauche du personnage principal

	public Image chargerImgGauche() {
		File imgGauche = new File("Ressources/Sprites/Personnage/pero-gauche.png");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
		return persoVueGauche;
	}

	// Méthode pour charger l'image droite du personnage principal

	public Image chargerImgDroite() {
		File imgDroit = new File("Ressources/Sprites/Personnage/pero-droite.png");
		Image persoVueDroit = new Image(imgDroit.toURI().toString());
		return persoVueDroit;
	}

	// Méthode pour charger l'image haute du personnage principal

	public Image chargerImgSaut() {
		File imgSaut = new File("Ressources/Sprites/Personnage/pero-saut.png");
		Image persoVueSaut = new Image(imgSaut.toURI().toString());
		return persoVueSaut;
	}

	// Méthode pour charger l'image basse du personnage principal

	public Image chargerImgChute() {
		File imgTombe = new File("Ressources/Sprites/Personnage/pero-tombe.png");
		Image persoVueTombe = new Image(imgTombe.toURI().toString());
		return persoVueTombe;
	}

	// Méthode pour charger l'image quand le personnage se fait mal

	public Image chargerImgBobo() {
		File imgChute = new File("Ressources/Sprites/Personnage/pero-bobo.png");
		Image persoVueChute = new Image(imgChute.toURI().toString());
		return persoVueChute;
	}

	public void orientationSpawn() {
		this.setImage(imageSpawn);
	}

	public void orientationGauche() {
		this.setImage(imageG);
	}

	public void orientationDroite() {
		this.setImage(imageD);
	}

	public void orientationHaut() {
		this.setImage(imageH);
	}

	public void orientationBas() {
		this.setImage(imageB);
	}

	public void orientationBobo() {
		this.setImage(imageDouleur);
	}

}