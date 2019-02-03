package application_projet;

import application.chaines.Calcul;
import application.chaines.ChProduction;
import application.elements.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultatController implements Initializable {
    private ObservableList<Element> AchatData = FXCollections.observableArrayList();
    @FXML
    private TableView<Element> table;
    private Calcul calcul;

    @FXML
    private void gestionButonRetour(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            URL url = new File("src/main/java/application_projet/ListeChProduction.fxml").toURL();
            Parent chainePageParent = FXMLLoader.load(url);
            Scene chainePageScene = new Scene(chainePageParent);
            Stage sceneActuel = stage;
            sceneActuel.setScene(chainePageScene);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        try {
            AchatData.addAll(calcul.achat());
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getItems().addAll(AchatData);
        // table.setEditable(true);
    }
}
