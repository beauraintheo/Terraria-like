package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.VueInventaire;
import vue.VueJoueur;
import vue.VuePlateau;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Coordonnees;
import modele.Jeu;
import modele.Playlist;

public class Controleur implements Initializable {

	private Jeu jeu;
	private Playlist playlist;
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;
	private VueInventaire vueInventaire;
	private int caseSelectionne;

	private int timer;
	private int toucheAppuyee;

	private Timeline gameloop;
	private KeyCode touche;

	@FXML
	private Pane paneJeu;

	private void initialiserMap() {
		this.paneJeu.getChildren().add(this.vuePlateau.getFond());
		this.paneJeu.getChildren().add(this.vuePlateau);
	}

	private void initialiserInventaire() {
		this.paneJeu.getChildren().add(this.vueInventaire);
		int i = 0;
		while (i < 8) {
			this.vueInventaire.getLabel(i).textProperty().bind(this.jeu.nbExemplaireItemProperty(i).asString());
			i++;
		}
	}

	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(this.vueJoueur);
		this.vueJoueur.translateXProperty().bind(this.jeu.coordonneesJoueurX());
		this.vueJoueur.translateYProperty().bind(this.jeu.coordonneesJoueurY());
		this.vueJoueur.orientationProperty().bind(this.jeu.orientationJoueur());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.jeu = new Jeu();
		this.vuePlateau = new VuePlateau(this.jeu.getTabPlateau());
		this.vueJoueur = new VueJoueur();
		this.vueInventaire = new VueInventaire();
		this.jeu.ajouterObsPlateau(this.vuePlateau);
		this.playlist = new Playlist();

		toucheAppuyee = 0;
		caseSelectionne = -1;
		
		initialiserMap();
		initialiserJoueur();
		initialiserInventaire();

		initAnimation();

		gameloop.play();
	}

	private void initAnimation() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		timer = 0;

		KeyFrame kf = new KeyFrame(Duration.seconds(0.060), (ev -> {
			this.actionsClavier();
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
			if (this.jeu.getIdItemJoueur() == 4 || this.jeu.peutEtreCasseMain(this.jeu.getCasePlateau(coord))) {
				this.jeu.ajouterBlocInventaire(coord);
				this.jeu.avertirChangementPlateau("Casser", coord);

			}

			if (this.jeu.getIdItemJoueur() == 9) {
				this.playlist.jouerMusique(3);
			}
		}

		if (event.getButton() == MouseButton.SECONDARY) {
			this.jeu.avertirChangementPlateau("Poser", coord);
		}
	}

	private void actionsClavier() {
		if (touche != null) {

			if (touche == KeyCode.Q || touche == KeyCode.LEFT) {
				this.jeu.avertirDeplacementJoueur("Gauche");
			}

			if (touche == KeyCode.D || touche == KeyCode.RIGHT) {
				this.jeu.avertirDeplacementJoueur("Droite");
			}

			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				if (toucheAppuyee < 7) {
					this.jeu.avertirDeplacementJoueur("Haut");
					toucheAppuyee++;
				}

				else {
					if (!this.jeu.videSousJoueur()) {
						toucheAppuyee = 0;
					}
				}
			}

			if ((touche == KeyCode.DIGIT1 || touche == KeyCode.F1) && caseSelectionne != 0) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(0).selectionnerCase();
				this.caseSelectionne = 0;
				int idItem = 9;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT2 || touche == KeyCode.F2) && caseSelectionne != 1) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(1).selectionnerCase();
				this.caseSelectionne = 1;
				int idItem = 4;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT3 || touche == KeyCode.F3) && caseSelectionne != 2) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(2).selectionnerCase();
				this.caseSelectionne = 2;
				int idItem = 1;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT4 || touche == KeyCode.F4) && caseSelectionne != 3) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(3).selectionnerCase();
				this.caseSelectionne = 3;
				int idItem = 2;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT5 || touche == KeyCode.F5) && caseSelectionne != 4) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(4).selectionnerCase();
				this.caseSelectionne = 4;
				int idItem = 5;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT6 || touche == KeyCode.F6) && caseSelectionne != 5) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(5).selectionnerCase();
				this.caseSelectionne = 5;
				int idItem = 6;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT7 || touche == KeyCode.F7) && caseSelectionne != 6) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(6).selectionnerCase();
				this.caseSelectionne = 6;
				int idItem = 7;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}

			if ((touche == KeyCode.DIGIT8 || touche == KeyCode.F8) && caseSelectionne != 7) {
				if (this.caseSelectionne != -1)
					this.vueInventaire.getVueInventaireCase(caseSelectionne).deselectionnerCase();
				this.vueInventaire.getVueInventaireCase(7).selectionnerCase();
				this.caseSelectionne = 7;
				int idItem = 17;
				this.jeu.changerIdItemJoueur(idItem);
				this.playlist.jouerMusique(2);
			}
		}
	}

}