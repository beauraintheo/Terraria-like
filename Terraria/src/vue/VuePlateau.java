package vue;

import java.io.File;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Coordonnees;

public class VuePlateau extends TilePane implements ObservateurPlateau {

	private int tilesetX;
	private int tilesetY;
	private Image tuiles;
	private Image imageFond;
	private ImageView imageViewFond;

	public VuePlateau(int[][] plateau) {
		tuiles = new Image(new File("Ressources/Maps/tilesetv4.png").toURI().toString());
		imageFond = new Image(new File("Ressources/Maps/jourv2.png").toURI().toString());
		imageViewFond = new ImageView();
		this.setOrientation(Orientation.HORIZONTAL);
		this.setTileAlignment(Pos.CENTER_LEFT);
		this.setPrefColumns(80);
		proprieteFond();
		creeVueMap(plateau);
	}

	public void proprieteFond() {
		this.imageViewFond.setImage(imageFond);
		// this.imageViewFond.setFitWidth(1270);
		// this.imageViewFond.setFitHeight(600);
	}

	// Méthode pour créer une vue de la map en fonction du plateau qui lui est
	// donné

	public void creeVueMap(int[][] plateau) {
		for (int x = 0; x < plateau.length; x++) {
			for (int y = 0; y < plateau[x].length; y++) {
				int casePlateau = plateau[x][y];
				ImageView img = decoupage(casePlateau);
				this.getChildren().add(img);
			}
		}
	}

	// MÃ©thode pour dÃ©couper le tileset donnÃ©

	public ImageView decoupage(int casePlateau) {
		if (casePlateau != -1) {
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

	// MÃ©thode pour sÃ©lectionner une tuile voulue en fonction de la case du
	// plateau

	public void selectionTuile(int tuile) {
		switch (tuile) {
		case 0: // Herbe (bloc)
			this.tilesetX = 1;
			this.tilesetY = 1;
			break;
		case 1: // Terre
			this.tilesetX = 18;
			this.tilesetY = 1;
			break;
		case 2: // Pierre
			this.tilesetX = 35;
			this.tilesetY = 1;
			break;
		case 3: // Sable
			this.tilesetX = 52;
			this.tilesetY = 1;
			break;
		case 5: // Bois
			this.tilesetX = 1;
			this.tilesetY = 18;
			break;
		case 6: // Charbon
			this.tilesetX = 18;
			this.tilesetY = 18;
			break;
		case 7: // Fer
			this.tilesetX = 35;
			this.tilesetY = 18;
			break;
		case 8: // Obsidienne
			this.tilesetX = 52;
			this.tilesetY = 18;
			break;
		case 10: // Feuille
			this.tilesetX = 1;
			this.tilesetY = 35;
			break;
		case 11: // Herbe-Courte
			this.tilesetX = 18;
			this.tilesetY = 35;
			break;
		case 12: // Herbe-Longue
			this.tilesetX = 35;
			this.tilesetY = 35;
			break;
		case 13: // Champignon
			this.tilesetX = 52;
			this.tilesetY = 35;
			break;
		case 15: // Eau-Sommet
			this.tilesetX = 1;
			this.tilesetY = 52;
			break;
		case 16: // Eau
			this.tilesetX = 18;
			this.tilesetY = 52;
			break;
		case 17: // Fleur magique
			this.tilesetX = 35;
			this.tilesetY = 52;
			break;
		case 18: // Barrière
			this.tilesetX = 52;
			this.tilesetY = 52;
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
	public void valueChanged(int newValue, Coordonnees coo) {
		int index = 80 * (coo.getCoordY() / 16) + (coo.getCoordX() / 16);
		this.getChildren().remove(index);
		this.getChildren().add(index, decoupage(newValue));
	}
}