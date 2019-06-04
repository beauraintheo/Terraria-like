package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueJoueur extends ImageView {
	
	private Image imageG, imageD, imageH, imageB, imageDouleur;
	
	public VueJoueur() {
		imageG = chargerImgGauche();
		imageD = chargerImgDroite();
		imageH = chargerImgSaut();
		imageB = chargerImgChute();
		imageDouleur = chargerImgBobo();
		
		this.setImage(imageD);
		this.setFitHeight(16);
		this.setFitWidth(16);
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