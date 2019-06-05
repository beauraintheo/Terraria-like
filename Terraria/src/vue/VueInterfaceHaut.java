package vue;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class VueInterfaceHaut extends HBox {
	
	private Label nomItem;
	private Image caseInventaire;
	
	public VueInterfaceHaut() {
		
	}
	
	//Boucle pour créer les 8 cases de l'inventaire (new ImageView)
	
	public void creationInventaire() {
		for (int i = 0; i < 8; i++) {
			ImageView img = new ImageView();
		}
	}
}