package vue;

import java.io.File;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Plateau;

public class VuePlateau extends TilePane implements ObservateurPlateau {
	
	private int tilesetX;
	private int tilesetY;
	private Image tuiles;
	private Image imageFond;
	private ImageView imageViewFond;
	
	public VuePlateau(Plateau plateau) {
		tuiles = new Image(new File("Ressources/Maps/tileset.png").toURI().toString());
		imageFond = new Image(new File("Ressources/Maps/jour.png").toURI().toString());
		imageViewFond = new ImageView();
		this.setOrientation(Orientation.VERTICAL);
		this.setTileAlignment(Pos.CENTER_LEFT);
		this.setPrefRows(80);
		this.setMaxWidth(800);
		proprieteFond();
		creeVueMap(plateau);
	}
	
	
	public void proprieteFond() {
		this.imageViewFond.setImage(imageFond);
		this.imageViewFond.setFitWidth(1264);
		this.imageViewFond.setFitHeight(600);
	}
	
	// Méthode pour créer une vue de la map en fonction du plateau qui lui est donné
	
	public void creeVueMap(Plateau plateau) {
		for (int x = 0; x < plateau.getPlateau().length - 1; x++) {
			for (int y = 0; y < plateau.getPlateau()[x].length; y++) {
				int casePlateau = plateau.getPlateau()[y][x];
				ImageView img = decoupage(casePlateau);
				this.getChildren().add(img);
			}	
		}
	}
		
	// Méthode pour découper le tileset donné
	
	public ImageView decoupage(int casePlateau) {
		if(casePlateau!=0) {
			ImageView imgTuile = new ImageView(tuiles);
			selectionTuile(casePlateau);
			Rectangle2D decoupage = new Rectangle2D(tilesetX, tilesetY, 16, 16);
			imgTuile.setViewport(decoupage);
			return imgTuile;
		}
		
		else {
			ImageView imgTuilevide = new ImageView();
			return imgTuilevide;
		}
	}
	
	// Méthode pour sélectionner une tuile voulue en fonction de la case du plateau
	
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
	
	public ImageView getFond() {
		return this.imageViewFond;
	}

	@Override
	public void valueChanged(int index, int newValue) {
		this.getChildren().remove(index);
		this.getChildren().add(index, decoupage(newValue));
		
	}
}