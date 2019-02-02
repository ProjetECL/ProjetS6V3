package application_projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxGridView extends Application {

   @Override
   public void start(Stage stage) throws Exception {

FXMLLoader loader = new FXMLLoader(JavaFxGridView.class.getResource("Test.fxml"));
Parent root = (Parent)loader.load();
final EtatStocksController controller = loader.getController();
controller.setStage(stage);

Scene scene = new Scene(root);
stage.setScene(scene);
stage.setTitle("Exemple d'utilsation d'un tabview - interêt du CellValueFactory");
stage.show();
   }

   public static void main(String[] args) {
       launch(args);
   }

}