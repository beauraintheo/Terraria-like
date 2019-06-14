/*
 * VueInventaireCase.java
 * Cette classe sert à créer les VBox de notre inventaire
 */

package vue;

import java.io.File;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VueInventaireCase extends VBox {

	private ImageView img;
	private Label l;

	public VueInventaireCase(int numCase) {
		this.img = new ImageView();
		this.img.setFitWidth(64);
		this.img.setFitHeight(64);
		this.l = new Label();
		this.l.setTranslateX(24);
		ajouterImage(numCase);
		this.getChildren().add(img);
		this.getChildren().add(l);
		this.setStyle("-fx-border-color: black;");
	}

	private void ajouterImage(int numCase) {
		switch (numCase) {
		case 1: // Epee
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/Epee.png").toURI().toString()));
			break;
		case 2: // Pioche
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/Pioche.png").toURI().toString()));
			break;
		case 3: // Herbe (bloc) / Terre
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/BlocTerre.png").toURI().toString()));
			break;
		case 4: // Pierre
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/BlocPierre.png").toURI().toString()));
			break;
		case 5: // Bois
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/BlocBois.png").toURI().toString()));
			break;
		case 6: // Charbon
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/BlocCharbon.png").toURI().toString()));
			break;
		case 7: // Fer
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/BlocFer.png").toURI().toString()));
			break;
		case 8: // Fleur magique
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/Fleur.png").toURI().toString()));
			break;
		default:
			this.img.setImage(new Image(new File("Ressources/Sprites/Inventaire/BlocTerre.png").toURI().toString()));
		}
	}

	public Label getLabel() {
		return this.l;
	}

	public void selectionnerCase() {
		this.setStyle("-fx-border-color: red;");
	}

	public void deselectionnerCase() {
		this.setStyle("-fx-border-color: black;");
	}
}