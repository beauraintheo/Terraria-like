/*
 * Controleur.java
 * Le controleur fait le lien entre le modèle et la vue. Il possède la gameloop et s'occupe d'initialiser toutes les vues.
 * Il s'occupe aussi des déplacements
 */

package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import vue.VueEnnemi;
import vue.VueInventaire;
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
import modele.GrandChefMH;
import modele.Playlist;
import test.HorsDuTerrainException;

public class Controleur implements Initializable {

	private GrandChefMH jeu;
	private Playlist playlist;
	
	private VuePlateau vuePlateau;
	private VueJoueur vueJoueur;
	private VueEnnemi vueEnnemi;
	private ArrayList<VueEnnemi> vuesEnnemis;
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
		this.paneJeu.getChildren().get(3).setTranslateX(16);
		this.paneJeu.getChildren().get(3).setTranslateY(16);

		int i = 0;

		while (i < 8) {
			this.vueInventaire.getLabel(i).textProperty().bind(this.jeu.nbExemplaireItemProperty(i).asString());
			i++;
		}
	}

	private void initialiserJoueur() {
		this.paneJeu.getChildren().add(this.vueJoueur);
		this.vueJoueur.translateXProperty().bind(this.jeu.coordonneesJoueurXProperty());
		this.vueJoueur.translateYProperty().bind(this.jeu.coordonneesJoueurYProperty());
		this.vueJoueur.orientationProperty().bind(this.jeu.orientationJoueur());
	}

	private void initialiserEnnemis() {
		for (int i = 0; i < this.jeu.nbEnnemis(); i++) {
			this.vueEnnemi = new VueEnnemi();
			this.paneJeu.getChildren().add(this.vueEnnemi);
			this.vueEnnemi.orientationProperty().bind(this.jeu.getEnnemi(i).orientationProperty());
			this.vueEnnemi.xProperty().bind(this.jeu.getEnnemi(i).getPosition().coordXProperty());
			this.vueEnnemi.yProperty().bind(this.jeu.getEnnemi(i).getPosition().coordYProperty());
			this.vuesEnnemis.add(this.vueEnnemi);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.jeu = new GrandChefMH(2, 656, 288);
		} catch (HorsDuTerrainException e) {
			e.printStackTrace();
		}

		this.vuePlateau = new VuePlateau(this.jeu.getPlateau());
		this.vueJoueur = new VueJoueur();
		this.vueInventaire = new VueInventaire();
		this.vuesEnnemis = new ArrayList<VueEnnemi>();
		this.jeu.ajouterObsPlateau(this.vuePlateau);
		this.playlist = new Playlist();

		toucheAppuyee = 0;
		caseSelectionne = -1;

		initialiserMap();
		initialiserJoueur();
		initialiserInventaire();
		initialiserEnnemis();

		initAnimation();

		gameloop.play();
	}

	// Initialisation de la gameloop
	
	private void initAnimation() {
		gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		timer = 0;

		KeyFrame kf = new KeyFrame(Duration.seconds(0.060), (ev -> {
			this.actionsClavier();
			this.jeu.unTour(timer);
			verifOrientationVuesEnnemis();
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

	// Pouvoir poser ou casser un bloc
	
	@FXML
	void actionBloc(MouseEvent event) {
		Coordonnees coord = new Coordonnees((int) event.getX(), (int) event.getY());
		
		if (event.getButton() == MouseButton.PRIMARY) {
			if (this.jeu.getIdItemJoueur() == 4 || this.jeu.peutEtreCasseMain(this.jeu.getCasePlateau(coord))) {
				this.jeu.ajouterBlocInventaire(coord);
				this.jeu.avertirChangementPlateau("Casser", coord);
			}

			if (this.jeu.getIdItemJoueur() == 9)
				this.playlist.jouerMusique(3);
		}

		if (event.getButton() == MouseButton.SECONDARY)
			this.jeu.avertirChangementPlateau("Poser", coord);
	}
	
	// Déplacements du joueur

	private void actionsClavier() {
		if (touche != null) {
			if (touche == KeyCode.Q || touche == KeyCode.LEFT)
				this.jeu.avertirDeplacementJoueur("Gauche");

			if (touche == KeyCode.D || touche == KeyCode.RIGHT)
				this.jeu.avertirDeplacementJoueur("Droite");

			if (touche == KeyCode.Z || touche == KeyCode.UP) {
				if (toucheAppuyee < 7) {
					this.jeu.avertirDeplacementJoueur("Haut");
					toucheAppuyee++;
				}

				else {
					if (!this.jeu.videSousJoueur()) 
						toucheAppuyee = 0;
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

	private void verifOrientationVuesEnnemis() {
		for (int i = 0; i < this.vuesEnnemis.size(); i++) {
			this.vuesEnnemis.get(i).chargerImage();
		}
	}

}