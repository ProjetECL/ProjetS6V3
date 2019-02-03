package application_projet;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.chaines.Calcul;
import application.chaines.ChProduction;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ListeChProdController implements Initializable {

    private ObservableList<ChProduction> PersonData = FXCollections.observableArrayList();
    private Calcul calcul;

    @FXML
    private TableView<ChProduction> table;

    @FXML
    private TextField chaine1;

    @FXML
    private TextField chaine2;

    @FXML
    private TextField chaine3;

    @FXML
    private void gestionButonRetour(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            URL url = new File("src/main/java/application_projet/Accueil.fxml").toURL();
            Parent chainePageParent = FXMLLoader.load(url);
            Scene chainePageScene = new Scene(chainePageParent);
            Stage sceneActuel = stage;
            sceneActuel.setScene(chainePageScene);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeChProdController.fxml"));
        PersonData.addAll(ChProduction.csvVersChainesTest());
        this.calcul = new Calcul();
        PersonData.addListener(new ListChangeListener<ChProduction>() {
            @Override
            public void onChanged(Change<? extends ChProduction> c) {
                calcul.setChaineProductionList(PersonData);
            }
        });
        table.getItems().addAll(PersonData);
    }
    //lancer l'evaluation depuis le controller
    @FXML
    private void gestionButonEvaluer(ActionEvent actionEvent) {
        try {
            this.calcul.evaluer();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            URL url = new File("src/main/java/application_projet/Resultat.fxml").toURL();
            Parent resultatPageParent = FXMLLoader.load(url);
            Scene resultatPageScene = new Scene(resultatPageParent);
            Stage sceneActuel = stage;
            sceneActuel.setScene(resultatPageScene);
        }catch(Exception e) {
            //redirection vers page production impossible
            try {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                URL url = new File("src/main/java/application_projet/ProductionImpossible.fxml").toURL();
                Parent chainePageParent = FXMLLoader.load(url);
                Scene chainePageScene = new Scene(chainePageParent);
                Stage sceneActuel = stage;
                sceneActuel.setScene(chainePageScene);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }






    Stage stage;
    void setStage(Stage stg){stage=stg;}

    public void recupNivActivation3(ActionEvent actionEvent) {
       PersonData.get(2).setNiveau(Integer.parseInt(chaine3.getText()));
    }

    public void recupNivActivation2(ActionEvent actionEvent) {
        PersonData.get(1).setNiveau(Integer.parseInt(chaine2.getText()));
    }

    public void recupNivActivation1(ActionEvent actionEvent) {
        PersonData.get(0).setNiveau(Integer.parseInt(chaine1.getText()));
    }
}