package application_projet;

import java.net.URL;
import java.util.ResourceBundle;

import application.elements.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
 
 
public class EtatStocksController implements Initializable {
 
    private ObservableList<Element> PersonData = FXCollections.observableArrayList();
 
    @FXML
    private Button button;
    @FXML
    private TableView<Element> table;
 
 
    @FXML
    private void handleButtonAction(ActionEvent event) {
       stage.close();
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       PersonData.addAll(Element.csvVersElement());
 
        table.getItems().addAll(PersonData);
    }    
 
    Stage stage;
    void setStage(Stage stg){stage=stg;}
 
}