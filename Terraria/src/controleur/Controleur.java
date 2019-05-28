package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.VueJoueur;
import vue.VuePlateau;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Personnage;
import modele.Plateau;

public class Controleur implements Initializable{

	private Plateau plateau;
	private Personnage player;
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;

	private int timer;
	private int toucheAppuyé;

	@FXML
	private Pane paneJeu;
	private Timeline gameloop;
	private KeyCode touche ;

	private void initialiserMap() {
		this.vuePlateau.proprieteFond();
		this.vuePlateau.proprieteTilePane();
		this.vuePlateau.creeVueMap(plateau);
		this.paneJeu.getChildren().add(this.vuePlateau.getFond());
		this.paneJeu.getChildren().add(this.vuePlateau.getTilePane());
	}

	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(this.vueJoueur.getPersonnage());

		this.vueJoueur.getPersonnage().translateXProperty().bind(player.getPosition().getCoordX());
		this.vueJoueur.getPersonnage().translateYProperty().bind(player.getPosition().getCoordY());
	}

	@Override
	public void initialize(URL location,ResourceBundle resources) {
		this.plateau = new Plateau();
		this.player = new Personnage(plateau);
		
		this.vuePlateau = new VuePlateau();
		this.vueJoueur = new VueJoueur();
		initialiserMap();
		initialiserJoueur();
		initAnimation();

		gameloop.play();
	}

	private void initAnimation() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		timer = 0;

		KeyFrame kf = new KeyFrame(Duration.seconds(0.030), 
				(ev -> { 
					this.deplacement();

					// Gravité

					if (timer % 5 == 0) {
						if (player.détectionSol()) {
							player.tomber();

							if (!player.détectionSol()) {
								this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueChute());
							}

							else {
								this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueTombe());
							}
						}
						timer = 0;
					}
					timer++;
				})
				);
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
		toucheAppuyé = 0;

		if (touche != null) {
			if (touche == KeyCode.Q || touche == KeyCode.LEFT) {
				player.deplacementGauche();
				this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueGauche());
			}

			if (touche == KeyCode.D || touche == KeyCode.RIGHT) {
				player.deplacementDroite();
				this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueDroite());
			}

			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				player.sauter();
				this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueSaut());
			}
		}
	}
}