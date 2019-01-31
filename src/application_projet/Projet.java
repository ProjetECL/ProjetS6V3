package application_projet;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Projet extends Application {
	private Stage primaryStage;
	
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application_projet/Accueil.fxml"));
			primaryStage.setTitle("Application de gestion de production");
			primaryStage.setScene(new Scene(root,400,400));
			primaryStage.show();
			this.primaryStage=primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
