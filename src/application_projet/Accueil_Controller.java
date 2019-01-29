package application_projet;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Accueil_Controller implements Initializable{

	@FXML
	private Button EtatStocks;
	
		public void handle(ActionEvent actionEvent) {
			Stage stage = new Stage();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application_projet/EtatStocks.fxml"));
				stage.setTitle("Etat des stocks");
				stage.setScene(new Scene(root,400,400));
				stage.show();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

		@Override
	public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
	}
}
