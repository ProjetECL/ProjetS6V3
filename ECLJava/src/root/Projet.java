package root;

import javafx.application.*;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import javafx.fxml.*;
import javafx.scene.*;

public class Projet extends Application {
    private Stage primaryStage;

    public void start(Stage primaryStage) {
        try {
            URL url = new File("src/modele/view/Accueil.fxml").toURL();
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
