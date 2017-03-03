package application.view;

import application.Main;
import javafx.fxml.FXML;

public class LibrairieController {
	
	private Main main;
	
	public void setMain(Main main){
		this.main = main;
	}
	
	@FXML
	public void handleLaunchTetris(){
		main.showTetris();
	}
	
	public void handleLaunchBlokus(){
		main.showBlokus();
	}
	
	public void handleLaunchPuzzle(){
		main.showPuzzle();
	}
}
