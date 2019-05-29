package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.VueJoueur;
import vue.VuePlateau;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Personnage;
import modele.Plateau;

public class Controleur implements Initializable {

	private Plateau plateau;
	private Personnage player;
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;

	private int timer;
	private int toucheAppuyée;

	@FXML
	private Pane paneJeu;
	private Timeline gameloop;
	private KeyCode touche;

	private void initialiserMap() {
		this.vuePlateau.proprieteFond();
		this.vuePlateau.proprieteTilePane();
		this.vuePlateau.creeVueMap(plateau);
		this.paneJeu.getChildren().add(this.vuePlateau.getFond());
		this.paneJeu.getChildren().add(this.vuePlateau.getTilePane());
	}

	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(this.vueJoueur.getPersonnage());

		this.vueJoueur.getPersonnage().translateXProperty().bind(player.getPosition().coordXProperty());
		this.vueJoueur.getPersonnage().translateYProperty().bind(player.getPosition().coordYProperty());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.plateau = new Plateau();
		this.player = new Personnage(plateau);
		this.vuePlateau = new VuePlateau();
		this.vueJoueur = new VueJoueur();

		toucheAppuyée = 0;

		initialiserMap();
		initialiserJoueur();
		initAnimation();

		gameloop.play();
	}

	private void initAnimation() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		timer = 0;

		KeyFrame kf = new KeyFrame(Duration.seconds(0.030), (ev -> {
			this.deplacement();

			// Gravité

			if (timer % 5 == 0) {
				if (player.détectionSol()) {
					player.tomber();

					if (player.détectionSol()) {
						this.vueJoueur.descendre();
					}

					else {
						this.vueJoueur.chuteViolente();
					}
				}
			}
			timer++;
		}));
		gameloop.getKeyFrames().add(kf);
	}

	@FXML
	void clavier(KeyEvent event) {
		this.touche = event.getCode();
	}

	@FXML
	void relacheTouche(KeyEvent event) {
		this.touche = null;
	}

	private void deplacement() {
		if (touche != null) {
			if (touche == KeyCode.Q || touche == KeyCode.LEFT) {
				player.deplacementGauche();
				this.vueJoueur.tournerAGauche();
			}

			if (touche == KeyCode.D || touche == KeyCode.RIGHT) {
				player.deplacementDroite();
				this.vueJoueur.tournerADroite();
			}

			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				if (toucheAppuyée < 10) {
					player.sauter();
					this.vueJoueur.monter();
					toucheAppuyée++;
				}

				else {
					if (!player.détectionSol()) {
						toucheAppuyée = 0;
					}
				}
			}
		}
	}
}