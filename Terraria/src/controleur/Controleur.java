package controleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import modele.*;
import vue.vueJoueur;
import vue.vuePlateau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{

	private vuePlateau vueMap = new vuePlateau();
	private int[][] notreMap;
	private Image tuiles;
	private PixelReader reader;
	private int tilesetX = 0;
	private int tilesetY = 0;
	
	@FXML
	private ImageView personnage;
	
    @FXML
    private Pane paneJeu;
	
	@FXML
	private TilePane tilePaneMap;
	
    private void initialiserMap() {
    	notreMap = vueMap.getMap();
    	tuiles = new Image("/vue/maps/tileset.png");
    	
    	for(int x=0; x<notreMap.length-1;x++) {
    		for(int y=0; y<notreMap[x].length;y++) {
	    		ImageView tuile = new ImageView();
    			tuile.setImage(tuiles);
	    		selectionTuile(notreMap[x][y]);
    			Rectangle2D decoupage = new Rectangle2D(tilesetX,tilesetY,16,16);
    			tuile.setViewport(decoupage);
    			
	    		
	    		this.tilePaneMap.getChildren().add(tuile);
    		}	
    	}
    }
    
    private void initialiserJoueur() {
		File imgGauche = new File("Ressources/Sprites/Personnage/player_left.png");
		Image persoVueGauche = new Image(imgGauche.toURI().toString());
    	personnage = new ImageView(persoVueGauche);
    	this.paneJeu.getChildren().add(personnage);
    }
    
    private void selectionTuile(int tuile) {
    	switch (tuile) {
    		case 0: //ciel
    			this.tilesetX=1;
    			this.tilesetY=1;
    			break;
    		case 1: //terre
    			this.tilesetX=18;
    			this.tilesetY=1;
    			break;
    		case 3: //bois
    			this.tilesetX=52;
    			this.tilesetY=1;
    			break;
    		case 7: //herbe
    			this.tilesetX=1;
    			this.tilesetY=17;
    			break;
    		case 8: //herbe - côté droit
    			this.tilesetX=18;
    			this.tilesetY=18;
    			break;
    		case 9: //herbe - côté gauche
    			this.tilesetX=35;
    			this.tilesetY=18;
    			break;
    		case 15: //herbe - coin haut droit
    			this.tilesetX=1;
    			this.tilesetY=52;
    			break;
    		case 28: //pierre
    			this.tilesetX=1;
    			this.tilesetY=69;
    			break;
    		case 29: //charbon
    			this.tilesetX=18;
    			this.tilesetY=69;
    			break;
    		default:
    			this.tilesetX=18;
    			this.tilesetY=18;
    	}
    }
    
    /*public void mouvement(KeyEvent event) {
    	// Déplacement à gauche
    	if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.LEFT) {
    		player.setImage(pv.gauche());
    	}
    	else
    		player.setImage(pv.gauche());
    }*/
    
    @Override
	public void initialize(URL location,ResourceBundle resources) {
    	initialiserMap();
    	initialiserJoueur();
    	
    }
}
