package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.VueEnnemi;
import vue.VueJoueur;
import vue.VuePlateau;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Coordonnees;
import modele.Ennemi;
import modele.Jeu;
import modele.Joueur;

public class Controleur implements Initializable {

	private Jeu jeu;
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;
	private VueEnnemi vueEnnemi;

	// Temporaire, à modifier
	private Joueur player;
	private Ennemi mob;

	private int timer;
	private int bordTouche;
	private int toucheAppuyee;

	@FXML
	private Pane paneJeu;
	private Timeline gameloop;
	private KeyCode touche;

	private void initialiserMap() {
		this.paneJeu.getChildren().add(this.vuePlateau.getFond());
		this.paneJeu.getChildren().add(this.vuePlateau);
	}

	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(this.vueJoueur);
		this.vueJoueur.translateXProperty().bind(player.getPosition().coordXProperty());
		this.vueJoueur.translateYProperty().bind(player.getPosition().coordYProperty());
	}

	private void initialiserEnnemis() {
		this.paneJeu.getChildren().add(this.vueEnnemi);
		this.vueEnnemi.translateXProperty().bind(mob.getPosition().coordXProperty());
		this.vueEnnemi.translateYProperty().bind(mob.getPosition().coordYProperty());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.jeu = new Jeu();
		this.player = this.jeu.getJoueur();
		this.mob = this.jeu.getEnnemi();
		this.vuePlateau = new VuePlateau(this.jeu.getPlateau());
		this.vueJoueur = new VueJoueur();
		this.vueEnnemi = new VueEnnemi();
		this.jeu.getPlateau().addObs(this.vuePlateau);
		
		toucheAppuyee = 0;

		initialiserMap();
		initialiserJoueur();
		initialiserEnnemis();
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
				if (player.detectionVide()) {
					player.tomber();

					if (player.detectionVide()) {
						this.vueJoueur.orientationBas();
					}

					else {
						this.vueJoueur.orientationBobo();
					}
				}
			}

			if (timer % 8 == 0) {
				if (bordTouche == 0) {
					if (mob.detectionBlocPlein(-16, 0)) {
						this.vueEnnemi.orientationDroite();
						bordTouche = 1;
					}
					mob.deplacementGauche();
				}

				if (bordTouche == 1) {
					if (mob.detectionBlocPlein(16, 0)) {
						this.vueEnnemi.orientationGauche();
						bordTouche = 0;
					}
					mob.deplacementDroite();
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

	@FXML
	void actionBloc(MouseEvent event) {
		Coordonnees coo = new Coordonnees((int) event.getX(), (int) event.getY());
		
		if (event.getButton() == MouseButton.PRIMARY) {
			this.jeu.getPlateau().casserBloc(coo);
		}
		
		if (event.getButton() == MouseButton.SECONDARY) {
			this.jeu.getPlateau().poserBloc(coo);
		}
	}

	private void deplacement() {
		if (touche != null) {
			if (touche == KeyCode.Q || touche == KeyCode.LEFT) {
				player.deplacementGauche();
				this.vueJoueur.orientationGauche();
			}

			if (touche == KeyCode.D || touche == KeyCode.RIGHT) {
				player.deplacementDroite();
				this.vueJoueur.orientationDroite();
			}

			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				if (toucheAppuyee < 10) {
					player.sauter();
					this.vueJoueur.orientationHaut();
					toucheAppuyee++;
				}

				else {
					if (!player.detectionVide()) {
						toucheAppuyee = 0;
					}
				}
			}
		}
	}
}