package nkg5.serverswither;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerSwitcher extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("res/ServerSwitcher.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("res/style.css").toString());
		stage.setScene(scene);
		stage.show();
	}

}
