package application_projet;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.*;

import javafx.stage.Stage;

public class AccueilController implements Initializable{

	@FXML private Projet main= new Projet();
	
	@FXML
	private Button ListeChaineProduction;
	@FXML 
	private javafx.scene.control.Button closeButton;
	@FXML
	public void handle(ActionEvent actionEvent) {
			
			try {			
				Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
				Parent elementPageParent = FXMLLoader.load(getClass().getResource("/application_projet/EtatStocks.fxml"));
				Scene elementPageScene = new Scene(elementPageParent);
				Stage sceneActuel = stage;
				sceneActuel.setScene(elementPageScene);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
			
	@FXML
	 private void gestionButonFermer(ActionEvent event) {
		    	 Stage stage = (Stage) closeButton.getScene().getWindow(); 
		    	   
		    	 stage.close(); 
	 }

		@Override
	public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
	}
}
