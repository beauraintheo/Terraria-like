package controleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import vue.VueJoueur;
import vue.VuePlateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import modele.Personnage;

public class Controleur implements Initializable{

	private VueJoueur vueJoueur = new VueJoueur();
	private VuePlateau vueMap = new VuePlateau();
	private Personnage player = new Personnage();
	private int[][] notreMap;
	private Image tuiles;
	private int tilesetX = 0;
	private int tilesetY = 0;

	private ImageView personnage;
	
	
	@FXML
	private Pane paneJeu;

	@FXML
	private TilePane tilePaneMap;
	
	

	private void initialiserMap() {
		notreMap = vueMap.getMap();
		tuiles = new Image("/vue/maps/tileset.png");

		for (int x = 0; x < notreMap.length - 1; x++) {
			for (int y = 0; y < notreMap[x].length; y++) {
				ImageView tuile = new ImageView();
				tuile.setImage(tuiles);
				selectionTuile(notreMap[x][y]);
				Rectangle2D decoupage = new Rectangle2D(tilesetX, tilesetY, 16, 16);
				tuile.setViewport(decoupage);
				this.tilePaneMap.getChildren().add(tuile);
			}	
		}
	}

	private void initialiserJoueur() {
		File img = new File("Ressources/Sprites/Personnage/player_right.png");
		Image persoVue = new Image(img.toURI().toString());
		personnage = new ImageView(persoVue);
		this.paneJeu.getChildren().add(personnage);

		this.personnage.layoutXProperty().bind(player.getCoordX());
		this.personnage.layoutYProperty().bind(player.getCoordY());
		
	}

	private void selectionTuile(int tuile) {
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

/*	@FXML
	public void seDeplacer(ActionEvent event) {
		System.out.println("gsdqjhdfhvdj");
		
		int x = this.player.getCoordX().getValue();
		int y = this.player.getCoordY().getValue();
		
		if () {
			this.player.setCoordX(x-10);
			this.player.setCoordY(y);
		}
		else if() {
			this.player.setCoordX(x+10);
			this.player.setCoordY(y);
		}
	}*/

	@Override
	public void initialize(URL location,ResourceBundle resources) {
		initialiserMap();
		initialiserJoueur();   
		
		personnage.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				System.out.println("lol");
			}
		});
	}
}
