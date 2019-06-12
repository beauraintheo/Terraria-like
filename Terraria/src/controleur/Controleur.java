package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.VueEnnemi;
import vue.VueInterfaceHaut;
import vue.VueJoueur;
import vue.VuePlateau;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.ParallelCamera;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import modele.Coordonnees;
import modele.Ennemi;
import modele.Item;
import modele.Jeu;
import modele.Joueur;

public class Controleur implements Initializable {

	private Jeu jeu;
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;
	private VueEnnemi vueEnnemi;
	private Item item;

	private int timer;
	private int bordTouche;
	private int toucheAppuyee;

	private Timeline gameloop;
	private KeyCode touche;

	@FXML
	private Pane paneJeu;
	
	@FXML
	private HBox inventaire;

	@FXML
	private Label labelTerre;

	@FXML
	private Label labelPierre;

	@FXML
	private Label labelBois;

	@FXML
	private Label labelCharbon;

	@FXML
	private Label labelFer;

	@FXML
	private Label labelFleur;

	private void initialiserMap() {
		this.paneJeu.getChildren().add(0, this.vuePlateau.getFond());
		this.paneJeu.getChildren().add(1, this.vuePlateau);
	}

	private void initialiserInventaire() {
		this.labelTerre.setText(Integer.toString(this.jeu.itemChoisi(1)));
		this.labelPierre.setText(Integer.toString(this.jeu.itemChoisi(2)));
		this.labelBois.setText(Integer.toString(this.jeu.itemChoisi(3)));
		this.labelCharbon.setText(Integer.toString(this.jeu.itemChoisi(4)));
		this.labelFer.setText(Integer.toString(this.jeu.itemChoisi(5)));
		this.labelFleur.setText(Integer.toString(this.jeu.itemChoisi(6)));
	}
	
	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(2, this.vueJoueur);
		this.vueJoueur.translateXProperty().bind(this.jeu.coordonneesJoueurX());
		this.vueJoueur.translateYProperty().bind(this.jeu.coordonneesJoueurY());
		this.vueJoueur.orientationProperty().bind(this.jeu.orientationJoueur());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.jeu = new Jeu();
		this.vuePlateau = new VuePlateau(this.jeu.getTabPlateau());
		this.vueJoueur = new VueJoueur();
		this.jeu.ajouterObsPlateau(this.vuePlateau);

		toucheAppuyee = 0;

		initialiserMap();
		initialiserJoueur();
		initAnimation();

		gameloop.play();
	}

	private void initAnimation() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		timer = 0;

		KeyFrame kf = new KeyFrame(Duration.seconds(0.060), (ev -> {
			this.deplacement();
			initialiserInventaire();
			this.jeu.unTour(timer);
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
		Coordonnees coord = new Coordonnees((int) event.getX(), (int) event.getY());

		if (event.getButton() == MouseButton.PRIMARY) {
			this.jeu.ajouterBlocInventaire(coord);
			this.jeu.avertirChangementPlateau("Casser", coord);
		}

		if (event.getButton() == MouseButton.SECONDARY) {
			this.jeu.avertirChangementPlateau("Poser", coord);
		}
	}

	private void deplacement() {
		if (touche != null) {
			if (touche == KeyCode.Q || touche == KeyCode.LEFT) {
				this.jeu.avertirDeplacementJoueur("Gauche");
			}

			if (touche == KeyCode.D || touche == KeyCode.RIGHT) {
				this.jeu.avertirDeplacementJoueur("Droite");
			}

			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				if (toucheAppuyee < 10) {
					this.jeu.avertirDeplacementJoueur("Haut");
					toucheAppuyee++;
				}

				else {
					if (!this.jeu.videSousJoueur()) {
						toucheAppuyee = 0;
					}
				}
			}
		}
	}

	@FXML
	void actionEpee(MouseEvent event) {
		
	}

	@FXML
	void actionPioche(MouseEvent event) {

	}

	@FXML
	void selectionBois(MouseEvent event) {

	}

	@FXML
	void selectionCharbon(MouseEvent event) {
		
	}

	@FXML
	void selectionFer(MouseEvent event) {
		
	}

	@FXML
	void selectionFleur(MouseEvent event) {
		
	}

	@FXML
	void selectionPierre(MouseEvent event) {
		
	}

	@FXML
	void selectionTerre(MouseEvent event) {
		
	}
}