/*
 * Main.java
 * Cette classe lance notre programme.
 */

package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Test.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root, 800, 800);
			primaryStage.setScene(scene);
			root.requestFocus();
			primaryStage.setTitle("Terraria");
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

// ©Terraria - BEAURAIN Théo - UTKIN Liam - PAPROCKI Alexandre
