package application_projet;

import java.net.URL;
import java.util.ResourceBundle;

import application.elements.Element;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

 
 
public class EtatStocksController implements Initializable {
 
    private ObservableList<Element> PersonData = FXCollections.observableArrayList();
   /* @FXML
    private Button button;*/
    @FXML
    private TableView<Element> table;
  //  private TableColumn<Object, Integer> Index;
 
    @FXML
	 private void gestionButonRetour(ActionEvent actionEvent) {
    	try {			
			Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			Parent elementPageParent = FXMLLoader.load(getClass().getResource("/application_projet/Accueil.fxml"));
			Scene elementPageScene = new Scene(elementPageParent);
			Stage sceneActuel = stage;
			sceneActuel.setScene(elementPageScene);
		}catch(Exception e) {
			e.printStackTrace();
		}
	 }
    
 /*  public void createColumnManually(int n) {
	   TableColumn <Element, String> nivActivation = new TableColumn <>("Niveau d'activation");
	   table.getColumns().add(n, nivActivation);
   }*/
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       PersonData.addAll(Element.csvVersElement());
 
        table.getItems().addAll(PersonData);
       // table.setEditable(true);
       
    }    
 
    Stage stage;
    void setStage(Stage stg){stage=stg;}
 
}