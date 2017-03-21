package application.controller;

import application.Main;
import application.model.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public abstract class GameController {
	
	@FXML
	protected GridPane gridView;
	
	protected Game game;

	protected Main main;
	
	public abstract void init();
	
	public abstract void update();
	
	public void setMain(Main main) {
		this.main = main;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
