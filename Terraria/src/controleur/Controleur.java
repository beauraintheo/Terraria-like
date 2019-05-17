package controleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import vue.VuePlateau;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import modele.Personnage;
import modele.Plateau;

public class Controleur implements Initializable{

	private VuePlateau vuePlateau = new VuePlateau();
	private Personnage player = new Personnage();
	private Plateau plateau;

	private ImageView personnage;
	private Timeline gameloop;

	@FXML
	private Pane paneJeu;

	@FXML
	private TilePane tilePaneMap;

	private void initialiserMap() {
		for (int x = 0; x < plateau.getPlateau().length - 1; x++) {
			for (int y = 0; y < plateau.getPlateau()[x].length; y++) {
				int casePlateau = plateau.getPlateau()[x][y];
				ImageView img = vuePlateau.decoupage(casePlateau);
				this.tilePaneMap.getChildren().add(img);
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

	private void initialiserDeplacement() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);		

		personnage.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				clavier(event);
			}
		});
	}

	@Override
	public void initialize(URL location,ResourceBundle resources) {
		this.plateau = new Plateau();

		initialiserMap();
		initialiserJoueur();
		initialiserDeplacement();
		gameloop.play();
	}

	@FXML
	void clavier(KeyEvent event) {
		if (event.getCode() == KeyCode.LEFT) {
			personnage.setTranslateX(- 1);
		}

		if (event.getCode() == KeyCode.RIGHT) {
			personnage.setTranslateX(+ 1);
		}

		if (event.getCode() == KeyCode.UP) {
			personnage.setTranslateY(- 1);
		}

		if (event.getCode() == KeyCode.DOWN) {
			personnage.setTranslateY(+ 1);
		}
	}
}
