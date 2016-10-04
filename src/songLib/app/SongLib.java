//Jerry Liu - jl1421 - 138006256
//Cynthia Liu - cl844 - 151001799

package songLib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import songLib.view.SongLibController;

public class SongLib extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songLib/view/SongLibLayout.fxml"));
		
		GridPane root = (GridPane)loader.load();
		SongLibController listController = loader.getController();
		listController.start(primaryStage);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main (String [] args) {
		launch(args);
	}
}
