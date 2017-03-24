package application.controller;

import application.Main;
import javafx.fxml.FXML;

/**
 * Contient les méthodes de comportement des boutons de la librairie.
 * Appelle simplement les méthodes du Main.
 */
public class LibrairieController {

	/**
	 * Appication JavaFX
	 */
	private Main main;

	/**
	 * Modifie la référence du Main
	 *
	 * @param main
	 */
	public void setMain(Main main){
		this.main = main;
	}

	/**
	 * Lance le Tetris
	 */
	@FXML
	public void handleLaunchTetris(){
		main.showTetris();
	}

	/**
	 * Lance le Blokus
	 */
	@FXML
	public void handleLaunchBlokus(){
		main.showBlokus();
	}

	/**
	 * Lance le Puzzle
	 */
	@FXML
	public void handleLaunchPuzzle(){
		main.showPuzzle();
	}
}
