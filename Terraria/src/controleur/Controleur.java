package controleur;
/**
 * Sample Skeleton for 'Test.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import modele.*;
import vue.vuePlateau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{

	private vuePlateau vueMap= new vuePlateau();
    
	@FXML
	private TilePane tilePaneMap;
    
    
    
    @Override
	public void initialize(URL location,ResourceBundle resources) {
    	Image tuiles = new Image("tileset3.png");
    	ImageView tuile = new ImageView();

    	vueMap.afficherMap();
    }
}
