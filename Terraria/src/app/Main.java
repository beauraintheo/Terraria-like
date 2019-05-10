package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import modele.plateau.Plateau;

public class Main implements Initializable {
	
	private Plateau p = new Plateau("src/vue/maps/map1.csv");;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p.initMap();
	}
}
