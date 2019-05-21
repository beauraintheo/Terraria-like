package vue;

import java.io.File;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Plateau;

public class VuePlateau {
	
	private int tilesetX;
	private int tilesetY;
	private Image tuiles;
	private TilePane tilePaneMap;
	
	public VuePlateau() {
		tuiles = new Image(new File("Ressources/Maps/tileset.png").toURI().toString());
		this.tilePaneMap = new TilePane(Orientation.VERTICAL);
	}
	
	public void proprieteTilePane() {
		this.tilePaneMap.setTileAlignment(Pos.CENTER_LEFT);
		this.tilePaneMap.setPrefRows(80);
		this.tilePaneMap.setMaxWidth(800);
	}
	
	public void creeVueMap(Plateau plateau) {
		for (int x = 0; x < plateau.getPlateau().length - 1; x++) {
			for (int y = 0; y < plateau.getPlateau()[x].length; y++) {
				int casePlateau = plateau.getPlateau()[x][y];
				ImageView img = decoupage(casePlateau);
				this.tilePaneMap.getChildren().add(img);
			}	
		}
	}
		
	public ImageView decoupage(int casePlateau) {
		ImageView imgTuile = new ImageView(tuiles);
		selectionTuile(casePlateau);
		Rectangle2D decoupage = new Rectangle2D(tilesetX, tilesetY, 16, 16);
		imgTuile.setViewport(decoupage);
		return imgTuile;
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
			this.tilesetY = 18;
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
			this.tilesetY = 1;
		}
	}
	
	public int getTilesetX() {
		return tilesetX;
	}

	public int getTilesetY() {
		return tilesetY;
	}
	
	public TilePane getTilePane() {
		return this.tilePaneMap;
	}

}
