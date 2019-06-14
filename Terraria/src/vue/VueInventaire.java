/*
 * VueInventaire.java
 * Cette classe s'occupe de créer notre inventaire côté vue
 */

package vue;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VueInventaire extends HBox {

	private ArrayList<VueInventaireCase> casesInventaire;
	private VueInventaireCase caseInventaire;

	public VueInventaire() {
		this.casesInventaire = new ArrayList<VueInventaireCase>();
		initialiserVueInventaire();
		this.setAlignment(Pos.CENTER_LEFT);
		this.setSpacing(8);
		this.setStyle("-fx-background-color: #54bac3;");
	}

	private void initialiserVueInventaire() {
		for (int i = 1; i < 9; i++) {
			this.caseInventaire = new VueInventaireCase(i);
			this.casesInventaire.add(this.caseInventaire);
			this.getChildren().add(this.caseInventaire);
		}
	}

	public Label getLabel(int i) {
		return this.casesInventaire.get(i).getLabel();
	}

	public VueInventaireCase getVueInventaireCase(int i) {
		return this.casesInventaire.get(i);
	}
}