package application_projet;
	
import application.chaines.ChProduction;
import application.elements.Element;
import application_projet.model.AssociationChProdElement;
import application_projet.model.TypeElement;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import utils.Log;

import java.io.File;
import java.net.URL;


public class Projet extends Application {
	private static final String TAG = "Projet";
	private Stage primaryStage;
	
	public void start(Stage primaryStage) {
		try {
			URL url = new File("src/main/java/application_projet/Accueil.fxml").toURL();
			Parent root = FXMLLoader.load(url);
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

		//Log.d(TAG,ChProduction.csvVersChainesT().toString());
		launch(args);
	}
}
