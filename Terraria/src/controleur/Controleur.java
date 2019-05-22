package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.VueJoueur;
import vue.VuePlateau;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import modele.Personnage;
import modele.Plateau;

public class Controleur implements Initializable{

	private Plateau plateau;
	private Personnage player;
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;

	@FXML
	private Pane paneJeu;

	private void initialiserMap() {
		this.vuePlateau.proprieteTilePane();
		this.vuePlateau.creeVueMap(plateau);
		this.paneJeu.getChildren().add(this.vuePlateau.getTilePane());
	}

	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(this.vueJoueur.getPersonnage());
		
		this.vueJoueur.getPersonnage().translateXProperty().bind(player.getPosition().getCoordX());
		this.vueJoueur.getPersonnage().translateYProperty().bind(player.getPosition().getCoordY());
	}

	private void deplacementPersonnage() {
		this.vueJoueur.getPersonnage().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				clavier(event);
			}
		});
	}
	
	@Override
	public void initialize(URL location,ResourceBundle resources) {
		this.plateau = new Plateau();
		this.player = new Personnage();
		this.vuePlateau = new VuePlateau();
		this.vueJoueur = new VueJoueur();

		initialiserMap();
		initialiserJoueur();
		deplacementPersonnage();
	}

	@FXML
	void clavier(KeyEvent event) {
		if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.LEFT) {
			player.setPosition(player.getPosition().getCoordX().getValue() - 5, player.getPosition().getCoordY().getValue());
			this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueGauche());
		}

		if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
			player.setPosition(player.getPosition().getCoordX().getValue() + 5, player.getPosition().getCoordY().getValue());
			this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueDroite());
		}

		if (event.getCode() == KeyCode.Z || event.getCode() == KeyCode.UP) {
			player.setPosition(player.getPosition().getCoordX().getValue(), player.getPosition().getCoordY().getValue() - 5);
		}

		if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
			player.setPosition(player.getPosition().getCoordX().getValue(), player.getPosition().getCoordY().getValue() + 5);
		}
	}
}