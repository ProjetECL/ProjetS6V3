package application_projet;

import java.net.URL;
import java.util.ResourceBundle;

import application.chaines.ChProduction;
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

 
 
public class ListeChProdController implements Initializable {
 
    private ObservableList<ChProduction> ChaineData = FXCollections.observableArrayList();
   /* @FXML
    private Button button;*/
    @FXML
    private TableView<ChProduction> table;
  //  private TableColumn<Object, Integer> Index;
 
    @FXML
	 private void gestionButonRetour(ActionEvent actionEvent) {
    	try {			
			Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			Parent chainePageParent = FXMLLoader.load(getClass().getResource("/application_projet/Accueil.fxml"));
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
       ChaineData.addAll(ChProduction.csvVersChaines());
 
        table.getItems().addAll(ChaineData);
       // table.setEditable(true);
       
    }    
 
    Stage stage;
    void setStage(Stage stg){stage=stg;}
 
}