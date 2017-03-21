package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Blokus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class BlokusController extends GameController implements Initializable{

    @FXML private Button passBtn;
    
    @FXML private Button ruleBtn;
    
    @FXML private Button quitBtn;
    
    private Blokus game;
    
    @Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
    @FXML
    private void passBtnAction(){
        
    }
    
    @FXML
    private void ruleBtnAction(ActionEvent event) throws IOException{
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/BlokusRules.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           System.out.println("Can't load new window");
          }
    }
    
    @FXML
    private void quitBtnAction(){
        Stage stage = (Stage) quitBtn.getScene().getWindow();
    stage.close();
    }
    
}
