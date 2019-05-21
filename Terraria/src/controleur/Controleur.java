package controleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import vue.VuePlateau;
import javafx.animation.KeyFrame;
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
import javafx.util.Duration;
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

	private void initialiserMap() {
		this.vuePlateau.proprieteTilePane();
		this.vuePlateau.creeVueMap(plateau);
		this.paneJeu.getChildren().add(this.vuePlateau.getTilePane());
	}

	private void initialiserJoueur() {
		File img = new File("Ressources/Sprites/Personnage/player_right.png");
		Image persoVue = new Image(img.toURI().toString());
		personnage = new ImageView(persoVue);
		this.paneJeu.getChildren().add(personnage);
		
		this.personnage.layoutXProperty().bind(player.getPosition().getCoordX());
		this.personnage.layoutYProperty().bind(player.getPosition().getCoordY());
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

		if (event.getCode() == KeyCode.Q) {
			player.setPosition(player.getPosition().getCoordX().getValue() - 100, player.getPosition().getCoordY().getValue());
		}

		if (event.getCode() == KeyCode.D) {
			player.setPosition(player.getPosition().getCoordX().getValue() + 100, player.getPosition().getCoordY().getValue());
		}

		if (event.getCode() == KeyCode.Z) {
			player.setPosition(player.getPosition().getCoordX().getValue(), player.getPosition().getCoordY().getValue() - 100);
		}

		if (event.getCode() == KeyCode.S) {
			player.setPosition(player.getPosition().getCoordX().getValue(), player.getPosition().getCoordY().getValue() + 100);
		}
	}
}
