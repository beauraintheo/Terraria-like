package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueJoueur {
	
	private Image imageG, imageD, imageH, imageB, imageT;
	private ImageView imgPersonnage;
	
	public VueJoueur() {
		imageG = gauche();
		imageD = droite();
		imageH = sauter();
		imageB = chute();
		imageT = tombe();
		
		imgPersonnage = new ImageView(imageD);
		imgPersonnage.setFitHeight(16);
		imgPersonnage.setFitWidth(16);
	}
	
	public Image gauche() {
		File imgGauche = new File("Ressources/Sprites/Personnage/pero-gauche.png");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
		return persoVueGauche;
	}

	public Image droite() {
		File imgDroit = new File("Ressources/Sprites/Personnage/pero-droite.png");
		Image persoVueDroit = new Image(imgDroit.toURI().toString());
		return persoVueDroit;
	}
	
	public Image chute() {
		File imgChute = new File("Ressources/Sprites/Personnage/pero-chute.png");
		Image persoVueChute = new Image(imgChute.toURI().toString());
		return persoVueChute;
	}
	
	public Image sauter() {
		File imgSaut = new File("Ressources/Sprites/Personnage/pero-saut.png");
		Image persoVueSaut = new Image(imgSaut.toURI().toString());
		return persoVueSaut;
	}
	
	public Image tombe() {
		File imgTombe = new File("Ressources/Sprites/Personnage/pero-tombe.png");
		Image persoVueTombe = new Image(imgTombe.toURI().toString());
		return persoVueTombe;
	}
	
	public Image getVueGauche() {
		return this.imageG;
	}
	
	public Image getVueDroite() {
		return this.imageD;
	}
	
	public Image getVueSaut() {
		return this.imageH;
	}
	
	public Image getVueChute() {
		return this.imageB;
	}
	
	public Image getVueTombe() {
		return this.imageT;
	}
	
	public ImageView getPersonnage() {
		return this.imgPersonnage;
	}
}
