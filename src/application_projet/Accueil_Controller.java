package application_projet;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Accueil_Controller implements Initializable{
	
	@FXML
	private Button EtatStocks;
	
	public void handle(ActionEvent actionEvent) {
		System.out.println("Salut le monde!");
	}

		@Override
	public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
	}
}
