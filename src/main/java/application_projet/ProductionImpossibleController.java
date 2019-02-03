package application_projet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class ProductionImpossibleController {
    @FXML
    private void gestionButonRetour(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            URL url = new File("src/main/java/application_projet/Accueil.fxml").toURL();
            Parent productionPageParent = FXMLLoader.load(url);
            Scene productionPageScene = new Scene(productionPageParent);
            Stage sceneActuel = stage;
            sceneActuel.setScene(productionPageScene);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
