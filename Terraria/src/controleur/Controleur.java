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

	@FXML
	private Pane paneJeu;
	private Timeline gameloop;
	private KeyCode touche ;

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
		initAnimation();

		gameloop.play();
	}

	private void initAnimation() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.030), 
								  (ev -> { this.deplacement(); })
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
		if (touche != null) {
			if (touche == KeyCode.Q || touche == KeyCode.LEFT) {
				player.deplacementGauche(plateau.getPlateau());
				this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueGauche());
			}
			
			if (touche == KeyCode.D || touche == KeyCode.RIGHT) {
				player.deplacementDroite(plateau.getPlateau());
				this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueDroite());
			}
			
			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				player.sauter(plateau.getPlateau());
				this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueSaut());
			}
			
			if (touche == KeyCode.S || touche == KeyCode.DOWN) {
				player.gravité(plateau.getPlateau());

				if (player.détectionSol(plateau.getPlateau())) {
					this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueTombe());
				}

				else {
					this.vueJoueur.getPersonnage().setImage(vueJoueur.getVueChute());
				}
			}
		}
	}
}