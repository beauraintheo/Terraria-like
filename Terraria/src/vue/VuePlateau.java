package vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Plateau;

public class VuePlateau {
	
	private int tilesetX;
	private int tilesetY;
	private Image tuiles;
	
	public VuePlateau() {
		tuiles = new Image("/vue/maps/tileset.png");
	}
	
	public void selectionTuile(int tuile) {
		switch (tuile) {
		case 0: //Ciel
			this.tilesetX = 1;
			this.tilesetY = 1;
			break;
		case 1: //Terre
			this.tilesetX = 18;
			this.tilesetY = 1;
			break;
		case 3: //Bois
			this.tilesetX = 52;
			this.tilesetY = 1;
			break;
		case 7: //Herbe
			this.tilesetX = 1;
			this.tilesetY = 17;
			break;
		case 8: //Herbe - côté droit
			this.tilesetX = 18;
			this.tilesetY = 18;
			break;
		case 9: //Herbe - côté gauche
			this.tilesetX = 35;
			this.tilesetY = 18;
			break;
		case 15: //Herbe - coin haut droit
			this.tilesetX = 1;
			this.tilesetY = 52;
			break;
		case 28: //Pierre
			this.tilesetX = 1;
			this.tilesetY = 69;
			break;
		case 29: //Charbon
			this.tilesetX = 18;
			this.tilesetY = 69;
			break;
		default:
			this.tilesetX = 18;
			this.tilesetY = 18;
		}
	}

	
	public ImageView decoupage(int casePlateau) {
		ImageView imgTuile = new ImageView(tuiles);
		selectionTuile(casePlateau);
		Rectangle2D decoupage = new Rectangle2D(tilesetX, tilesetY, 16, 16);
		imgTuile.setViewport(decoupage);
		return imgTuile;
	}
	
	
	public int getTilesetX() {
		return tilesetX;
	}

	public int getTilesetY() {
		return tilesetY;
	}

}
